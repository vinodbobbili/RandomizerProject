package com.hcl.service;

import com.hcl.dto.QueueInfoDTO;
import com.hcl.utils.QueueUtil;

public class Prime {

	public static void main(String args[])
	{
		try {
				QueueInfoDTO  queueInfoDTO=new QueueInfoDTO();
				queueInfoDTO.setBrokerName("tcp://localhost:61616");
				queueInfoDTO.setQueueName("randomRequestQueue");
				//Receiving Request
				String number=QueueUtil.getInstance().reveiveFromQueue(queueInfoDTO);
				System.out.println(""+isPrime(Integer.parseInt(number)));
		    } catch (Exception e) {
			System.out.println("Exception in Randomizer"+e);
		}
	}
 private static String isPrime(int n) {
	    for(int i=2;i<n/2;i++) {
	        if(n%i==0)
	            return n+ " is not a prime number";
	    }
	    return n+" is a prime number";
	}
}
