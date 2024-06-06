package edu.fra.uas.dockerdesktop.controller;


import edu.fra.uas.dockerdesktop.common.ResponseMessage;
import edu.fra.uas.dockerdesktop.model.DirectMessage;
import edu.fra.uas.dockerdesktop.repository.DirectMessageRepository;
import edu.fra.uas.dockerdesktop.services.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("direct_messages")
public class DirectMessageController {

    private final DirectMessageRepository directMessageRepository;

    private MessageService messageService;

    @Autowired
    public DirectMessageController(DirectMessageRepository directMessageRepository) {
        this.directMessageRepository = directMessageRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> index(@RequestParam(value = "message-id", defaultValue = "null") String messageID) {
        log.debug("Indexing Sensor : {}", this.directMessageRepository.count());

        List<DirectMessage> messages = this.directMessageRepository.findAll();
        return this.message("Indexing messages", messages, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable UUID id) {
        log.debug("Getting User by id: {} ", id);
        Optional<DirectMessage> optionalMessage = this.directMessageRepository.findById(id);
        return optionalMessage.map(
                        sensor -> this.message("Getting Message by id", sensor, HttpStatus.OK))
                .orElseGet(() -> this.message("Message not found", null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public DirectMessage sendMessage(DirectMessage message){

        return this.messageService.saveMessage(message);
    }


    private ResponseEntity<ResponseMessage> message(String message, Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseMessage(message, data), httpStatus);
    }
}
