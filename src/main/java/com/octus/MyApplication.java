/**
 * 
 */
package com.octus;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author naresh
 *
 */
@ApplicationPath("resources")
public class MyApplication extends ResourceConfig {
	public MyApplication(){
		packages("com.octus.rest");
	}
}
