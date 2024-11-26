package com.nit.mentors.mentorDAO;

public interface DBQueries 
{
   
   String insertMentorQuery="insert into mentor values(?,?,?,?,?,?)";
   String selectMentorQuery="select * from mentor where MENTORID=?";
   String idAccessQuery="select mentorIdGenerator.nextVal from dual";
   String updateMentorQuery="update mentor set mentorName=?,designation=?,salary=?,mobileNo=?,address=? where mentorId=?";
   String deleteMentorQuery="DELETE FROM mentor WHERE mentorId=? ";
   String selectAllMentor="select * from mentor";
}
