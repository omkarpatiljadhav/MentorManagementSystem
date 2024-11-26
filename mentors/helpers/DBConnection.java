 package com.nit.mentors.helpers;

import java.sql.Connection;
import java.sql.DriverManager;

//Singleton class
public final class DBConnection 
{
    private DBConnection() {};
    private static Connection connection=null;
    
    public static Connection getConnection()
    {
      if(connection==null)
      {
    	  try 
    	  {
    		  Class.forName(DBInfo.DRIVER);
    		  
    		  connection=DriverManager.getConnection(DBInfo.URL,DBInfo.USERNAME,DBInfo.PASSWORD);
		     // System.out.println(connection);
    		  connection.setAutoCommit(false);
    	  } 
    	  catch (Exception e) 
    	  {
			e.printStackTrace();
		  }
    	  
    	    
      }
      
      
      return connection;
    }
}
