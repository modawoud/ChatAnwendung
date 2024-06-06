package edu.fra.uas.dockerdesktop.repository;

import edu.fra.uas.dockerdesktop.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {

    Optional<Chat> findById(UUID id);
    Optional<Chat> findByName(String name);
    boolean existsById(UUID id);
}
