package com.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Database {
    
    public Connection conn;
    public Statement stm;
    public ResultSet rs;
    public String query;
    
    public Database(String DB, String usr, String pass){
        try{ 
            Class.forName("com.mysql.jdbc.Driver"); 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB, usr, pass);
           
            if(conn != null){ 
                stm = conn.createStatement();
                System.out.println("Connected"); 
            }
        }
        catch(Exception ex){ System.out.println(ex); }
    }
    
    public void close(){
        try{
             stm.close(); conn.close();
        }
        catch(Exception ex){ System.out.println(ex); }
    }
    
    public ResultSet fetchTable(String col, String table){
        try{
            query = "SELECT "+col+" FROM "+table;
            return stm.executeQuery(query);
        }
        catch(Exception ex){ System.out.println(ex); }
        return null;
    }
    
    public ResultSet fetchTable(String col, String table, String extra){
        try{
            query = "SELECT "+col+" FROM "+table+" "+extra;
            return stm.executeQuery(query);
        }
        catch(Exception ex){ System.out.println(ex); }
        return null;
    }
    
    public void insert(String query){
        try{ stm.executeUpdate(query); }
        catch(Exception ex){ System.out.println(ex); }        
    }
    
    public void execute(String query){  // Delete, Drop, Create, Update
        try{ stm.execute(query); }
        catch(Exception ex){ System.out.println(ex); }             
    }
}
