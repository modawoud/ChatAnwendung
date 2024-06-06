package edu.fra.uas.dockerdesktop.common;

import edu.fra.uas.dockerdesktop.model.User;
import edu.fra.uas.dockerdesktop.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Component
public class InitDB {

    private final UserRepository userRepository;

    @Autowired
    public InitDB(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

        List<User> users = new ArrayList<>();

        User user1 = new User(null, "admin1", "admin1", "@dawoud", "mohammed58@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user1);
        User user2 = new User(null, "admin2", "mohammed2", "@dawoud", "mohammed58+2@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user2);
        User user3 = new User(null, "admin3", "mohammed3", "@dawoud", "mohammed58+3@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user3);
        User user4 = new User(null, "admin4", "mohammed4", "@dawoud", "mohammed58+4@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user4);
        User user5 = new User(null, "admin5", "mohammed5", "@dawoud", "mohammed58+5@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user5);
        User user6 = new User(null, "admin6", "mohammed6", "@dawoud", "mohammed58+6@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user6);
        User user7 = new User(null, "admin7", "mohammed7", "@dawoud", "mohammed58+7@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user7);
        User user8 = new User(null, "admin8", "mohammed8", "@dawoud", "mohammed58+8@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user8);
        User user9 = new User(null, "admin9", "mohammed9", "@dawoud", "mohammed58+9@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user9);
        User user10 = new User(null, "admin10", "mohammed10", "@dawoud", "mohammed58+10@gmail.com", "admin1234", new HashSet<>(), new HashSet<>());
        users.add(user10);

        userRepository.saveAll(users);

        log.info("Database initialized with {} users", users.size());
    }
}
