package com.example.messageservice.Controllers;

import com.example.messageservice.Models.DTO.MessageRequest;
import com.example.messageservice.Models.Message;
import com.example.messageservice.Services.MesssageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MesssageService messageService;


    @PostMapping("sendmessage")
    public ResponseEntity<Void> sendMessage(@RequestBody MessageRequest messageRequest) {
        messageService.sendMessage(messageRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("showMessages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
    @GetMapping("/by-sender/{senderId}")
    public ResponseEntity<List<Message>> findMessagesBySender(@PathVariable long senderId) {
        try {
            List<Message> messages = messageService.findMessagesBySender(senderId);
            return ResponseEntity.ok(messages);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null); // Handle not found exception
        }
    }
    @GetMapping("/by-receiver/{receiverId}")
    public ResponseEntity<List<Message>> findMessagesByReceiver(@PathVariable long receiverId) {
        List<Message> messages = messageService.findMessagesByReceiver(receiverId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> findConversation(@RequestParam long senderId, @RequestParam long receiverId) {
        try {
            List<Message> conversation = messageService.findMessagesBySenderAndReceiver(senderId, receiverId);
            return ResponseEntity.ok(conversation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(null); // Handle not found exception
        }
    }


}