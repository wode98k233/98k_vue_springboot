package com.demo.controller;

import com.demo.entity.Message;
import com.demo.entity.Text;
import com.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;
    @RequestMapping("/findAll")
    public List<Message> findAll() {
        return messageRepository.findAll();
    }
    @GetMapping("/select")
    public List<Message> SelectText(@RequestParam("sid")Integer sid){
        return messageRepository.findBySid(sid);
    }
    @PostMapping("/insert")
    public Message InsertText(@RequestBody Message m){
        Integer orderId= UUID.randomUUID().toString().replace("-","").hashCode();
        orderId = orderId < 0 ? -orderId : orderId;
        m.setMid(Integer.valueOf(orderId));
        Timestamp d = new Timestamp(System.currentTimeMillis());
        m.setTime(d);
        return messageRepository.save(m);
    }
    @DeleteMapping("/delete")
    public void deleteText(@RequestParam("mid")Integer mid){
        messageRepository.deleteById(mid);
    }
}
