import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description: RabbitMQ队列消息接收者
 * @Author: zjf
 * @Date: 2019/11/27 13:50.
 */
public class Receiver {

    private static final String QUEUE_NAME="helloMQ";

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接到RabbitMQ
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //设置连接地址
        connectionFactory.setHost("127.0.0.1");

        //创建一个连接
        Connection connection = connectionFactory.newConnection();

        //创建一个频道
        Channel channel = connection.createChannel();

        //声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("等待消息");

        Consumer consumer= new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"utf-8");
                System.out.println("received"+message);
            }
        };

        channel.basicConsume(QUEUE_NAME,true,consumer);
    }
}
