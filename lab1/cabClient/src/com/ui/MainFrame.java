package com.ui;

import javax.swing.UIManager;
import com.db.*;
import java.awt.Color;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {

    public Database db;
    public ResultSet rs;
    public boolean loggedIn = false;
    public String usr, name, pass, date, time, found;
    DateFormat df;
    boolean isShared = false;
    
    public String src = "", dst = "", type = "";
    public int Cap = 1, Max = 0;
    
    public MainFrame() {
        initComponents();
        db = new Database("db", "root", "password");
        //jTextField1.setText(""); jPasswordField1.setText(""); jTextField3.setText(""); jTextField4.setText("");
        jTextField2.setEditable(false); jTextField2.setBackground(Color.WHITE);
        jButton3.setEnabled(false); jButton4.setEnabled(false);
        jComboBox1.setEnabled(false); jComboBox2.setEnabled(false); jComboBox3.setEnabled(false);
        jSpinner1.setEnabled(false);
        
        df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat f = new SimpleDateFormat("HH:mm");
        jTextField3.setText(df.format(new Date()));
        jTextField4.setText(f.format(new Date()));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cab-Client");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Login"));

        jLabel1.setText("Username :");

        jTextField1.setText("anurag");

        jLabel2.setText("Password :");

        jPasswordField1.setText("pass");

        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sign Up");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cab Reservation"));

        jLabel3.setText("Pick-Up :");

        jLabel4.setText("Destination :");

        jLabel5.setText("Type :");

        jSpinner1.setValue(1);

        jLabel6.setText("Passengers :");

        jButton3.setText("Reserve");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Check Status");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel8.setText("Date :");

        jTextField3.setToolTipText("yyyy-mm-dd");
        jTextField3.setEnabled(false);

        jLabel9.setText("Time (24 Hours):");

        jTextField4.setToolTipText("HH:mm");
        jTextField4.setEnabled(false);

        jCheckBox1.setText("Shared");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox3, 0, 73, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(203, 203, 203)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSpinner1)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField4))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jLabel7.setText("Result :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        usr = jTextField1.getText(); pass = jPasswordField1.getText();
        if(usr.length() > 0 && pass.length() > 0){
            rs = db.fetchTable("*", "users");
            try{
                while(rs.next()){
                    if(usr.equals(rs.getString("username")) && pass.equals(rs.getString("password"))){ 
                        loggedIn = true; name = rs.getString("name"); logInProc();break;
                    }
                }
                if(!loggedIn){ JOptionPane.showMessageDialog(rootPane, "Invalid username or password"); }
            }
            catch(Exception ex){}
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        usr = jTextField1.getText(); pass = jPasswordField1.getText();
        if(usr.length() > 0 && pass.length() > 0){
            rs = db.fetchTable("username", "users");
            try{
                boolean exists = false;
                while(rs.next()){ if(usr.equals(rs.getString("username"))){ exists = true; break; } }
                if(exists){ JOptionPane.showMessageDialog(rootPane, "Username already exists"); }
                else{
                    name = JOptionPane.showInputDialog("Enter your name");
                    if(name != null){
                        db.insert("INSERT INTO users (username, password, name) VALUES ('"+usr+"', '"+pass+"', '"+name+"')");
                        logInProc();
                    }
                }
            }
            catch(Exception ex){}
        }        
    }//GEN-LAST:event_jButton2ActionPerformed

    public boolean isDateTimeCorrect(String date, String time){
        try{
            DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm"); f.setLenient(false);
            Date req = f.parse(date+" "+time); 
            
            Date today = new Date();
            if(req.before(today)){ JOptionPane.showMessageDialog(rootPane, "Input Date-Time is in past"); return false; }
            
            return true;
        }
        catch(Exception ex){ JOptionPane.showMessageDialog(rootPane, "Invalid Date Time, Check tooltip"); }
        return false;
    }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        src = jComboBox1.getSelectedItem().toString(); dst = jComboBox2.getSelectedItem().toString();
        type = jComboBox3.getSelectedItem().toString(); Cap = Integer.parseInt(jSpinner1.getValue().toString());
        date = jTextField3.getText(); time = jTextField4.getText();
        isShared = jCheckBox1.isSelected();
        
        jTextField2.setText("Not Available"); jButton3.setEnabled(false);
        
        if(!src.equals(dst) && date.length() > 0 && time.length() > 0 && Cap > 0 && isDateTimeCorrect(date, time)){
            try{

                Calendar cal1 = Calendar.getInstance(), cal2 = Calendar.getInstance();
                String[] tempD = date.split("-"), tempT = time.split(":"); 
                int y = Integer.parseInt(tempD[0]), M = Integer.parseInt(tempD[1]), d = Integer.parseInt(tempD[2]);
                int h = Integer.parseInt(tempT[0]), m = Integer.parseInt(tempT[1]);
                cal1.set(y, M, d, h, m, 0);
                
                long t1 = cal1.getTimeInMillis()/60000;
                
                found = "";
                int N = 0;
                
                rs = db.fetchTable("id, type, capacity, x", "cabs", "WHERE capacity > "+Cap);
                while(rs.next()){
                    String id = rs.getString("id"), _type = rs.getString("type");
                    int I = Integer.parseInt(rs.getString("capacity"));
                    
                    if(type.equals(_type) && !rs.getString("x").equals("-1")){
                        ResultSet rS = db.fetchTable("*", "cab_"+id);
                        rS.last(); N = rS.getRow(); rS.beforeFirst();
                        if(N == 0){ found = id; break; }
                        
                        while(rS.next()){
                            String s = rS.getString("time"); tempD = s.split(" ")[0].split("-"); tempT = s.split(" ")[1].split(":");
                            
                            y = Integer.parseInt(tempD[0]); M = Integer.parseInt(tempD[1]); d = Integer.parseInt(tempD[2]);
                            h = Integer.parseInt(tempT[0]); m = Integer.parseInt(tempT[1]);
                            cal2.set(y, M, d, h, m, 0);
                            
                            long t2 = cal2.getTimeInMillis()/60000;
                            int i =  Integer.parseInt(rS.getString("passengers"));
                            
                            if(t2 > t1 + Max || t2 < t1 - Max){ found = id; break; }
                            if(isShared && t2 == t1 && I >= (Cap + i) && rS.getString("src").equals(src) && rS.getString("dst").equals(dst) && rS.getString("shared").equals("1")){ found = id; Cap += i; break; }
                        }
                    }
                    if(found.length() > 0){ break; }
                }
                if(found.length() > 0){ jButton3.setEnabled(true); jTextField2.setText("Available"); }
            }
            catch(Exception ex){}
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(found.length() > 0 && !isShared){
            db.execute("INSERT INTO cab_"+found+" (src, dst, time, passengers, shared, temp, user) VALUES ('"+src+"', '"+dst+"', '"+date+" "+time+":00"+"', "+Cap+", '0', '#', '"+usr+"')");
            jTextField2.setText("Cab - "+found+" will arrive at "+src+" on "+date+" "+time); 
        }else{
            db.execute("UPDATE cab_"+found+" SET passengers="+Cap+" WHERE src='"+src+"' AND dst='"+dst+"' AND time='"+date+" "+time+":00'");
            jTextField2.setText("Cab - "+found+" will arrive at "+src+" on "+date+" "+time);            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void logInProc(){
         jButton2.setEnabled(false); jTextField1.setEditable(false); 
         jPasswordField1.setEditable(false); jButton1.setEnabled(false); 
         jPasswordField1.setText(""); jTextField3.setEnabled(true); jTextField4.setEnabled(true);
         
         jComboBox1.setEnabled(true); jComboBox2.setEnabled(true); jComboBox3.setEnabled(true);
         jSpinner1.setEnabled(true); jButton4.setEnabled(true);
         
         try{
             rs = db.fetchTable("name", "landmarks");
             while(rs.next()){
                 String s = rs.getString("name"); jComboBox1.addItem(s); jComboBox2.addItem(s);
             }
             
             ArrayList<String> L = new ArrayList<String>();
             rs = db.fetchTable("type, x", "cabs");
             while(rs.next()){
                 String s = rs.getString("type");
                 if(L.indexOf(s) == -1 && !rs.getString("x").equals("-1")){ L.add(s); jComboBox3.addItem(s); }
             }
             
             int i = 0, N;
             rs = db.fetchTable("length", "roads", " ORDER BY length DESC");
             rs.last(); N = rs.getRow(); rs.beforeFirst();
             while(rs.next() && (i < N/2)){
                 i++; Max += Integer.parseInt(rs.getString("length"));
             }
         }
         catch(Exception ex){}
    }
    
    public static void main(String args[]) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ex){}
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
