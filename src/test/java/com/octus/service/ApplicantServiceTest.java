/**
 * 
 */
package com.octus.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.octus.dao.ApplicantDAO;
import com.octus.exception.InvalidDataException;
import com.octus.exception.ResourceMissingException;
import com.octus.model.Applicant;

/**
 * @author naresh
 *
 */
public class ApplicantServiceTest {

	static ApplicantService service;
	static ApplicantDAO appDAO;
	static Applicant app1, app2, app3;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		appDAO = mock(ApplicantDAO.class);
		service = new ApplicantService(appDAO);
		List<Applicant> applicants = new ArrayList<Applicant>();

		app1 = new Applicant("hseran", "naresh", "nnaresh@gmail.com");

		app2 = new Applicant("app1", "applicant1", "app1@gmail.com");
		
		app3 = new Applicant("app2", "applicant2", "app2@gmail.com");

		applicants.add(app1);
		applicants.add(app2);

		when(appDAO.listApplicants()).thenReturn(applicants);
		when(appDAO.lookupApplicantByLogin("hseran")).thenReturn(app1);
		when(appDAO.lookupApplicantByLogin("app1")).thenReturn(app2);

		when(appDAO.deleteApplicant("app1")).thenReturn(true);
		when(appDAO.updateApplicant(app1)).thenReturn(app1.getLogin());
		when(appDAO.addApplicant(app3)).thenReturn(app3.getLogin());
	}

	/**
	 * Test method for
	 * {@link org.octus.service.ApplicantService#addApplicant(com.octus.model.Applicant)}
	 * .
	 * @throws InvalidDataException 
	 */
	@Test
	public final void testAddApplicant() throws InvalidDataException {
		assert(service.addApplicant(app3) == true);
	}

	/**
	 * Test method for
	 * {@link org.octus.service.ApplicantService#deleteApplicant(java.lang.String)}
	 * .
	 */
	@Test
	public final void testDeleteApplicant() throws Exception {
		assert (service.deleteApplicant("app1") == true);
	}

	/**
	 * Test method for
	 * {@link org.octus.service.ApplicantService#updateApplicant(com.octus.model.Applicant)}
	 * .
	 * @throws ResourceMissingException 
	 * @throws InvalidDataException 
	 */
	@Test
	public final void testUpdateApplicant() throws InvalidDataException, ResourceMissingException {
		assert(service.updateApplicant(app1) == true);
	}

	/**
	 * Test method for
	 * {@link org.octus.service.ApplicantService#listApplicants()}.
	 */
	@Test
	public final void testListApplicants() {
		List<Applicant> applicants = service.listApplicants();
		assert (applicants != null);
	}

	/**
	 * Test method for
	 * {@link org.octus.service.ApplicantService#lookupApplicantByLogin(java.lang.String)}
	 * .
	 */
	@Test
	public final void testLookupApplicantByLogin() {
		Applicant applicant = service.lookupApplicantByLogin("hseran");
		assert (applicant != null && "naresh".equals(applicant.getName()));
	}

}
