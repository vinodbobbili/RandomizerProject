package com.hcl.service;

import java.util.Random;

import com.hcl.dto.QueueInfoDTO;
import com.hcl.utils.QueueUtil;


public class Randomizer {
	public static void main(String args[])
	{
		try {
			QueueInfoDTO  queueInfoDTO=new QueueInfoDTO();
			queueInfoDTO.setBrokerName("tcp://localhost:61616");
			Random rand = new Random();
			int  randomNumber = rand.nextInt(100) + 1;
			queueInfoDTO.setMessage(""+randomNumber);
			queueInfoDTO.setQueueName("randomRequestQueue");
			QueueUtil.getInstance().sentToQueue(queueInfoDTO);
		} catch (Exception e) {
			System.out.println("Exception in Prime"+e);
		}
	}
}
