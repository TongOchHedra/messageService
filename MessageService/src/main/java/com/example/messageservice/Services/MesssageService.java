package com.example.messageservice.Services;

import com.example.messageservice.DAO.MessageRepository;
import com.example.messageservice.DAO.UserRepository;
import com.example.messageservice.Models.DTO.MessageRequest;
import com.example.messageservice.Models.Message;
import com.example.messageservice.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MesssageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;
    public boolean sendMessage(MessageRequest messageRequest){
        User sender = userRepository.findById(messageRequest.getSender_id())
                .orElseThrow(() -> new RuntimeException("Sender not found with id "));

        User receiver = userRepository.findById(messageRequest.getReceiver_id())
                .orElseThrow(() -> new RuntimeException("Receiver not found with id "));

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setMessage(messageRequest.getMessage());
        message.setDate(java.sql.Date.valueOf(LocalDate.now()));
        messageRepository.save(message);
        return true;
    }
    public List<Message> getAllMessages(){
        List<Message> messages = messageRepository.findAll();
        return messages;
    }
    public boolean deleteMessageById(long id){
        messageRepository.deleteById(id);
        return true;
    }
    public Message updateMessageById(long id, Message updatedMessage) {
        Optional<Message> messageOptional = messageRepository.findById(id);

        if (messageOptional.isPresent()) {
            Message existingMessage = messageOptional.get();
            existingMessage.setMessage(updatedMessage.getMessage());
            existingMessage.setDate(updatedMessage.getDate());
            existingMessage.setSender(updatedMessage.getSender());
            existingMessage.setReceiver(updatedMessage.getReceiver());
            return messageRepository.save(existingMessage);
        }
        return null;
    }
    public List<Message> findMessagesBySender(long senderId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found with ID: " + senderId));
        return messageRepository.findBySender(sender);
    }
    public List<Message> findMessagesByReceiver(long receiverId) {
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found with ID: " + receiverId));
        return messageRepository.findByReceiver(receiver);
    }

    public List<Message> findMessagesBySenderAndReceiver(long senderId, long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found with ID: " + senderId));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found with ID: " + receiverId));
        return messageRepository.findBySenderAndReceiver(sender, receiver);
    }


}
