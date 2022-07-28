import pika

connection = pika.BlockingConnection(pika.ConnectionParameters('25.9.31.126'))
channel = connection.channel()

channel.queue_declare(queue='hello')

message = 'publicador python conectado...'

channel.basic_publish(exchange='', routing_key='hello', body=message)
print(f" Mensaje enviado: {message}")

while message != "exit":
    message = input()
    channel.basic_publish(exchange='', routing_key='hello', body=message)
    print(f" Mensaje enviado: {message}")