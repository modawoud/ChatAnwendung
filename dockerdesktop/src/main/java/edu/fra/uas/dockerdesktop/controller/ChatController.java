package edu.fra.uas.dockerdesktop.controller;


import edu.fra.uas.dockerdesktop.common.ResponseMessage;
import edu.fra.uas.dockerdesktop.model.Chat;
import edu.fra.uas.dockerdesktop.model.DirectMessage;
import edu.fra.uas.dockerdesktop.model.User;
import edu.fra.uas.dockerdesktop.repository.ChatRepository;
import edu.fra.uas.dockerdesktop.repository.DirectMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("chats")
public class ChatController {


    private final ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> index(@RequestParam(value = "message-id", defaultValue = "null") String messageID) {
        log.debug("Indexing Chats : {}", this.chatRepository.count());

        List<Chat> messages = this.chatRepository.findAll();
        return this.message("Indexing chats", messages, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable UUID id) {
        log.debug("Getting chat by id: {} ", id);
        Optional<Chat> optionalChat = this.chatRepository.findById(id);
        return optionalChat.map(
                        chat -> this.message("Getting Chat by id", chat, HttpStatus.OK))
                .orElseGet(() -> this.message("Chat not found", null, HttpStatus.NOT_FOUND));
    }


    @PostMapping("/chats")
    public ResponseEntity<ResponseMessage> createChat(@RequestBody Chat chat) {
        Optional<Chat> optionalChat = chatRepository.findByName(chat.getName());
        if (optionalChat.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ResponseMessage("Chat with name " + chat.getName() + " already exists", null));
        }

        Chat savedChat = chatRepository.save(chat);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage("Chat created", savedChat));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable("id") UUID id, @RequestBody Chat chat) {
        log.debug("Updating User by id: {}", id);
        Optional<Chat> optionalChat = this.chatRepository.findById(id);
        if (optionalChat.isPresent() && optionalChat.get().getId().equals(chat.getId())) {

            Chat chatCreated = this.chatRepository.save(chat);

            return this.message("Updating User by id", chatCreated, HttpStatus.ACCEPTED);
        }
        return this.message("User not found", null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") UUID id) {
        log.debug("Deleting User by id: {}", id);
        Optional<Chat> chatUpdate = this.chatRepository.findById(id);

        if (chatUpdate.isPresent()) {
            this.chatRepository.deleteById(id);
            return this.message("Chat is deleted", null, HttpStatus.NO_CONTENT);
        }
        return this.message("Chat is not found", null, HttpStatus.NO_CONTENT);
    }


    private ResponseEntity<ResponseMessage> message(String message, Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseMessage(message, data), httpStatus);
    }

}
