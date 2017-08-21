package com.rbs.scf.payments.model.core;

import org.json.JSONArray;
import org.json.JSONObject;
import com.rbs.scf.payments.model.beans.*;
import com.rbs.scf.payments.utils.*;
import com.rbs.scf.payments.model.dao.PaymentsImpl;
public class Payment {
	
	public boolean createTransaction(Customer_Transaction txn)
	{
		txn.setStatus(Constants.AWAITING_APPROVAL);
		txn.setAml_status(Constants.AML_NOT_DONE);
		try{
			PaymentsImpl p = new PaymentsImpl();
			p.addCustomer_Transaction(txn);
			return true;
		}
		catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
	
	
	public JSONArray getAllTransactions()
	{
		try {
			PaymentsImpl p = new PaymentsImpl();
			Customer_Transaction[] arrayOfTxn = p.getAllCustomerTransactionDetails();
			JSONArray toReturn = new JSONArray();
			for( Customer_Transaction txn:arrayOfTxn)
			{
				JSONObject newObj = new JSONObject();
				newObj.put("sender", txn.getPayer_id());
				newObj.put("amount", txn.getAmount());
				newObj.put("beneficiary", txn.getPayer_id());
				newObj.put("date", txn.getTransaction_date());
				newObj.put("details", txn.getComments());
				newObj.put("comments", txn.getComments());
				newObj.put("aml_status", txn.getAml_status());
				newObj.put("status", txn.getStatus());
				newObj.put("message_code", txn.getMessage_code());
				newObj.put("transaction_id", txn.getTransaction_id());
				
				toReturn.put(newObj);
				
			}
			
			
			return toReturn;
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			JSONArray newArray = new JSONArray();
			return newArray;
		}
	}
	
	
	
	
	public JSONObject getTransaction(int transactionId)
	{
		try {
			PaymentsImpl p = new PaymentsImpl();
			Customer_Transaction txn = p.getCustomerTransactionDetails(transactionId);
			
			JSONObject newObj = new JSONObject();
			newObj.put("sender", txn.getPayer_id());
			newObj.put("amount", txn.getAmount());
			newObj.put("beneficiary", txn.getPayer_id());
			newObj.put("date", txn.getTransaction_date());
			newObj.put("details", txn.getComments());
			newObj.put("comments", txn.getComments());
			newObj.put("aml_status", txn.getAml_status());
			newObj.put("status", txn.getStatus());
			newObj.put("message_code", txn.getMessage_code());
			newObj.put("transaction_id", txn.getTransaction_id());
				
		
			
			
			return newObj;
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			JSONObject newObj = new JSONObject();
			return newObj;
		}
	}
}
