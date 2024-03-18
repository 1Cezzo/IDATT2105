package com.github.Cezzo.vuecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.github.Cezzo.vuecalculator.model.User;
import com.github.Cezzo.vuecalculator.repository.UserRepository;
import com.github.Cezzo.vuecalculator.service.UserService;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class VueCalculatorApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(VueCalculatorApplication.class, args);

        // Get beans
        UserRepository userRepository = context.getBean(UserRepository.class);
        UserService userService = context.getBean(UserService.class);

        // Create and save a user
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("testpassword");
        userService.saveUser(user);

        // Print saved user
        System.out.println("Saved User:");
        System.out.println(user);

        // Retrieve user by ID
        Optional<User> optionalUser = userService.getUserById(user.getId());
        if (optionalUser.isPresent()) {
            User retrievedUser = optionalUser.get();
            System.out.println("Retrieved User:");
            System.out.println(retrievedUser);
        } else {
            System.out.println("User not found.");
        }

        // Retrieve all users
        List<User> userList = userService.getAllUsers();
        System.out.println("All Users:");
        for (User u : userList) {
            System.out.println(u);
        }

        // Delete the user
        userService.deleteUser(user.getId());
        System.out.println("User deleted.");

        // Close the context
        context.close();
    }
}
