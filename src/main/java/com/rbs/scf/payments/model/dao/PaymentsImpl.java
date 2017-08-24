package com.rbs.scf.payments.model.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import com.rbs.scf.payments.model.dbconn.ConnectionClass;
import com.rbs.scf.payments.model.beans.*;

public class PaymentsImpl implements PaymentsDao {
	ConnectionClass c;
	public PaymentsImpl()
	{
		c=new ConnectionClass();
	}
	@Override
	public Bank[] getAllBankDetails() {
		
		try {
		Connection con=c.getConnection();
		Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		//System.out.println("Hello");
		ResultSet rs1=stmt1.executeQuery("select * from bank"); 
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		//System.out.println("Hello"+count);
		rs1.beforeFirst();
		//System.out.println("Hello"+count);
		Bank b[]=new Bank[count];
		//System.out.println("Hello"+count);
		while(rs1.next())  
		{	
			Map<String,String> m=new HashMap<String,String>();
			String s1=rs1.getString(1);
			PreparedStatement stmt=con.prepareStatement("select * from nostro_accounts where swift_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setString(1, s1);
			System.out.println("Hello"+count);
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next())
			{
					m.put(rs.getString(2), rs.getString(3));
				
			}
			Bank b1=new Bank(rs1.getString(1),rs1.getString(2),rs1.getInt(3),rs1.getString(4),m,rs1.getString(5),rs1.getString(6),rs1.getDate(7),rs1.getString(8),rs1.getInt(9),rs1.getString(10));
			b[i++]=b1;
		}
		con.close();
		return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
	}

	@Override
	public Customer_Transaction[] getAllCustomerTransactionDetails() {
		try {
			Connection con=c.getConnection();
			Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);  
			ResultSet rs1=stmt1.executeQuery("select * from transaction");
			int i=0,count=0;
			//System.out.println("Hello"+count);
			while (rs1.next())
			{
			    // Process the row.
			    count++;
			}
			rs1.beforeFirst();
			Customer_Transaction b[]=new Customer_Transaction[count];
			while(rs1.next())  
			{	
				int s1=rs1.getInt(1);
				PreparedStatement stmt=con.prepareStatement("select * from customer_transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE); 
				stmt.setInt(1, s1);
				System.out.println("Hello"+count);
				ResultSet rs=stmt.executeQuery();
				if( rs.first()){
					 
				Customer_Transaction t1=new Customer_Transaction(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
				b[i++]=t1;}
			}
			con.close();return b;
			}
			catch(Exception e){ System.out.println(e);}  
			return null;
	}

	@Override
	public Swift[] getAllSwiftDetails() {
		try {
			Connection con=c.getConnection();
			Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);  
			ResultSet rs1=stmt1.executeQuery("select * from swift"); 
			int i=0,count=0;
			//System.out.println("Hello"+count);
			while ( rs1.next() )
			{
			    // Process the row.
			    count++;
			}
			rs1.beforeFirst();
			Swift b[]=new Swift[count];
			while(rs1.next())  
			{	System.out.println("Hello"+count);
				Swift t1=new Swift(rs1.getString(1),rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs1.getString(11),rs1.getString(12),rs1.getString(13),rs1.getString(14),rs1.getString(15));
				b[i++]=t1;
			}
			con.close();return b;
			}
			catch(Exception e){ System.out.println(e);}  
			return null;
	}

	

	@Override
	public Bank_to_Customer[] getAllBankToCustomerDetails() {
		try {
			Connection con=c.getConnection();
			Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);  
			ResultSet rs1=stmt1.executeQuery("select * from transaction"); 
			int i=0,count=0;
			//System.out.println("Hello"+count);
			while ( rs1.next() )
			{
			    // Process the row.
			    count++;
			}
			rs1.beforeFirst();
			Bank_to_Customer b[]=new Bank_to_Customer[count];
			while(rs1.next())  
			{	
				int s1=rs1.getInt(1);
				PreparedStatement stmt=con.prepareStatement("select * from bank_to_customer where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE); 
				stmt.setInt(1, s1);
				ResultSet rs=stmt.executeQuery();
				System.out.println("Hello"+count);
				if( rs.first()){
				Bank_to_Customer t1=new Bank_to_Customer(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
				b[i++]=t1;}
			}
			con.close();return b;
			}
			catch(Exception e){ System.out.println(e);}  
			return null;
	}

	@Override
	public Customer_to_Bank[] getAllCustomerToBankDetails() {
		try {
			Connection con=c.getConnection();
			Statement stmt1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);  
			ResultSet rs1=stmt1.executeQuery("select * from transaction"); 
			int i=0,count=0;
			//System.out.println("Hello"+count);
			while ( rs1.next() )
			{
			    // Process the row.
			    count++;
			}
			rs1.beforeFirst();
			Customer_to_Bank b[]=new Customer_to_Bank[count];
			while(rs1.next())  
			{	
				int s1=rs1.getInt(1);
				PreparedStatement stmt=con.prepareStatement("select * from customer_to_bank where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE); 
				stmt.setInt(1, s1);
				ResultSet rs=stmt.executeQuery();
				System.out.println("Hello"+count);
				if( rs.first()){
				Customer_to_Bank t1=new Customer_to_Bank(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
				b[i++]=t1;}
			}
			con.close();return b;
			}
			catch(Exception e){ System.out.println(e);}  
			return null;
	}

@Override
public Bank getBankDetails(String swift_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from bank where swift_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE); 
		stmt1.setString(1, swift_id);
		//System.out.println("Hello");
		Bank b1=null;
		ResultSet rs1=stmt1.executeQuery(); 
		while(rs1.next())  
		{	
			Map<String,String> m=new HashMap<String,String>();
			String s1=rs1.getString(1);
			PreparedStatement stmt=con.prepareStatement("select * from nostro_accounts where swift_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setString(1, s1);
			ResultSet rs=stmt.executeQuery();
			
			while(rs.next())
			{
					m.put(rs.getString(2), rs.getString(3));
				
			}
			b1=new Bank(rs1.getString(1),rs1.getString(2),rs1.getInt(3),rs1.getString(4),m,rs1.getString(5),rs1.getString(6),rs1.getDate(7),rs1.getString(8),rs1.getInt(9),rs1.getString(10));
			
		}
		con.close();
		return b1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Swift getSwiftDetails(int transaction_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from swift where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, transaction_id);
		ResultSet rs1=stmt1.executeQuery(); 
		Swift t1=null;
		
		while(rs1.next())  
		{	
			t1=new Swift(rs1.getString(1),rs1.getInt(2),rs1.getString(3),rs1.getString(4),rs1.getString(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs1.getString(9),rs1.getString(10),rs1.getString(11),rs1.getString(12),rs1.getString(13),rs1.getString(14),rs1.getString(15));
			
		}
		con.close();return t1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_Transaction getCustomerTransactionDetails(int transaction_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, transaction_id);
		ResultSet rs1=stmt1.executeQuery();
		
		Customer_Transaction t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_Transaction(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
			}
		}
		con.close();return t1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer getBankToCustomerDetails(int transaction_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, transaction_id);
		ResultSet rs1=stmt1.executeQuery();
		
		Bank_to_Customer t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from bank_to_customer where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Bank_to_Customer(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
			}
		}
		con.close();return t1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank getCustomerToBankDetails(int transaction_id) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, transaction_id);
		ResultSet rs1=stmt1.executeQuery();
		
		Customer_to_Bank t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_to_bank where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_to_Bank(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
			}
		}
		con.close();return t1;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public boolean addBank(Bank b) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into bank values(?,?,?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, b.getSwift_id());
		stmt1.setString(2, b.getBank_name());
		stmt1.setInt(3, b.getReg_no());
		stmt1.setString(4, b.getAcc_no());
		stmt1.setString(5, b.getCategory());
		stmt1.setString(6, b.getDir_name());
		stmt1.setDate(7, b.getD());
		stmt1.setString(8, b.getAddress());
		stmt1.setInt(9, b.getContact());
		stmt1.setString(10, b.getPan_no());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			
			for ( Map.Entry<String, String> entry : b.getNostro_accounts().entrySet()) {
			    String key = entry.getKey();
			    String tab = entry.getValue();
			    PreparedStatement stmt=con.prepareStatement("insert into nostro_accounts values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				stmt.setString(1, b.getSwift_id());
				stmt.setString(2, key);
				stmt.setString(3, tab);
				int rs=stmt.executeUpdate();
				if(rs==1)
				{
					s=true;
				}
			}
		}
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean addSwift(Swift s) {
	boolean s1=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into swift values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, s.getMessage_code());
		stmt1.setInt(2, s.getTransaction_id());
		stmt1.setString(3, s.getSender());
		stmt1.setString(4, s.getReceiver());
		stmt1.setString(5, s.getMessage_text());
		stmt1.setString(6, s.getBank_operation_code());
		stmt1.setString(7, s.getSender_ref());
		stmt1.setString(8, s.getInterbank_settled_amount());
		stmt1.setString(9, s.getInstructed_amount());
		stmt1.setString(10,s.getOrdering_customer());
		stmt1.setString(11, s.getBeneficiary_customer());
		stmt1.setString(12,s.getSender_correspondent());
		stmt1.setString(13, s.getReceiver_correspondent());
		stmt1.setString(14, s.getRemit_info());
		stmt1.setString(15, s.getDetails_of_charges());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
					s1=true;
			}
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s1;
}
@Override
public boolean addCustomer_Transaction(Customer_Transaction ct) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into transaction values(?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, ct.getTransaction_id());
		stmt1.setString(2, ct.getMessage_code());
		stmt1.setString(3, ct.getCurrency_code());
		stmt1.setDouble(4, ct.getAmount());
		stmt1.setDate(5, ct.getTransaction_date());
		stmt1.setString(6, ct.getAml_status());
		stmt1.setString(7, ct.getStatus());
		stmt1.setString(8, ct.getComments());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    PreparedStatement stmt=con.prepareStatement("insert into customer_transaction values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, ct.getTransaction_id());
				stmt.setString(2,ct.getPayer_id());
				stmt.setString(3, ct.getPayee_id());
				int rs=stmt.executeUpdate();
				if(rs==1)
				{
					s=true;
				}
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean addBank_to_Customer(Bank_to_Customer btc) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into transaction values(?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, btc.getTransaction_id());
		stmt1.setString(2, btc.getMessage_code());
		stmt1.setString(3, btc.getCurrency_code());
		stmt1.setDouble(4, btc.getAmount());
		stmt1.setDate(5, btc.getTransaction_date());
		stmt1.setString(6, btc.getAml_status());
		stmt1.setString(7, btc.getStatus());
		stmt1.setString(8, btc.getComments());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    PreparedStatement stmt=con.prepareStatement("insert into bank_to_customer values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, btc.getTransaction_id());
				stmt.setString(2,btc.getPayer_id());
				stmt.setString(3, btc.getPayee_id());
				int rs=stmt.executeUpdate();
				if(rs==1)
				{
					s=true;
				}
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean addCustomer_to_Bank(Customer_to_Bank ctb) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("insert into transaction values(?,?,?,?,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setInt(1, ctb.getTransaction_id());
		stmt1.setString(2, ctb.getMessage_code());
		stmt1.setString(3, ctb.getCurrency_code());
		stmt1.setDouble(4, ctb.getAmount());
		stmt1.setDate(5, ctb.getTransaction_date());
		stmt1.setString(6, ctb.getAml_status());
		stmt1.setString(7, ctb.getStatus());
		stmt1.setString(8, ctb.getComments());
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    PreparedStatement stmt=con.prepareStatement("insert into customer_to_bank values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
		                ResultSet.CONCUR_UPDATABLE);
				stmt.setInt(1, ctb.getTransaction_id());
				stmt.setString(2,ctb.getPayer_id());
				stmt.setString(3, ctb.getPayee_id());
				int rs=stmt.executeUpdate();
				if(rs==1)
				{
					s=true;
				}
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}


@Override
public Customer_Transaction[] getAllPendingCustomerTransactionDetails(String status) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where status=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, status);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while (rs1.next())
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_Transaction b[]=new Customer_Transaction[count];
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			System.out.println("Hello"+count);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			Customer_Transaction t1=new Customer_Transaction(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
			b[i++]=t1;}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer[] getAllPendingBankToCustomerDetails(String status) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where status=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, status);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Bank_to_Customer b[]=new Bank_to_Customer[count];
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from bank_to_customer where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			System.out.println("Hello"+count);
			if( rs.first()){
			Bank_to_Customer t1=new Bank_to_Customer(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
			b[i++]=t1;}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank[] getAllPendingCustomerToBankDetails(String status) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where status=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, status);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_to_Bank b[]=new Customer_to_Bank[count];
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_to_bank where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			System.out.println("Hello"+count);
			if( rs.first()){
			Customer_to_Bank t1=new Customer_to_Bank(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
			b[i++]=t1;}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}

