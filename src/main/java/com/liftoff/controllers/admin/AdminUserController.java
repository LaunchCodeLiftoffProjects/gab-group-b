package com.liftoff.controllers.admin;

import com.liftoff.models.Role;
import com.liftoff.models.User;
import com.liftoff.models.data.RoleRepository;
import com.liftoff.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account/manageUsers")
public class AdminUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping ("")
    public String displayManageUsersPage (Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("pageTitle", "User Management");
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("user", new User());
        return "/account/manageUsers/index";
    }

    @GetMapping("new")
    public String displayRegisterNewUser(){
        return "/register";
    }

    @GetMapping ("edit/{id}")
    public String displayEditUserPage(@PathVariable("id") Integer id, Model model){
        if (!userRepository.existsById(id)) {
            return "/500";
        } else {
            List<User> listUsers = userRepository.findAll();
            List<Role> listRoles = roleRepository.findAll();
            User user = userRepository.findById(id).get();
            model.addAttribute("listUsers", listUsers);
            model.addAttribute("listRoles", listRoles);
            model.addAttribute("pageTitle", "User Management");
            model.addAttribute("title", "Editing User");
            model.addAttribute("button", "Update");
            model.addAttribute("user", user);
            return "/account/manageUsers/edit";
        }
    }

    @GetMapping("reset/{id}")
    public String passwordReset (@PathVariable("id") Integer id) {
        if (!userRepository.existsById(id)) {
            return "/500";
        }else{
            User user = userRepository.getById(id);
            String resetPassword = "welcome";
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(resetPassword);
            user.setPassword(encodedPassword);
            return "redirect:";
        }
    }

    @GetMapping ("delete/{id}")
    public String deleteUser (User user, Model model) {
        userRepository.delete(user);
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("title", "User Management");
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("user", new User());
        return "/account/manageUsers/index";
    }

    @PostMapping("save")
    public String saveUser (User user) {
        userRepository.save(user);
        return "redirect:";
    }
}
