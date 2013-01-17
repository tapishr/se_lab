package com.map;

import com.ui.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JOptionPane;

public class Map extends Canvas{
    
    public CopyOnWriteArrayList<Element> obj;
    public ContextMenu menu;
    public int mx, my;
    public String temp_src = "", temp_dst = "";
    public ResultSet rs;
    
    public MainFrame ui;
    
    public Map(MainFrame ui){
        
        obj = new CopyOnWriteArrayList<Element>();
        menu = new ContextMenu(this);
        this.ui = ui;
        
        addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e){
                if(e.getButton() == 1){
                    menu.setVisible(false);
                }
                else if(e.getButton() == 3){
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseMoved(MouseEvent e){
                mx = e.getX(); my = e.getY();
            }
        });
    }
    
    
    @Override
    public void paint(Graphics g){
        for(Element e : obj){
            e.draw(g);
        }
    }
    
    public void loadStatic(){
        obj = new CopyOnWriteArrayList<Element>();
        
        try{
            rs = ui.db.fetchTable("*", "landmarks");
            while(rs.next()){
                String s = rs.getString("name");
                int x = Integer.parseInt(rs.getString("x")); int y = Integer.parseInt(rs.getString("y"));
                obj.add(new Element(x, y, 1, s)); ui.graph.addNode(s, x, y);
            }
            
            rs = ui.db.fetchTable("*", "roads");
            while(rs.next()){
                int i = Integer.parseInt(rs.getString("length"));
                String s = rs.getString("id"); ui.graph.addLink(s.split("#:#")[0], s.split("#:#")[1], i);
                obj.add(new Element(s.split("#:#")[0], s.split("#:#")[1], i, obj));
            }
            
        }
        catch(Exception ex){}
    }
    
    public void loadCabs(){
        try{
            double temp = 9999;
            rs = ui.db.fetchTable("id, x, y, status", "cabs");
            while(rs.next()){
                int x = Integer.parseInt(rs.getString("x")); int y = Integer.parseInt(rs.getString("y"));
                if(x >= 0){
                    int s = Integer.parseInt(rs.getString("status")); Element e = new Element(x, y, 2, rs.getString("id")); e.L = 1;
                    if(s == 0){ e.boundaryColor = Color.BLUE; }
                    else{ e.boundaryColor = Color.RED; }
                    obj.add(e);
                    for(Element n : obj){
                        if(n.type == 1 && n.getDistance(x, y) < temp){ temp = n.getDistance(x, y); e.sX = n.x; e.sY = n.y; e.src = n.id; }
                    }
                }
            }
        }
        catch(Exception ex){}        
    }
    
    public void addLandmark(){
        String name = JOptionPane.showInputDialog("Landmark name?");
        if(name != null){ 
            rs = ui.db.fetchTable("*", "landmarks");
            try{
                boolean found = false;
                while(rs.next()){
                    if(name.equals(rs.getString("name"))){ found = true; break; }
                }
                if(found){ JOptionPane.showMessageDialog(menu, "Landmark already there"); }
                else{ 
                    ui.db.insert("INSERT INTO landmarks (name, x, y) VALUES ('"+name+"', '"+mx+"', '"+my+"')");
                    obj.add(new Element(mx, my, 1, name)); ui.graph.addNode(name, mx, my); repaint(); 
                }
            }
            catch(Exception ex){} 
        }
    }
    public void addRoad(){
        if(temp_src.length() == 0){
            for(Element e : obj){
                if(e.inShape(mx, my) && e.type == 1){ temp_src = e.id; break; }
            }
        }else{
            for(Element e : obj){
                if(e.inShape(mx, my) && e.type == 1){ 
                    String s = JOptionPane.showInputDialog("Road length?");
                    if(s != null){
                        int length = Math.abs(Integer.parseInt(s));
                        temp_dst = e.id; ui.graph.addLink(temp_src, temp_dst, length);
                        ui.db.insert("INSERT INTO roads (id, length) VALUES ('"+temp_src+"#:#"+temp_dst+"', '"+length+"')");
                        obj.add(new Element(temp_src, temp_dst, length, obj)); repaint(); break;
                    }
                    temp_src = ""; temp_dst = "";
                }
            }
        }
    }
    
    public void addCab(){
        for(Element e : obj){
            if(e.inShape(mx, my) && e.type != 2){
                String id = JOptionPane.showInputDialog("Cab registration number?");
                if(id != null){
                    try{
                        rs = ui.db.fetchTable("id, x, status", "cabs"); boolean found = false; int s = 0;
                        while(rs.next()){
                            int x = Integer.parseInt(rs.getString("x"));
                            if(id.equals(rs.getString("id")) && x < 0){ found = true; s = Integer.parseInt(rs.getString("status")); break; }
                        }
                        if(found){                            
                           ui.db.execute("UPDATE cabs SET x='"+mx+"', y='"+my+"' WHERE id='"+id+"'");
                           Element cab = new Element(mx, my, 2, id); 
                           if(s == 0){ cab.boundaryColor = Color.BLUE; }
                           else{ cab.boundaryColor = Color.RED; }
                           obj.add(cab); repaint();
                        }else{ JOptionPane.showMessageDialog(menu, "Invalid cab"); }
                    }
                    catch(Exception ex){}
                }                
                break;
            }
        }
    }
    
    public void deleteLandmark(){
        for(Element e : obj){
            if(e.inShape(mx, my) && e.type == 1){ 
                obj.remove(e); 
                ui.db.execute("DELETE FROM landmarks WHERE name='"+e.id+"'");
                rs = ui.db.fetchTable("id", "roads");
                try{
                    while(rs.next()){
                        String s = rs.getString("id");
                        if(e.id.equals(s.split("#:#")[0]) ||  e.id.equals(s.split("#:#")[1])){
                            ui.db.execute("DELETE FROM roads WHERE id='"+s+"'");
                            for(Element r : obj){
                                if(r.src.equals(s.split("#:#")[0]) && r.dst.equals(s.split("#:#")[1])){ obj.remove(r); }
                            }
                        }
                    }
                }
                catch(Exception ex){}
                repaint(); break; 
            }
        }
    }
    public void deleteCab(){
        for(Element e : obj){
            if(e.inShape(mx, my) && e.type == 2){
                ui.db.execute("UPDATE cabs SET x='-1', y='-1' WHERE id='"+e.id+"'");
                obj.remove(e); repaint(); break; 
            }
        }
    }  
    public void deleteRoad(){
        for(Element e : obj){
            if(e.inShape(mx, my) && e.type == 3){ 
                obj.remove(e);
                ui.db.execute("DELETE FROM roads WHERE id='"+e.src+"#:#"+e.dst+"'");
                repaint(); 
            }
        }
    }
}