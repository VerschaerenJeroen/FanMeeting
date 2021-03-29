package be.thomasmore.league.controllers;

import be.thomasmore.league.model.User;
import be.thomasmore.league.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/register")
    public String register(Model model, Principal principal) {
        if (principal != null) return "redirect:/championlist";
        return "user/register";
    }

    @PostMapping("/register")
    public String registerPost(Model model, Principal principal,
                               @RequestParam String username,
                               @RequestParam String password) {
        if (principal != null) return "redirect:/championlist";
        if (username == null || username.isBlank()) return "redirect:/championlist";
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) return "redirect:/championlist";

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setRole("USER");
        String encode = passwordEncoder.encode(password);
        newUser.setPassword(encode);
        userRepository.save(newUser);

        autologin(username, password);

        return "redirect:/championlist";
    }

    private void autologin(String userName, String password) {
        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(userName, password);

        try {
            Authentication auth = authenticationManager.authenticate(token);
            logger.info("authentication: " + auth.isAuthenticated());

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/login")
    public String login(Model model,  Principal principal) {
        if (principal!=null) return "redirect:/championlist";
        return "user/login";
    }
}
