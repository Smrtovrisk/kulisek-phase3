package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bean.ChangePassword;
import com.service.LoginService;

@Controller
public class ChangePasswordController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/changePassword")
    public String showChangePasswordPage(Model model) {
        model.addAttribute("changePassword", new ChangePassword());
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String processChangePassword(@ModelAttribute("changePassword") ChangePassword changePassword, Model model) {
        if (changePassword.getCurrentPassword().isEmpty() || changePassword.getNewPassword().isEmpty() || changePassword.getConfirmNewPassword().isEmpty()) {
            model.addAttribute("error", "All fields must be filled in.");
            return "changePassword";
        }

        if (!changePassword.getNewPassword().equals(changePassword.getConfirmNewPassword())) {
            model.addAttribute("error", "New password and confirm new password do not match.");
            return "changePassword";
        }

        // Verify the current password is correct
        // Replace the "isCurrentPasswordCorrect" method with your actual logic to verify the current password
        boolean isCurrentPasswordCorrect = loginService.isCurrentPasswordCorrect(changePassword.getEmailid(), changePassword.getCurrentPassword());

        if (!isCurrentPasswordCorrect) {
            model.addAttribute("error", "Current password is incorrect.");
            return "changePassword";
        }

        loginService.updatePassword(changePassword.getEmailid(), changePassword.getNewPassword());
        return "changePasswordSuccess";
    }
}


