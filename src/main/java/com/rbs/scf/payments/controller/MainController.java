package com.rbs.scf.payments.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import com.rbs.scf.payments.model.core.*;
import com.rbs.scf.payments.utils.*;


@Path("/")

public class MainController {

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
	 * @throws JSONException 
     */
	
	
	

    
    @GET 
    @Path("/initTransaction")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInititateTransaction(@QueryParam("InvoiceId")int invoiceId,@Context HttpServletRequest request) throws JSONException
    {
    		String userid;
    		HttpSession sess = request.getSession(false);
    		if(sess==null)
    		{
    			userid = "1";
    		}
    		
    		UUID newId = UUID.randomUUID();
    		ConsumeRestService consumer = new ConsumeRestService();
    		
    		
    		return consumer.getInvoice(invoiceId);
    }
    
    
    
    
}


