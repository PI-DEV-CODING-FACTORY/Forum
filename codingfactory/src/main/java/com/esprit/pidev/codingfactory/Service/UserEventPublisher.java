// package com.esprit.pidev.codingfactory.Service;

// import org.springframework.amqp.core.Queue;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
// import org.springframework.stereotype.Service;

// @Service
// public class UserEventPublisher {

//     private final RabbitTemplate rabbitTemplate;
//     private final Queue userQueue;

//     public UserEventPublisher(RabbitTemplate rabbitTemplate, Queue userQueue) {
//         this.rabbitTemplate = rabbitTemplate;
//         this.userQueue = userQueue;
//     }

//     public void publishUserCreatedEvent(String user) {
//         rabbitTemplate.convertAndSend(userQueue.getName(), user);
//     }
// }