package com.rbs.scf.payments.model.dao;

import com.rbs.scf.payments.model.beans.*;

public interface PaymentsDao {
//return all details
	Bank[] getAllBankDetails();
	Swift[] getAllSwiftDetails();
	Customer_Transaction[] getAllCustomerTransactionDetails();
	Bank_to_Customer[] getAllBankToCustomerDetails();
	Customer_to_Bank[] getAllCustomerToBankDetails();
	//return row corresponding to a key
	Bank getBankDetails(String swift_id);
	Swift getSwiftDetails(int transaction_id);
	Customer_Transaction getCustomerTransactionDetails(int transaction_id);
	Bank_to_Customer getBankToCustomerDetails(int transaction_id);
	Customer_to_Bank getCustomerToBankDetails(int transaction_id);
	//inserting a row to database
	boolean addBank(Bank b);
	boolean addSwift(Swift s);
	boolean addCustomer_Transaction(Customer_Transaction ct);
	boolean addBank_to_Customer(Bank_to_Customer btc);
	boolean addCustomer_to_Bank(Customer_to_Bank ctb);

}
