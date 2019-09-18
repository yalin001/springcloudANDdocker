package com.pd;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pd.pojo.PdOrder;
import com.pd.service.OrderService;
import com.rabbitmq.client.Channel;

@Component
public class OrderConsumer {
    //收到订单数据后,会调用订单的业务代码,把订单保存到数据库
	@Autowired
	private OrderService orderService;

    //添加该注解后,会从指定的orderQueue接收消息,
    //并把数据转为 PdOrder 实例传递到此方法
	@RabbitListener(queues="orderQueue")
	public void save(PdOrder pdOrder, Channel channel, Message message)
	{
		System.out.println("消费者");
		System.out.println(pdOrder.toString());
		try {
			orderService.saveOrder(pdOrder);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}