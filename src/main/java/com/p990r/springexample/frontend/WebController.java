package com.p990r.springexample.frontend;

import com.p990r.springexample.api.User;
import com.p990r.springexample.model.UserRepository;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WebController {

    @Autowired
    private UserRepository userRepository;
    private static final Logger LOG = LoggerFactory.getLogger(WebController.class);

    public WebController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String abstractIndex(Model model) {
        LOG.info("index");
        return "index";
    }

    @GetMapping("/")
    public String showMainPage(Model model) {
        return abstractIndex(model);
    }

    @GetMapping("/index")
    public String showIndexPage(Model model) {
        return abstractIndex(model);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getUsers() {
        LOG.info("Returning users");
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        LOG.info("signup");
        return "add-user";
    }

    private boolean isValidEmail(String email) {
        if (email.contains("@")) {
            String[] parts = email.split("@");
            try {
                if (parts[0].length() > 0 && parts[1].length() > 0) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                exception.printStackTrace();
            }
        }
        LOG.info("Invalid email");
        return false;
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        LOG.info("add user");
        if (!isValidEmail(user.getEmail())) {
            return "add-user";
        }
        LOG.info("{}", user.toString());

        userRepository.save(user);

        model.addAttribute("users", userRepository.findAll());

        return "redirect:/";
    }

    // additional CRUD methods
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
            BindingResult result, Model model) {
        if (!isValidEmail(user.getEmail())) {
            user.setId(id);
            return "update-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/users-table")
    public String showUsersTable(Model model) {
        model.addAttribute("users", getUsers());
        return "users-table";
    }
}
