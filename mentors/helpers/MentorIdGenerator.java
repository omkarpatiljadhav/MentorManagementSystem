package com.nit.mentors.helpers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.nit.mentors.mentorDAO.DBQueries;

public final class MentorIdGenerator {
	
	

	private MentorIdGenerator(){}
	
	public static String getIdOfMentor()
	{
		String id="NITH";
		try 
		{
			
			Connection connection= DBConnection.getConnection();
			PreparedStatement stm = connection.prepareStatement(DBQueries.idAccessQuery);
			ResultSet rs = stm.executeQuery();
			if(rs.next())
			{
				
				
				id=id.concat(String.valueOf(rs.getInt(1)));
				//System.out.println(id);
			}
			else
			{
				System.err.println("id not generated");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return id;
	}

}
