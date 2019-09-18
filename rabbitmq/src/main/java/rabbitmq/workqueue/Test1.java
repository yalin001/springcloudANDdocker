package rabbitmq.workqueue;

import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Test1 {
	public static void main(String[] args) throws Exception {
		ConnectionFactory f = new ConnectionFactory();
		f.setHost("192.168.74.135");
		f.setPort(5672);
		f.setUsername("admin");
		f.setPassword("admin");
		
		Connection c = f.newConnection();
		Channel ch = c.createChannel();
		//参数:queue,durable,exclusive,autoDelete,arguments
		//ch.queueDeclare("helloworld", true,false,false,null);
		
		//定义一个新的队列,名为 task_queue
		//第二个参数是持久化参数 durable
		ch.queueDeclare("task_queue", true, false, false, null);
		while (true) {
		    //控制台输入的消息发送到rabbitmq
			System.out.print("输入消息: ");
			String msg = new Scanner(System.in).nextLine();
			//如果输入的是"exit"则结束生产者进程
			if ("exit".equals(msg)) {
				break;
			}
			//参数:exchage,routingKey,props,body
			//ch.basicPublish("", "helloworld", null, msg.getBytes());
			//第三个参数设置消息持久化
			ch.basicPublish("", "task_queue",
			            MessageProperties.PERSISTENT_TEXT_PLAIN,
			            msg.getBytes());
			System.out.println("消息已发送: "+msg);
		}

		c.close();
	}
}