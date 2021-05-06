package com.demo.controller;

import com.demo.entity.Text;
import com.demo.entity.User;
import com.demo.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/text")
public class TextController {
    @Autowired
    private TextRepository textRepository;
    @RequestMapping("/findAll/{page}/{size}")
    public Page<Text> findAll(@PathVariable("page")Integer page,
                              @PathVariable("size")Integer size) {
        Pageable pageable= PageRequest.of(page-1,size);
        return textRepository.findAll(pageable);
    }
    @RequestMapping("/findAll")
    public List<Text> findAll() {
        return textRepository.findAll();
    }
    @GetMapping("/selectText")
    public Object SelectText(@RequestParam("tid")Integer tid){
        return textRepository.findById(tid);
    }
    @GetMapping("/selectByAuthor")
    public List<Text> SelectText(@RequestParam("author")String author){return textRepository.findByAuthor(author);}
    @GetMapping("/selectByName")
    public List<Text> SelectTextByName(@RequestParam("name")String name){return textRepository.findByName(name);}
    @GetMapping("/selectByPath")
    public List<Text> SelectByPath(@RequestParam("path")String path){return textRepository.findByPath(path);}
    @PostMapping("/insert")
    public Text InsertText(@RequestBody Text text){
        Integer orderId= UUID.randomUUID().toString().replace("-","").hashCode();
        orderId = orderId < 0 ? -orderId : orderId;
        text.setTid(Integer.valueOf(orderId));
        Timestamp d = new Timestamp(System.currentTimeMillis());
        text.setTime(d);
        text.setUp(0);
        text.setCollection(0);
        return textRepository.save(text);
    }
    @DeleteMapping("/delete")
    public void deleteText(@RequestParam("tid")Integer tid){
         textRepository.deleteById(tid);
    }
    @PutMapping("/update")
    public Text updateText(@RequestBody Text text){
        return textRepository.save(text);
    }
}
