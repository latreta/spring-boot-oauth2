package com.devglan.controller;

import com.devglan.dao.UserRepository;
import com.devglan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userService;

    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser(){
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User findOne(@PathVariable long id){
        Optional<User> resultado = userService.findById(id);
        if(resultado.isPresent()){
            return resultado.get();
        }
        return null;
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public User update(@PathVariable long id, @RequestBody User user){
        user.setId(id);
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(value = "id") Long id){
        userService.deleteById(id);
    }

}
