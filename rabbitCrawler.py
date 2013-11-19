#!/usr/bin/env python
import pika
import logging

logging.basicConfig(format='%(levelname)s:%(message)s', level=logging.CRITICAL)

credentiale = pika.PlainCredentials('guest', 'guest')
connection = pika.BlockingConnection(pika.ConnectionParameters(host='azyl13.no-ip.org',port=5672,virtual_host='/',credentials=credentiale))
channel = connection.channel()

channel.queue_declare(queue='fetchQueue')
message='https://mail.google.com/mail/u/0/?shva=1#inbox'


channel.basic_publish(exchange='',
                      routing_key='fetchPage',
                      body=message)
print " [x] Sent URL to be fetched --->  '" + message +"'"
connection.close()
