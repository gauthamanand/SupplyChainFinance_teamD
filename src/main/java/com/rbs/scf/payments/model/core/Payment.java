package com.rbs.scf.payments.model.core;

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
	
}
