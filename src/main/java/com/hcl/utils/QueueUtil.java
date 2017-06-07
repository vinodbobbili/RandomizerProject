package com.hcl.utils;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.hcl.dto.QueueInfoDTO;

public class QueueUtil {
	
	Connection connection = null;
	ConnectionFactory connectionFactory =null;
	Session session =null;
	private static QueueUtil queueUtil=null;
	public static QueueUtil getInstance()
	{
		if(queueUtil==null)
			queueUtil=new QueueUtil();
		return queueUtil;
	}
	
	public boolean sentToQueue(QueueInfoDTO queueInfoDTO)
	{
		
		try {
			// Producer
			connectionFactory = new ActiveMQConnectionFactory(queueInfoDTO.getBrokerName());
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(queueInfoDTO.getQueueName());
			Message msg = (Message) session.createTextMessage(queueInfoDTO.getMessage());
			MessageProducer producer = session.createProducer(queue);
			System.out.println("Sending text '" + queueInfoDTO.getMessage() + "'");
			producer.send(msg);
			// Consumer
			MessageConsumer consumer = session.createConsumer(queue);
			connection.start();
			TextMessage textMsg = (TextMessage) consumer.receive();
			System.out.println(textMsg);
			System.out.println("Received: " + textMsg.getText());
			return false;
		} catch(Exception ex)
		{
			System.out.println("sentToQueue Exception"+ex);
		}finally
		{
			try {
				if(session!=null)
				session.close();
				
				if(connection!=null)
				connection.close();
			} catch (Exception e) {
				System.out.println("Exception while closing "+e);
			}
		}
			return false;
	}
	
	public String reveiveFromQueue(QueueInfoDTO queueInfoDTO)
	{	
		try {
			//Consumer
			connectionFactory = new ActiveMQConnectionFactory(queueInfoDTO.getBrokerName());
			connection = connectionFactory.createConnection();
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue(queueInfoDTO.getQueueName());
			MessageConsumer producer = session.createConsumer(queue);
			TextMessage resp =(TextMessage)producer.receive();
			resp.getText();
			System.out.println("Received Message : " + resp.getText());
			session.close();
			return resp.getText();
		} catch(Exception ex)
		{
			System.out.println("reveiveFromQueue Exception"+ex);
		}finally
		{
			try {
				if(session!=null)
				session.close();
				
				if(connection!=null)
				connection.close();
			} catch (Exception e) {
				System.out.println("Exception while closing "+e);
			}
		}
		
	  return "";
	}

}
