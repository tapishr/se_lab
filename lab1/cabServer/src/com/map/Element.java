/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author Anurag
 */
public class Element{
    public int type;
    public int x, y, H, W, L;
    public Color fillColor, boundaryColor;
    public String id, src, dst;
    public CopyOnWriteArrayList<Element> obj;
    public int sX = 0, sY = 0, eX = 0, eY = 0;
    
    public Element(int x, int y, int type, String id){
        this.x = x; this.y = y; this.type = type; this.id = id;
        fillColor = Color.LIGHT_GRAY; src = ""; dst = ""; L = 0;
        
        H = W = 14;
        
        if(type == 1){ boundaryColor = Color.BLACK; }
        else if(type == 2){  boundaryColor = Color.BLUE; }
    }
    
    public Element(String src, String dst, int L, CopyOnWriteArrayList<Element> obj){
        this.src = src; this.dst = dst; this.L = L;
        x = y = H = W = 0;
        type = 3; id = ""+L;
        this.obj = obj;
    }
    
    public boolean inShape(int mx, int my){
        if(type == 1){
            if(mx >= x && mx <= x+W && my >= y && my <= y+H){ return true; }else{ return false; }
        }
        else if(type == 2){
            return Math.sqrt((mx-x)*(mx-x)+(my-y)*(my-y)) < W;
        }
        else{ 
            return (Line2D.ptLineDist(sX, sY, eX, eY, mx, my) < 7);
        }
    }
    
    public void draw(Graphics g){
        if(type == 1){
            g.setColor(fillColor); g.fillOval(x, y, W, H);
            g.setColor(boundaryColor); g.drawOval(x, y, W, H);
        }
        else if(type == 2){
            g.setColor(fillColor); g.fillRect(x, y, W, H);
            g.setColor(boundaryColor); g.drawRect(x, y, W, H);           
        }
        else{
            for(Element e : obj){
                if(src.equals(e.id) && e.type == 1){ sX = e.x+e.W/2; sY = e.y+e.W/2; }
                if(dst.equals(e.id) && e.type == 1){ eX = e.x+e.H/2; eY = e.y+e.H/2; }
            }
            g.drawLine(sX, sY, eX, eY); x = (sX+eX)/2; y = (sY+eY)/2;
        }
        g.drawString(id, x + 4, y - 4);
    }
    
    public double getDistance(int mx, int my){
        return Math.sqrt((mx - x)*(mx - x) + (my - y)*(my - y));
    }
}
