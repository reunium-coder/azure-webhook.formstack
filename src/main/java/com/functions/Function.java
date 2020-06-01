/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 */

package com.functions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map.Entry;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;

import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.reunium.blog.pojos.Address;
import com.reunium.blog.pojos.Name;
import com.reunium.blog.pojos.Registration;

import static com.reunium.blog.jooq.tables.EventRegistration.EVENT_REGISTRATION;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    private Connection connection;
	private ExecutionContext context;
	private DSLContext dsl;

	/**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("HttpRegistration")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) { 
    	
    	// Setting Context field
        this.context = context;
        
        try {
        
	        this.context.getLogger().info("Java HTTP trigger processed a request.");
	        
	        // Retrieve Connection and Set DSL Context
	        getDSLContext();
	        
	        // Query parameters
	        for (Entry<String, String> entry : request.getQueryParameters().entrySet())
	        	this.context.getLogger().info(entry.getKey() + ":" + entry.getValue());
	
	        // Body
	        final String body = request.getBody().orElse(null);        
	        this.context.getLogger().info(body);
	        
	        
	        // Deserialize Body
	        ObjectMapper objectMapper = new ObjectMapper();
			Registration registration = objectMapper.readValue(body, Registration.class);	

			// JOOQ - Insert Row
			dsl.insertInto(
					EVENT_REGISTRATION, 
					EVENT_REGISTRATION.UNIQUEID, 
					EVENT_REGISTRATION.FORMID,
					EVENT_REGISTRATION.PERSONAL_INFORMATION, 
					EVENT_REGISTRATION.NAME_FIRST,
					EVENT_REGISTRATION.NAME_LAST, 
					EVENT_REGISTRATION.ADDRESS_ADDRESS, 
					EVENT_REGISTRATION.ADDRESS_CITY,
					EVENT_REGISTRATION.ADDRESS_STATE, 
					EVENT_REGISTRATION.ADDRESS_ZIP,
					EVENT_REGISTRATION.ADDRESS_COUNTRY, 
					EVENT_REGISTRATION.EMAIL, 
					EVENT_REGISTRATION.EVENT_INFORMATION,
					EVENT_REGISTRATION.WILL_YOU_BE_ATTENDING, 
					EVENT_REGISTRATION.NUMBER_ATTENDING,
					EVENT_REGISTRATION.AMOUNT, 
					EVENT_REGISTRATION.UPLOAD)
					.values(
							registration.getUniqueID(), 
							registration.getFormID(),
							registration.getPersonal_information(), 
							registration.getName().getFirst(),
							registration.getName().getLast(), 
							registration.getAddress().getAddress(),
							registration.getAddress().getCity(), 
							registration.getAddress().getState(),
							registration.getAddress().getZip(), 
							registration.getAddress().getCountry(),
							registration.getEmail(), 
							registration.getEvent_information(),
							registration.getWill_you_be_attending(), 
							registration.getNumber_attending(),
							registration.getAmount(), 
							registration.getUpload())
					.execute();
			
			// Close Connection
			closeConnection();
	        
	        if (body == null) {
	            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).body("Please pass a name on the query string or in the request body").build();
	        } else {
	            return request.createResponseBuilder(HttpStatus.OK).body("OK").build();
	        }
        } catch (Exception  e) {
        	context.getLogger().info(e.getMessage());
        	context.getLogger().info(getStackTrace(e));
        	return request.createResponseBuilder(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()).build();
        }
    }
    
    /**
     * 
     * @return
     * @throws Exception
     */
	protected void getDSLContext() throws Exception {
		
		if (this.connection == null)
			retrieveConnection();
		
		this.dsl = DSL.using(new DefaultConfiguration()
			.set(this.connection)
			.set(SQLDialect.MYSQL)
		);
		
	}    
    
    
	/**
	 * 
	 * @throws Exception
	 */
    private void retrieveConnection() throws Exception {
	    String driver = System.getenv("db.driver");
	    String url = System.getenv("db.url");
	    String userName = System.getenv("db.username");
	    String password = System.getenv("db.password");

		Class.forName(driver).newInstance();
        
        this.connection = DriverManager.getConnection(url, userName, password);    	
    }
    
    /**
     * 
     * @throws Exception
     */
    protected void closeConnection() throws Exception {
    	if (this.connection != null)
				this.connection.close();    	
    }
    
    /**
     * 
     * @param throwable
     * @return
     */
    private String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }

}
