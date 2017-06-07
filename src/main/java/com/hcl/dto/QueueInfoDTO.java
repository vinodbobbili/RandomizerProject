package com.hcl.dto;

public class QueueInfoDTO {
	
   private String brokerName;
   private String queueName;
   private String message;
public String getBrokerName() {
	return brokerName;
}
public void setBrokerName(String brokerName) {
	this.brokerName = brokerName;
}
public String getQueueName() {
	return queueName;
}
public void setQueueName(String queueName) {
	this.queueName = queueName;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

}
