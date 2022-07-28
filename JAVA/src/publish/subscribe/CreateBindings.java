/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package publish.subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//Run me - Third
public class CreateBindings {
  public static void main(String[] args) throws IOException, TimeoutException {

    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("localhost");
    try (Connection connection = connectionFactory.newConnection();Channel channel = connection.createChannel()) {
      //Create bindings - (queue, exchange, routingKey)
      channel.queueBind("MobileQ", "my-direct-exchange", "personalDevice");
      channel.queueBind("ACQ", "my-direct-exchange", "homeAppliance");
      channel.queueBind("LightQ", "my-direct-exchange", "homeAppliance");
      channel.queueBind("hello", "my-direct-exchange", "hello");
    }
  }
}
