package edu.fra.uas.dockerdesktop.controller;

import edu.fra.uas.dockerdesktop.common.ResponseMessage;
import edu.fra.uas.dockerdesktop.model.DirectMessage;
import edu.fra.uas.dockerdesktop.model.User;
import edu.fra.uas.dockerdesktop.repository.UserRepository;
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
@RequestMapping("/api/v1/web/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMessage> index() {
        log.debug("Indexing Sensor : {}", this.userRepository.count());

            List<User> users = this.userRepository.findAll();
            return this.message("Indexing Users", users, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseMessage> getById(@PathVariable UUID id) {
        log.debug("Getting User by id: {} ", id);
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.map(
                        sensor -> this.message("Getting User by id", sensor, HttpStatus.OK))
                .orElseGet(() -> this.message("User not found", null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> create(@RequestBody User user) {
        log.debug("create User: {}",user );
        Optional<User> optionalUser = (user.getId() != null) ? this.userRepository.findById(user.getId()) : Optional.empty();

        if (optionalUser.isPresent() && userRepository.existsByUsername(user.getUsername())) {
            return this.message("User is already exists", null, HttpStatus.CONFLICT);
        }
        User userCreated = this.userRepository.save(user);
        return this.message("Created User", userCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> update(@PathVariable("id") UUID id, @RequestBody User user) {
        log.debug("Updating User by id: {}", id);
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent() && optionalUser.get().getId().equals(user.getId())) {

            User sensorCreated = this.userRepository.save(user);

            return this.message("Updating User by id", sensorCreated, HttpStatus.ACCEPTED);
        }
        return this.message("User not found", null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable("id") UUID id) {
        log.debug("Deleting User by id: {}", id);
        Optional<User> userUpdate = this.userRepository.findById(id);

        if (userUpdate.isPresent()) {
            this.userRepository.deleteById(id);
            return this.message("User is deleted", null, HttpStatus.NO_CONTENT);
        }
        return this.message("User is not found", null, HttpStatus.NO_CONTENT);
    }


    private ResponseEntity<ResponseMessage> message(String message, Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseMessage(message, data), httpStatus);
    }
}