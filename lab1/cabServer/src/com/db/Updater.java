package com.db;

import com.map.*;
import com.ui.MainFrame;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;

public class Updater implements Runnable{

    public MainFrame ui;
    public Map map;
    public DateFormat df;
    
    public Updater(MainFrame ui, Map map){
        this.ui = ui; this.map = map; df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    }
 
    public int compareDates(Date d1, Date d2){
        if (d1.getYear() != d2.getYear()) 
            return d1.getYear() - d2.getYear();
        if (d1.getMonth() != d2.getMonth()) 
            return d1.getMonth() - d2.getMonth();
        return d1.getDate() - d2.getDate();
    }
    
    public Element getElement(String id, int type){
        for(Element e : ui.map.obj){
            if(e.id.equals(id) && e.type == type){ return e; }
        }
        return null;
    }
        
    @Override
    public void run() {
        while(true){
            try{
                DefaultTableModel model = (DefaultTableModel)ui.jTable1.getModel();
                
                int N = model.getRowCount();
                while(N > 0){ model.removeRow(N-1); N--; }
                
                ResultSet rs = ui.db.fetchTable("*", "cabs");
                while(rs.next()){
                    model.addRow(new Object[]{rs.getString("id"), rs.getString("driver"), rs.getString("type"), rs.getString("capacity"), rs.getString("reading")});
                }
                
                
                if(ui.JF.isVisible()){
                    for(Element e : map.obj){
                        if(e.type == 2){
                            try{
                                Date today = new Date();
                                rs = ui.db.fetchTable("*", "cab_"+e.id);
                                
                                while(rs != null & rs.next()){
                                    Date date = df.parse(rs.getString("time"));
                                    int seq = Integer.parseInt(rs.getString("seq"));
                                    if(compareDates(today, date) > 0){ ui.db.execute("DELETE FROM cab_"+e.id+" WHERE seq="+seq); }
                                    else if(compareDates(today, date) == 0){
                                        
                                        Element s = getElement(rs.getString("src"), 1), d = getElement(rs.getString("dst"), 1);
                                        if(today.getHours() == date.getHours() && today.getMinutes() == date.getMinutes() && rs.getString("temp").equals("#")){
                                            //ui.db.execute("UPDATE cabs SET x='"+s.x+"', y='"+s.y+"' WHERE id='"+e.id+"'");
                                            ui.db.execute("UPDATE cab_"+e.id+" SET temp='A' WHERE seq="+seq);
                                            e.x = s.x; e.y = s.y; ui.map.repaint();
                                            
                                            Thread t = new Thread(new cabMover(e, s, d, seq, this, ui.graph.getPath(s.id, d.id)));
                                            t.start();
                                        }
                                    }
                                }
                            }
                            catch(Exception ex){ System.out.println(ex); ex.printStackTrace(); }
                        }
                    }
                }
                Thread.sleep(3000);
            }
            catch(Exception ex){ }
        }
    }
}


class cabMover implements Runnable{

    public Element cab, src, dst, temp;
    public boolean keepMoving = true;
    public Updater up;
    public int seq, count = 1, length = 0;
    public LinkedList<String> path;
           
    
    public cabMover(Element cab, Element src, Element dst, int seq, Updater up, LinkedList<String> path){
        this.cab = cab; this.src = src; this.dst = dst; this.up = up; this.seq = seq; this.path = path;
    }
    
    @Override
    public void run() {
        while(keepMoving){
            try{
                temp = up.getElement(path.get(count), 1);
                cab.boundaryColor = Color.RED;
                           
                if(dst.getDistance(cab.x, cab.y) < 8){
                    up.ui.db.execute("DELETE FROM cab_"+cab.id+" WHERE seq="+seq); keepMoving = false;
                    cab.boundaryColor = Color.BLUE;
                }
                if(temp.getDistance(cab.x, cab.y) < 8){
                    src = temp; count++;
                    up.ui.db.execute("UPDATE cabs SET reading = reading + "+length+", status='1' WHERE id='"+cab.id+"'");
                }
                for(Element l : up.ui.map.obj){
                    if(l.type == 3 && ((l.src.equals(src.id) && l.dst.equals(temp.id)) || (l.src.equals(temp.id) && l.dst.equals(src.id)))){ length = l.L; }
                }  
                
                cab.x += (temp.x - src.x)/length; cab.y += (temp.y - src.y)/length;
                up.ui.map.repaint();
                up.ui.db.execute("UPDATE cabs SET x='"+cab.x+"', y='"+cab.y+"', status='0' WHERE id='"+cab.id+"'");
                Thread.sleep(1500);
            }
            catch(Exception ex){ System.out.println(ex); }
        }
    }
    
}