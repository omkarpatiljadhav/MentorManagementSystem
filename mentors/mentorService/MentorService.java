package com.nit.mentors.mentorService;

import java.util.List;

import com.nit.mentors.models.Mentor;

public interface MentorService 
{
    public boolean registerMentor(Mentor mentor);
    public Mentor checkMentor(String mentorId);
    public boolean updateMentor(Mentor mentor);
    public boolean deleteMentor(String mentorId);
    public List<Mentor> getAllMentor();
}
