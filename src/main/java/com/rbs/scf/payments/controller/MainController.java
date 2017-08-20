package com.rbs.scf.payments.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;
import com.rbs.scf.payments.model.core.*;
import com.rbs.scf.payments.utils.*;
import com.rbs.scf.payments.model.beans.*;

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
    		
    		ConsumeRestService consumer = new ConsumeRestService();
    		
    		
    		return consumer.getInvoice(invoiceId);
    }
    
    @POST
    @Path("/initTransaction")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String postInititateTransaction(@FormParam("sender")String senderId,
    		@FormParam("amount")double amount,
    		@FormParam("currency")String currency,
    		@FormParam("beneficiary")String beneficiary,
    		@FormParam("accountNo")String accno,
    		@FormParam("date")String date,
    		@FormParam("details")String details,
    		@FormParam("comments")String comments
    		) throws ParseException, JSONException{
    	
    	Random generator= new Random();
    	int txnId = generator.nextInt(10000);
    	java.sql.Date dt = Conversions.convertToSqlDate(date);
    	Customer_Transaction newTrans = new Customer_Transaction(txnId, "104", currency, amount, dt, null, null, comments, senderId,beneficiary );
    	Payment pay = new Payment();
    	JSONObject returnStatus = new JSONObject();
    	
    	if(pay.createTransaction(newTrans)){
    		returnStatus.put("status", "success");
    		return returnStatus.toString();
    	}
    	else{
    		returnStatus.put("status", "failure");
    		return returnStatus.toString();
    	}
    }
    
    
    
}


