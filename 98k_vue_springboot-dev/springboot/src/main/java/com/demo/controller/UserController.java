package com.demo.controller;

import com.demo.entity.Text;
import com.demo.entity.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/findAll/{page}/{size}")
    public Page<User> findAll(@PathVariable("page")Integer page,
                              @PathVariable("size")Integer size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return userRepository.findAll(pageable);
    }
    @GetMapping("/findAll")
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @GetMapping("/selectUser")
    public Object SelectUser(Integer id){
        return userRepository.findById(id);
    }
    @GetMapping("/selectUserByname")
    public User SelectUser(@RequestParam("name")String name){
        return userRepository.findByName(name);
    }
    @PostMapping("/insert")
    public User InsertUser(@RequestBody User user){
        //通过uuid生成唯一id
        Integer orderId= UUID.randomUUID().toString().replace("-","").hashCode();
        orderId = orderId < 0 ? -orderId : orderId;
        user.setId(Integer.valueOf(orderId));
        Timestamp d = new Timestamp(System.currentTimeMillis());
        user.setBtime(d);
        user.setJurisdiction(0);
        return userRepository.save(user);}
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam("id")Integer id){
        userRepository.deleteById(id);
    }
    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userRepository.save(user);
    }
}
