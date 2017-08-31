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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.rbs.scf.payments.model.core.*;
import com.rbs.scf.payments.utils.*;
import com.rbs.scf.payments.model.beans.*;

@Path("/Transactions")

public class MainController {

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
	 * @throws JSONException 
     */
	
	@Context HttpServletRequest request;
	

    
    @GET 
    @Path("/initTransaction")
    @Produces(MediaType.APPLICATION_JSON)
    public String getInititateTransaction(@QueryParam("InvoiceId")int invoiceId) throws JSONException
    {
    		
    		ConsumeRestService consumer = new ConsumeRestService();
    		
    		
    		return consumer.getInvoice(invoiceId);
    }
    
    @POST
    @Path("/initTransaction")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String postInititateTransaction(String data) throws ParseException, JSONException{
    	
    	
    	JSONObject newObj = new JSONObject(data);
    	String senderId = newObj.getString("sender");
    	double amount = newObj.getDouble("amount");
    	String currency = newObj.getString("beneficiary");
    	String beneficiary = newObj.getString("beneficiary");
    	String accno = newObj.getString("accountNo");
    	String date = newObj.getString("date");
    	String details  = newObj.getString("details");
    	String comments = newObj.getString("comments");
    	
    	
    	Random generator= new Random();
    	int txnId = generator.nextInt(10000);
    	java.sql.Date sqlDate = java.sql.Date.valueOf(date);
    	
    	Customer_Transaction newTrans = new Customer_Transaction(txnId, "104", currency, amount, sqlDate, null, null, comments, senderId,beneficiary );
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
    
    
    @GET 
    @Path("/getAllTransaction")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllTransactions()
    {
    	Payment pay = new Payment();
    	JSONArray resultArray =  pay.getAllTransactions();
    	return resultArray.toString();
    }
    
    @GET 
    @Path("/getTransaction")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTransactions(@QueryParam("TransactionId")int txnId)
    {
    	Payment pay = new Payment();
    	JSONObject jsonObj =  pay.getTransaction(txnId);
    	return jsonObj.toString();
    }
    
    
    @POST
    @Path("/setCurrentTransaction")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String setCurrentTransaction(int data)
    {
    	HttpSession ses = request.getSession();
    	int transactionId = data;
    	Payment pay = new Payment();
    	
    	JSONObject returnObj = pay.getTransaction(transactionId);
    	ses.setAttribute("transactionObj", returnObj);
    	
    	return "Done";
    }
    
    @GET
    @Path("/getCurrentTransaction")
    @Produces(MediaType.TEXT_HTML)
    public String getCurrentTransaction()
    {
    	HttpSession ses = request.getSession();
    	return ((JSONObject)ses.getAttribute("transactionObj")).toString();
    }
   
    
    @POST
    @Path("/submitSwiftMessage")
    @Consumes(MediaType.TEXT_HTML)
    @Produces(MediaType.TEXT_HTML)
    public String submitSwiftMessage(String data) throws JSONException
    {
    	JSONObject newObj = new JSONObject(data);
    	String messageCode = newObj.getString("messageCode");
    	int transactionId = newObj.getInt("transactionId");
    	String sender = newObj.getString("sender");
    	String reciever = newObj.getString("reciever");
    	String messageTxt = newObj.getString("messageText");
    	String bankOperationCode = newObj.getString("bankOperationCode");
    	String senderRef = newObj.getString("senderRef");
    	String interbankSettledAmount = newObj.getString("interbankSettledAmount");
    	String instructedAmount = newObj.getString("instructedAmount");
    	String orderingCustomer = newObj.getString("orderingCustomer");
    	String beneficiaryCustomer = newObj.getString("beneficiaryCustomer");
    	String senderCorrespondent = newObj.getString("senderCorrespondent");
    	String recieverCorrespondent = newObj.getString("recieverCorrespondent");
    	String remitInfo = newObj.getString("remitInfo");
    	String detailsOfCharge = newObj.getString("detailsOfCharge");
    	Payment pay = new Payment();
    	
    	Swift smessage = new Swift(messageCode,transactionId,sender,reciever,messageTxt,bankOperationCode,senderRef,interbankSettledAmount,instructedAmount,orderingCustomer,beneficiaryCustomer,senderCorrespondent,recieverCorrespondent,remitInfo,detailsOfCharge);
    	
    	pay.checkAml(transactionId);
    	
    	
    	if(pay.createSwiftMessage(smessage)) {
    		return "success";
    	}
    	else return "failure";
    }
    
    
    
}


