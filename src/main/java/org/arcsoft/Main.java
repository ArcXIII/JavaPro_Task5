package org.arcsoft;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner init(UserService userService, DepartmentService departmentService) {
        return args -> {
            log.info("Creating users...");
            var user1 = userService.getUserById(1L);
            var user2 = userService.getUserById(2L);
            var userId3 = userService.createUser("Mary Sue", user1.getDepartment()).getId();
            var userId4 = userService.createUser("John Doe", user2.getDepartment()).getId();

            departmentService.listAllDepartments();

            log.info("All created users: {}", userService.getAllUsers());

            log.info("Updating user with id = {}. Result: {}", userId3, userService.updateUser(userId3, "Marty Sue"));
            log.info("Updating user with id = {}. Result: {}", userId4, userService.updateUser(userId4, "Jane Doe"));

            log.info("Updated user with id = {}: {}", userId3, userService.getUserById(userId3));
            log.info("Updated user with id = {}: {}", userId4, userService.getUserById(userId4));

            log.info("Deleting user with id = {}. Result: {}", userId3, userService.deleteUser(userId3));

            log.info("All users after deletion: {}", userService.getAllUsers());

            log.info("Deleting user with id = {}. Result: {}", userId4, userService.deleteUser(userId4));

            try {
                log.info("Trying to find user with id = {}", userId4);
                userService.getUserById(userId4);
            } catch (Exception e) {
                log.error("Error finding user with id = {}.", userId4, e);
            }

            log.info("All users after last deletion: {}", userService.getAllUsers());
        };
    }
}