package com.rbs.scf.payments.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ConsumeRestService {

	public String getInvoice(int invoiceId)
	{
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8181/payments/services/Stubs/GetInvoice?InvoiceId="+String.valueOf(invoiceId));

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			System.out.println("Output from Server .... \n");
			System.out.println(output);
			return output;

		  } catch (Exception e) {

			e.printStackTrace();
		  }
		return "{}";

	}
}
