package com.spring.control;

import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }

    //start REST server

    @RequestMapping(value = "/rest", method = RequestMethod.GET)
    public List<User> listUser() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/rest/admin/edit/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> edit(@PathVariable("id") int id, @RequestBody User user) {
        User tempUser = userService.getUserById(id);
        tempUser.setName(user.getName());
        tempUser.setLogin(user.getLogin());
        tempUser.setPassword(user.getPassword());
        userService.updateUser(tempUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/admin/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/admin/add", method = RequestMethod.POST)
    public ResponseEntity<Void> addModal(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rest/user/{id}", method = RequestMethod.POST)
    public User getUser(@PathVariable("id") int id) {

        return userService.getUserById(id);
    }

    @RequestMapping(value = "/rest/getuser/{name}", method = RequestMethod.POST)
    public User getUserByName(@PathVariable("name") String name) {

        return userService.getUserByLogin(name);
    }
    //end REST


}

