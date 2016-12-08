/**
 * 
 */
package com.octus.service;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.MongoException;
import com.octus.dao.ApplicantDAO;
import com.octus.exception.InvalidDataException;
import com.octus.exception.ResourceMissingException;
import com.octus.model.Applicant;

/**
 * @author naresh
 *
 */
public class ApplicantService {
	
	ApplicantDAO appDAO;
	
	public ApplicantService(ApplicantDAO dao) throws UnknownHostException{
		appDAO = dao;
	}
	
	public ApplicantService() throws UnknownHostException {
		appDAO = new ApplicantDAO();
	}
	
	public boolean addApplicant(Applicant applicant) throws InvalidDataException{
		if (!isValidApplicant(applicant)){
			throw new InvalidDataException("Applicant data incomplete");
		}
		return applicant.getLogin().equals(appDAO.addApplicant(applicant));
	}

	/**
	 * delete applicant from collection
	 * @param login
	 * @throws ResourceMissingException 
	 */
	public boolean deleteApplicant(String login) throws MongoException, ResourceMissingException{
		if (!doesApplicantExist(login)){
			throw new ResourceMissingException();
		}
		
		return appDAO.deleteApplicant(login);
	}
	
	/**
	 * 
	 * @param login
	 * @return
	 */
	private boolean doesApplicantExist(String login){
		Applicant applicant = null;
		applicant = appDAO.lookupApplicantByLogin(login);
		
		if (applicant == null){
			return false;
		}
		return true;
	}
	
	/**
	 * updates applicant in collection
	 * @param login
	 * @param name
	 * @param email
	 * @throws ResourceMissingException 
	 */
	public boolean updateApplicant(Applicant applicant) throws InvalidDataException, ResourceMissingException{
		
		if (!isValidApplicant(applicant)){
			throw new InvalidDataException();
		}
		
		if (!doesApplicantExist(applicant.getLogin())){
			throw new ResourceMissingException();
		}
		
		return applicant.getLogin().equals(appDAO.updateApplicant(applicant));
	}

	/**
	 * returns all applicants in collection
	 * @return
	 */
	public List<Applicant> listApplicants() {
		
		List<Applicant> dbApplicants = appDAO.listApplicants();

		return dbApplicants;
	}	
	
	/**
	 * lookup user by login
	 * @param login
	 * @return
	 */
	public Applicant lookupApplicantByLogin(String login){
		return (appDAO.lookupApplicantByLogin(login));
	}
	
	private boolean isValidApplicant(Applicant applicant){
		if (applicant == null){
			return false;
		}
		
		String login = applicant.getLogin();
		String name = applicant.getName();
		String email = applicant.getEmail();
		
		if (login == null || login.isEmpty() || name == null || name.isEmpty() 
				|| email == null || email.isEmpty())
		{
			return false;
		}
		
		return true;
	}
}