@Override
public Customer_Transaction[] getCustomerTransactionDetailsbyPayerId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from customer_transaction where payer_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_Transaction b[]=new Customer_Transaction[count];
		Customer_Transaction t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_Transaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer[] getBankToCustomerDetailsbyPayerId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from bank_to_customer where payer_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Bank_to_Customer b[]=new Bank_to_Customer[count];
		Bank_to_Customer t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Bank_to_Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank[] getCustomerToBankDetailsbyPayerId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from customer_to_bank where payer_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_to_Bank b[]=new Customer_to_Bank[count];
		Customer_to_Bank t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_to_Bank(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_Transaction[] getCustomerTransactionDetailsbyPayeeId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from customer_transaction where payee_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_Transaction b[]=new Customer_Transaction[count];
		Customer_Transaction t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_Transaction(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer[] getBankToCustomerDetailsbyPayeeId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from bank_to_customer where payee_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Bank_to_Customer b[]=new Bank_to_Customer[count];
		Bank_to_Customer t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Bank_to_Customer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank[] getCustomerToBankDetailsbyPayeeId(String sid) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from customer_to_bank where payee_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setString(1, sid);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_to_Bank b[]=new Customer_to_Bank[count];
		Customer_to_Bank t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_to_Bank(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getDate(5),rs.getString(6),rs.getString(7),rs.getString(8),rs1.getString(2),rs1.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_Transaction[] getCustomerTransactionDetailsbyDate(Date d) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_date=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setDate(1, d);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_Transaction b[]=new Customer_Transaction[count];
		Customer_Transaction t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_transaction where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_Transaction(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Bank_to_Customer[] getBankToCustomerDetailsbyDate(Date d) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_date=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setDate(1, d);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Bank_to_Customer b[]=new Bank_to_Customer[count];
		Bank_to_Customer t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from bank_to_customer where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Bank_to_Customer(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public Customer_to_Bank[] getCustomerToBankDetailsbyDate(Date d) {
	try {
		Connection con=c.getConnection();
		PreparedStatement stmt1=con.prepareStatement("select * from transaction where transaction_date=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);  
		stmt1.setDate(1, d);
		ResultSet rs1=stmt1.executeQuery();
		int i=0,count=0;
		//System.out.println("Hello"+count);
		while ( rs1.next() )
		{
		    // Process the row.
		    count++;
		}
		rs1.beforeFirst();
		Customer_to_Bank b[]=new Customer_to_Bank[count];
		Customer_to_Bank t1=null;
		while(rs1.next())  
		{	
			int s1=rs1.getInt(1);
			PreparedStatement stmt=con.prepareStatement("select * from customer_to_bank where transaction_id=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE); 
			stmt.setInt(1, s1);
			ResultSet rs=stmt.executeQuery();
			if( rs.first()){
				 
			t1=new Customer_to_Bank(rs1.getInt(1),rs1.getString(2),rs1.getString(3),rs1.getDouble(4),rs1.getDate(5),rs1.getString(6),rs1.getString(7),rs1.getString(8),rs.getString(2),rs.getString(3));
			b[i++]=t1;
			}
		}
		con.close();return b;
		}
		catch(Exception e){ System.out.println(e);}  
		return null;
}
@Override
public boolean addSanctionedCountry(String code,String country) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		Statement s1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rss=s1.executeQuery("select count(*) from sanc_countries");
		int count=0;
		while(rss.next()) {
			count=rss.getInt(1);
		}
		count+=1;
		PreparedStatement stmt1=con.prepareStatement("insert into sanc_countries values(?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE); 
		stmt1.setInt(1, count);
		stmt1.setString(2, country);
		stmt1.setString(3, code);
		
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    s=true;
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean addSanctionedName(String name) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		Statement s1=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		ResultSet rss=s1.executeQuery("select count(*) from sanc_users");
		int count=0;
		while(rss.next()) {
			count=rss.getInt(1);
		}
		count+=1;
		PreparedStatement stmt1=con.prepareStatement("insert into sanc_users values(?,?)",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE); 
		stmt1.setInt(1, count);
		stmt1.setString(2, name);
		
		int rs1=stmt1.executeUpdate();
		System.out.println(rs1);
		
		if(rs1==1)  
		{	
			    s=true;
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean isCountrySanctioned(String code) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement s1=con.prepareStatement("select count(*) from sanc_countries where code=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		s1.setString(1, code);
		ResultSet rss=s1.executeQuery();
		int rs1=0;
		while(rss.next()) {
		rs1=rss.getInt(1);
		}
		if(rs1!=0)  
		{	
			    s=true;
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
@Override
public boolean isPersonSanctioned(String name) {
	boolean s=false;
	try {
		Connection con=c.getConnection();
		PreparedStatement s1=con.prepareStatement("select count(*) from sanc_users where name=?",ResultSet.TYPE_SCROLL_SENSITIVE, 
                ResultSet.CONCUR_UPDATABLE);
		s1.setString(1, name);
		ResultSet rss=s1.executeQuery();
		int rs1=0;
		while(rss.next()) {
		rs1=rss.getInt(1);
		}
		
		if(rs1!=0)  
		{	
			    s=true;
			}
		
		con.close();
		}
		catch(Exception e){ System.out.println(e);}  
	return s;
}
public static void main(String[] args) throws ParseException {
	PaymentsImpl p=new PaymentsImpl();
	Map<String,String> m=new HashMap<String,String>();
	m.put("val1", "123");
	m.put("val2", "87");
	java.sql.Date sqlDate = java.sql.Date.valueOf("2017-11-11");
	//Bank myBank=new Bank("hh","aakriti",1,"1234",m,"as","sa",sqlDate,"er",234,"yu");
	//System.out.println(p.addBank(myBank));
	//Customer_Transaction ct=new Customer_Transaction(8,"abcd","f",67.5,sqlDate,"a","pending","c","d","e");
	//System.out.println(p.addCustomer_Transaction(ct));
	//Customer_Transaction ct1[]=p.getAllPendingCustomerTransactionDetails("pening");
	//System.out.println(ct1[0].getTransaction_id());
}


}
