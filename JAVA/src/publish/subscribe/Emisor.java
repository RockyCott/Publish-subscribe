/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package publish.subscribe;
import com.rabbitmq.client.*;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Emisor {
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            String message = "Turn on home appliances";
            channel.basicPublish("my-direct-exchange", "homeAppliance", null, message.getBytes());
            channel.basicPublish("my-direct-exchange", "hello", null, "HOLA COMO ESTAS".getBytes());
            
            System.out.println(" [x] Sent '" + "homeAppliance" + "':'" + message + "'");
            

        }
    }
}