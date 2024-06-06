package edu.fra.uas.dockerdesktop.repository;

import edu.fra.uas.dockerdesktop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID> {

    Optional<User> findById(UUID id);
    Optional<User> findByUsername (String username);

    boolean existsById(UUID id);
    boolean existsByUsername (String username);

}