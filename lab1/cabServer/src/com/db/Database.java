package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    
    public Connection conn;
    public ResultSet rs;
    public String query;
    
    public Database(String DB, String usr, String pass){
        try{ 
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB, usr, pass);
           
            if(conn != null){ 
                
                System.out.println("Connected"); 
            }
        }
        catch(Exception ex){ System.out.println(ex); }
    }
    
    public void close(){
        try{
             conn.close();
        }
        catch(Exception ex){ System.out.println(ex); }
    }
    
    public ResultSet fetchTable(String col, String table){
        try{
            Statement stm = conn.createStatement();
            query = "SELECT "+col+" FROM "+table;
            return stm.executeQuery(query);
        }
        catch(Exception ex){ System.out.println(ex); }
        return null;
    }
    
    
    public void insert(String query){
        try{ Statement stm = conn.createStatement(); stm.executeUpdate(query); stm.close(); }
        catch(Exception ex){ System.out.println(ex); }        
    }
    
    public void execute(String query){  // Delete, Drop, Create, Update
        try{ Statement stm = conn.createStatement(); stm.execute(query); stm.close(); }
        catch(Exception ex){ System.out.println(ex); }             
    }
}
