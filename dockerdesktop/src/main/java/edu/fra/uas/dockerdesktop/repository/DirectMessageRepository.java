package edu.fra.uas.dockerdesktop.repository;

import edu.fra.uas.dockerdesktop.model.Chat;
import edu.fra.uas.dockerdesktop.model.DirectMessage;
import edu.fra.uas.dockerdesktop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DirectMessageRepository extends JpaRepository<DirectMessage, UUID> {

    List<DirectMessage> findByUser(User user);

    List<DirectMessage> findByChat(Chat chat);
}
