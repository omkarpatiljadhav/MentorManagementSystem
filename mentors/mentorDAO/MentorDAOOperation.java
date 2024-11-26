package com.nit.mentors.mentorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nit.mentors.helpers.DBConnection;
import com.nit.mentors.models.Address;
import com.nit.mentors.models.Mentor;

public class MentorDAOOperation 
{
	 private static Connection connection=null;
	 private static int update;
	 
	 private PreparedStatement st1=null;
	 static
	 {
		 connection= DBConnection.getConnection();
		 
		 update=0;
	 }
	
	public boolean insertMentor(Mentor mentor) 
	{
		//db logic
		
		try 
		{
			Address address = mentor.getAddress();
			st1=connection.prepareStatement(DBQueries.insertMentorQuery);
			//setting values into insert query
			st1.setString(1,mentor.getMentorId());
			st1.setString(2, mentor.getMentorName());
			st1.setString(3, mentor.getDesignation());
			st1.setDouble(4, mentor.getSalary());
			st1.setLong(5, mentor.getMobileNo());
			st1.setString(6, address.getCountry()+","+address.getState()+","+address.getDistrict());
			
			//Executing insertQuery
			connection.setAutoCommit(true);
			update = st1.executeUpdate();
			connection.setAutoCommit(false);
			
		} 
		catch (Exception e) 
		{
			try 
			{
				connection.rollback();
			} 
			catch (Exception e2) 
			{
				e.printStackTrace();
			}
			
		}
		
		
		return update >=1 ? true : false ;
	}
	
	public Mentor isValidMentor(String mentorId)
	{
		ResultSet rs=null;
		Mentor mentor=null;
		Address address=null;
		
		try 
		{
			 
			 st1=connection.prepareStatement(DBQueries.selectMentorQuery);
		     
			 st1.setString(1, mentorId);
			 
			 rs=st1.executeQuery();
			 
			 if(rs.next()) 
			 {
				 
				 mentor=new Mentor();
				 mentor.setMentorId(rs.getString(1));
				 mentor.setMentorName(rs.getString(2));
				 mentor.setDesignation(rs.getString(3));
				 mentor.setSalary(rs.getDouble(4));
				 mentor.setMobileNo(rs.getLong(5));
				 
				 String add = rs.getString(6);
				 String[] split = add.split(",");
				 address=new Address(split[0],split[1],split[2]);
				 mentor.setAddress(address);
				 
				 rs.close();
			 }
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return mentor;
	}
	
	
	public boolean updateMentor(Mentor mentor)
	{
		
		try 
		{
			System.out.println(mentor);
			st1 = connection.prepareStatement(DBQueries.updateMentorQuery);	
			st1.setString(1, mentor.getMentorName());
			st1.setString(2, mentor.getDesignation());
			st1.setDouble(3, mentor.getSalary());
			st1.setLong(4, mentor.getMobileNo());
			Address add=mentor.getAddress();
			st1.setString(5, add.getCountry()+","+add.getState()+","+add.getDistrict());
			st1.setString(6, mentor.getMentorId());
			
			connection.setAutoCommit(true);
			update=st1.executeUpdate();
			System.out.println("execuated");
			connection.setAutoCommit(false);
		} 
		catch (Exception e) 
		{
			try 
			{
				connection.rollback();
			} 
			catch (SQLException e1) 
			{
				e1.printStackTrace();
			}
			
		}
		
		return update>0 ? true : false;
		
		
		
	}
	
	
	

	public boolean deleteMentor(String mentorId) 
	{
	   	try 
	   	{
			 st1=connection.prepareStatement(DBQueries.deleteMentorQuery);
			 st1.setString(1, mentorId);
			 
			 connection.setAutoCommit(true);
			 update=st1.executeUpdate();
			 connection.setAutoCommit(false);
		} 
	   	catch (Exception e) 
	   	{
			try 
			{
				connection.rollback();
			} 
			catch (Exception e2) 
			{
				e.printStackTrace();
			}
		}
	   	
	   	return update>0 ? true : false;
		
	}
	
	public List<Mentor> getAllMentor()
	{
		List<Mentor> mentorsList = null;
		try 
		{
			st1=connection.prepareStatement(DBQueries.selectAllMentor);
			
			ResultSet mentors = st1.executeQuery();
			
			if(mentors!=null)
			{
				mentorsList=new ArrayList<Mentor>();
				
				while(mentors.next())
				{
					Mentor m=new Mentor();
					m.setMentorId(mentors.getString(1));
					m.setMentorName(mentors.getString(2));
					m.setDesignation(mentors.getString(3));
					m.setSalary(mentors.getDouble(4));
					m.setMobileNo(mentors.getLong(5));
					
					String comaSeparatedAddress=mentors.getString(6);
					String[] addressInfo = comaSeparatedAddress.split(",");
					
					Address address=new Address();
					address.setCountry(addressInfo[0]);
					address.setState(addressInfo[1]);
					address.setDistrict(addressInfo[2]);
					
					m.setAddress(address);
					
					mentorsList.add(m);
					
				}
				
			}
			
			
		}	
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return mentorsList;
				
	}
	




} 
		
		
		
		
	
	
	


