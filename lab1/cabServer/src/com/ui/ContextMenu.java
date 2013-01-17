package com.ui;

import com.map.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class ContextMenu extends JPopupMenu{
    
    Map map;
    
    public ContextMenu(final Map map){
        this.map = map;
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = ((JMenuItem)e.getSource()).getText();
                if(s.equals("Add Landmark")){ map.addLandmark(); }
                else if(s.equals("Add to Road")){ map.addRoad(); }
                else if(s.equals("Add Cab")){ map.addCab(); }
                else if(s.equals("Delete Landmark")){ map.deleteLandmark(); }
                else if(s.equals("Delete Road")){ map.deleteRoad(); }
                else if(s.equals("Delete Cab")){ map.deleteCab(); }
            }
        };
        
        JMenuItem item = new JMenuItem("Add Landmark"); add(item); item.addActionListener(al);
        item = new JMenuItem("Add to Road"); add(item); item.addActionListener(al);
        item = new JMenuItem("Add Cab"); add(item); item.addActionListener(al);
        item = new JMenuItem("Delete Landmark"); add(item); item.addActionListener(al);
        item = new JMenuItem("Delete Road"); add(item); item.addActionListener(al);
        item = new JMenuItem("Delete Cab"); add(item); item.addActionListener(al);
    }
}
