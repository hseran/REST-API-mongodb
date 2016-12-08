/**
 * 
 */
package com.octus.rest;

import java.net.UnknownHostException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mongodb.MongoException;
import com.octus.exception.InvalidDataException;
import com.octus.exception.ResourceMissingException;
import com.octus.model.Applicant;
import com.octus.service.ApplicantService;

/**
 * @author naresh
 *
 */
@Path("/applicants")
public class ApplicantAPI {

	static ApplicantService service;
	
	static{
		try {
			service = new ApplicantService();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public ApplicantAPI() {
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addApplicant(Applicant applicant) {
		try {
			service.addApplicant(applicant);
		} catch (InvalidDataException ie) {

		} catch (MongoException.DuplicateKey e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Applicant with given login exists already")
					.build();
		}
		return Response.ok(applicant.getLogin()).build();
	}

	@PUT
	@Path("/{login}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editApplicant(@PathParam("login") String login, Applicant applicant) {
		if (!login.equals(applicant.getLogin())){
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Invalid Data").build();
		}
		
		try {
			service.updateApplicant(applicant);
		} catch (MongoException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (InvalidDataException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Invalid Data").build();
		} catch (ResourceMissingException e) {
			return Response.status(Status.NOT_FOUND)
					.entity("Applicant does not exist").build();
		}
		return Response.ok(applicant.getLogin()).build();
	}

	@GET
	@Path("/{login}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApplicant(@PathParam("login") String login) {
		Applicant applicant = null;
		try {
			applicant = service.lookupApplicantByLogin(login);
		} catch (MongoException me) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		if (applicant == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok(applicant).build();
	}

	@DELETE
	@Path("/{login}")
	public Response deleteApplicant(@PathParam("login") String login) {
		try {
			service.deleteApplicant(login);
		} catch (MongoException me) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} catch (ResourceMissingException e) {
			return Response.status(Status.NOT_FOUND)
					.entity("Applicant does not exist").build();
		}

		return Response.ok().build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getApplicants() {
		List<Applicant> applicants = null;
		try {
			applicants = service.listApplicants();
		} catch (MongoException me) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok(applicants).build();
	}

}
