package com.coderscampus.assignment13.web;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users/{userId}/account")
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;

    @GetMapping("{accountId}")
    public String getOneAccount(ModelMap model, User user, @PathVariable Long accountId, @PathVariable Long userId) {
        user = userService.findById(userId);
        Account account = accountService.findById(accountId);
        model.put("user", user);
        model.put("account", account);
        return "account";
    }

    @PostMapping("{accountId}")
    public String postOneAccount(Account account, User user) {
        accountService.saveAccount(account);
        return "redirect:/users/"+user.getUserId()+"/account/"+account.getAccountId();
    }
}
