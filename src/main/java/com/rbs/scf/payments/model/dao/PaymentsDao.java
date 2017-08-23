package com.rbs.scf.payments.model.dao;

import java.sql.Date;

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

//to extract details of pending transactions
Customer_Transaction[] getAllPendingCustomerTransactionDetails(String status);
Bank_to_Customer[] getAllPendingBankToCustomerDetails(String status);
Customer_to_Bank[] getAllPendingCustomerToBankDetails(String status);

//extract transactions based on payerId
Customer_Transaction[] getCustomerTransactionDetailsbyPayerId(String sid);
Bank_to_Customer[] getBankToCustomerDetailsbyPayerId(String sid);
Customer_to_Bank[] getCustomerToBankDetailsbyPayerId(String sid);

//extract transactions based on payeeId
Customer_Transaction[] getCustomerTransactionDetailsbyPayeeId(String sid);
Bank_to_Customer[] getBankToCustomerDetailsbyPayeeId(String sid);
Customer_to_Bank[] getCustomerToBankDetailsbyPayeeId(String sid);

//extract transactions based on date
Customer_Transaction[] getCustomerTransactionDetailsbyDate(Date d);
Bank_to_Customer[] getBankToCustomerDetailsbyDate(Date d);
Customer_to_Bank[] getCustomerToBankDetailsbyDate(Date d);
}
