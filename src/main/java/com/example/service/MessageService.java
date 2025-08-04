package com.example.service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
     @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Optional<Message> createMessage(Message message) {
        // Validate messageText
        if (message.getMessageText() == null || message.getMessageText().isBlank()
                || message.getMessageText().length() > 255) {
            return Optional.empty();
        }

        // Validate postedBy (accountId exists)
        if (message.getPostedBy() == null || accountRepository.findById(message.getPostedBy()).isEmpty()) {
            return Optional.empty();
        }

        // Save message
        return Optional.of(messageRepository.save(message));
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer messageId) {
        return messageRepository.findById(messageId);
    }

    public int deleteMessageById(Integer messageId) {
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return 1;
        }
        return 0;
    }

    public int updateMessage(Integer messageId, String newText) {
        if (newText == null || newText.isBlank() || newText.length() > 255) return 0;

        Optional<Message> optional = messageRepository.findById(messageId);
        if (optional.isEmpty()) return 0;

        Message msg = optional.get();
        msg.setMessageText(newText);
        messageRepository.save(msg);
        return 1;
    }

    public List<Message> getMessagesByAccountId(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
    
}
