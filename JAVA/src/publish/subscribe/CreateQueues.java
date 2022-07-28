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

//Run me - second
public class CreateQueues {
  public static void main(String[] args) throws IOException, TimeoutException {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
      try (Connection connection = factory.newConnection(); 
          Channel channel = connection.createChannel()) {
          
          //Create the Queues
          channel.queueDeclare("MobileQ", true, false, false, null);
          channel.queueDeclare("ACQ", true, false, false, null);
          channel.queueDeclare("LightQ", true, false, false, null);
          
      }
  }
}
