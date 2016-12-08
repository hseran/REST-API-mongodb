package com.octus.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import com.octus.model.Applicant;

public class ApplicantDAO {
	private final DBCollection applicantCollection;

	public ApplicantDAO() throws UnknownHostException {
		DB OctusDB = ConnectionUtil.getDBConnection();
		applicantCollection = OctusDB.getCollection("applicants");
	}

	/**
	 * adds applicant to collection
	 * @param applicant
	 */
	public String addApplicant(Applicant applicant) throws MongoException{

		BasicDBObject user = new BasicDBObject();

		user.append("_id", applicant.getLogin()).append("name", applicant.getName());

		user.append("email", applicant.getEmail());

		try {
			applicantCollection.insert(user);
			return applicant.getLogin();
		} catch (MongoException.DuplicateKey e) {
			throw e;
		}
	}

	/**
	 * delete applicant from collection
	 * @param login
	 */
	public boolean deleteApplicant(String login) throws MongoException{

		BasicDBObject user = new BasicDBObject();

		user.append("_id", login);

		try {
			applicantCollection.remove(user);
			return true;
		} catch (MongoException e) {
			throw e;
		}
	}
	
	/**
	 * updates applicant in collection
	 * @param applicant
	 */
	public String updateApplicant(Applicant applicant) throws MongoException{

		BasicDBObject user = new BasicDBObject();
		BasicDBObject query = new BasicDBObject();
		query.append("_id", applicant.getLogin());
		
		user.append("_id", applicant.getLogin()).append("name", applicant.getName());

		user.append("email", applicant.getEmail());

		try {
			applicantCollection.update(query, user);
			return applicant.getLogin();
		} catch (MongoException.DuplicateKey e) {
			throw e;
		}
	}

	/**
	 * returns all applicants in collection
	 * @return
	 */
	public List<Applicant> listApplicants() throws MongoException{
		List<DBObject> dbApplicants;
		DBCursor cursor = applicantCollection.find();
        try {
            dbApplicants = cursor.toArray();
        } finally {
            cursor.close();
        }
        
        List<Applicant> applicants = new ArrayList<Applicant>();
		for (DBObject obj : dbApplicants){
			applicants.add(transform(obj));
		}
        
        return applicants;	
	}	
	
	/**
	 * lookup user by login
	 * @param login
	 * @return
	 */
	public Applicant lookupApplicantByLogin(String login) throws MongoException{
		DBObject user = 
				applicantCollection.findOne(new BasicDBObject("_id", login));
		
        if (user == null) {
            System.out.println("User not in database");
            return null;
        }
        return transform(user);
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	private Applicant transform(DBObject obj){
		if (obj == null){
			return null;
		}
		
		return new Applicant(
				(String)obj.get("_id"), (String)obj.get("name"), (String)obj.get("email"));
	}
}
