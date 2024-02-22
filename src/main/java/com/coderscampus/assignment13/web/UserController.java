package com.coderscampus.assignment13.web;

import com.coderscampus.assignment13.domain.Address;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AddressService;
import com.coderscampus.assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/register")
    public String getCreateUser(ModelMap model) {

        model.put("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String postCreateUser(User user) {
        System.out.println(user);
        userService.saveUser(user);
        return "redirect:/register";
    }

    @GetMapping("/users")
    public String getAllUsers(ModelMap model) {
        Set<User> users = userService.findAll();

        model.put("users", users);
        if (users.size() == 1) {
            model.put("user", users.iterator().next());
        }

        return "users";
    }

    @GetMapping("/users/{userId}")
    public String getOneUser(ModelMap model, @PathVariable Long userId) {
        User user = userService.findById(userId);
        model.put("users", Arrays.asList(user));
        model.put("user", user);
        return "users";
    }

    @PostMapping("/users/{userId}")
    public String postOneUser(User user, @PathVariable Long userId, Address address) {

        User currentUser = userService.findByIdAccounts(userId);
        user.setAccounts(currentUser.getAccounts());
        address = addressService.saveAddress(user.getAddress());
        user.setAddress(address);
        user.setAccounts(currentUser.getAccounts());
        userService.saveUser(user);
        return "redirect:/users/" + user.getUserId();
    }

    @PostMapping("/users/{userId}/delete")
    public String deleteOneUser(@PathVariable Long userId) {
        userService.delete(userId);
        return "redirect:/users";
    }
}
