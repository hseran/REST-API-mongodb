/**
 * 
 */
package com.octus.dao;

import static org.junit.Assert.fail;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.octus.model.Applicant;

/**
 * @author naresh
 *
 */
public class ApplicantDAOTest {
	
	static ApplicantDAO appDAO;
	
	@BeforeClass
	public static void setUpBeforeClass() {	
		try {
			appDAO = new ApplicantDAO();
		} catch (UnknownHostException e) {
			// log
		}
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	public void setUp(String login) {
		appDAO.addApplicant(new Applicant(login, "hello world", "123@gmail.com"));
	}

	/**
	 * @throws java.lang.Exception
	 */
	public void tearDown(String login) {
		appDAO.deleteApplicant(login);
	}

	/**
	 * Test method for {@link org.octus.dao.ApplicantDAO#addApplicant(com.octus.model.Applicant)}.
	 */
	@Test
	public final void testAddApplicant() {
		try{
			appDAO.addApplicant(new Applicant("naresh", "naresh reddy", "nnaresh@gmail.com"));
		}
		catch (Exception e){
			fail("Add applicant failed");
		}
		finally{
			appDAO.deleteApplicant("naresh");
		}
	}
	
	/**
	 * Test method for {@link org.octus.dao.ApplicantDAO#deleteApplicant(java.lang.String)}.
	 */
	@Test
	public final void testDeleteApplicant() {
		setUp("delete");
		appDAO.deleteApplicant("delete");
	}

	/**
	 * Test method for {@link org.octus.dao.ApplicantDAO#updateApplicant(com.octus.model.Applicant)}.
	 */
	@Test
	public final void testUpdateApplicant() {
		setUp("update");
		appDAO.updateApplicant(new Applicant("update", "test", "test@test.com"));
		Applicant object = appDAO.lookupApplicantByLogin("update");
		assert("test".equals(object.getName()));
		assert("test@test.com".equals(object.getEmail()));
		tearDown("update");
	}

	/**
	 * Test method for {@link org.octus.dao.ApplicantDAO#listApplicants()}.
	 */
	@Test
	public final void testListApplicants() {
		setUp("123");
		setUp("456");
		
		List<Applicant> applicants = appDAO.listApplicants();
		
		assert(applicants!= null);
		
		Set<String> logins = new HashSet<String>();
		for (Applicant obj : applicants){
			logins.add(obj.getLogin());
		}
		
		assert(logins.contains("123"));
		assert(logins.contains("456"));
		
		tearDown("123");
		tearDown("456");
	}

	/**
	 * Test method for {@link org.octus.dao.ApplicantDAO#lookupApplicantByLogin()}.
	 */
	@Test
	public final void testlookupApplicantByLogin() {
		setUp("lookup");
		
		Applicant obj = appDAO.lookupApplicantByLogin("lookup");
		
		assert(obj != null && "lookup".equals(obj.getLogin()));
		
		tearDown("lookup");
	}
}
