import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Description: RabbitMQ消息生产者
 * @Author: zjf
 * @Date: 2019/11/20 17:08.
 */
public class Provider {

    private static final String QUEUE_NAME = "helloMQ";

    public static void main(String[] args) throws IOException, TimeoutException {

        //创建连接到RabbitMQ
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //设置连接地址
        connectionFactory.setHost("127.0.0.1");

        //创建一个连接
        Connection connection = connectionFactory.newConnection();

        //创建一个频道
        Channel channel = connection.createChannel();

        //指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //发送的消息
        String message = "Hello RabbitMQ";

        //往队列中发送一条消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("sent message" + message);

        //关闭频道和连接
        channel.close();
        connection.close();


    }
}
