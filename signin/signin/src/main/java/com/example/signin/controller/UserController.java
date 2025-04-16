package com.example.signin.controller;

import com.example.signin.model.User;
import com.example.signin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }
   
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginStepRequest request) {
        Optional<User> optionalUser = userRepository.findByUsername(request.getUsername());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        User user = optionalUser.get();
        String input = request.getEnteredPassword();
        String method = user.getLogInMethod();
        int step = request.getStep();

        if ("r".equals(method)) {
            if (step == 1 && input.equals(user.getPassword())) {
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }

        if ("m".equals(method)) {
            if (step == 1 && input.equals(user.getPassword())) {
                return ResponseEntity.ok("PROCEED_TO_STEP_2");
            }
            if (step == 2 && input.equals(user.getPassword())) {
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }

        if ("s".equals(method)) {
            if (step == 1 && input.equals(user.getPassword())) {
                return ResponseEntity.ok("PROCEED_TO_STEP_2");
            }
            if (step == 2 && input.equals(user.getPassword())) {
                return ResponseEntity.ok("PROCEED_TO_STEP_3");
            }
            if (step == 3 && input.equals(user.getPassword())) {
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }

        if ("c".equals(method)) {
            if (step == 1 && input.equals(user.getPassword())) {
                return ResponseEntity.ok("PROCEED_TO_STEP_2");
            }
            if (step == 2 && input.equals(user.getPassword2())) {
                return ResponseEntity.ok("PROCEED_TO_STEP_3");
            }
            if (step == 3 && input.equals(user.getPassword3())) {
                return ResponseEntity.ok("PROCEED_TO_STEP_4");
            }
            if (step == 4 && input.equals(user.getPassword4())) {
                return ResponseEntity.ok(user);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid login method");
    }
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @PutMapping("/users/{id}/passwords")
    public ResponseEntity<?> updateUserPasswords(@PathVariable Long id, @RequestBody User updatedPasswords) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = optionalUser.get();

        user.setPassword(updatedPasswords.getPassword());

        if ("c".equals(user.getLogInMethod())) {
            user.setPassword2(updatedPasswords.getPassword2());
            user.setPassword3(updatedPasswords.getPassword3());
            user.setPassword4(updatedPasswords.getPassword4());
        }

        userRepository.save(user);
        return ResponseEntity.ok("Passwords updated");
    }


}
