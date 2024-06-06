package edu.fra.uas.dockerdesktop.services;

import edu.fra.uas.dockerdesktop.model.DirectMessage;
import edu.fra.uas.dockerdesktop.repository.DirectMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired

    private DirectMessageRepository directMessageRepository;

    public DirectMessage saveMessage(DirectMessage message ) {
        message.setCreatedAt(LocalDateTime.now());
        return directMessageRepository.save(message);
    }

    public List<DirectMessage> getAllMessages(){
        return directMessageRepository.findAll();
    }
}
