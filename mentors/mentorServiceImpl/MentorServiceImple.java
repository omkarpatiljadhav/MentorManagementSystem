package com.nit.mentors.mentorServiceImpl;

import java.util.List;

import com.nit.mentors.mentorDAO.MentorDAOOperation;
import com.nit.mentors.mentorService.MentorService;
import com.nit.mentors.models.Mentor;

public class MentorServiceImple implements MentorService {
	private MentorDAOOperation mentorDao;// has-a relationship

	public MentorServiceImple(MentorDAOOperation mentorDao) {
		this.mentorDao = mentorDao;
	}

	@Override
	public boolean registerMentor(Mentor mentor) {

		// business logic
		return mentorDao.insertMentor(mentor);
	}

	@Override
	public Mentor checkMentor(String mentorId) {

		// business logic
		return mentorDao.isValidMentor(mentorId);
	}

	@Override
	public boolean updateMentor(Mentor mentor) 
	{
		return mentorDao.updateMentor(mentor);
	}

	@Override
	public boolean deleteMentor(String mentorId) 
	{
		return mentorDao.deleteMentor(mentorId);
	}

	@Override
	public List<Mentor> getAllMentor() {
		return mentorDao.getAllMentor();
	}

	
	

}
