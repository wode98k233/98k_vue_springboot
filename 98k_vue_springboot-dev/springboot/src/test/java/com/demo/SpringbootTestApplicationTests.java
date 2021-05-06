package com.demo;

import com.demo.entity.Message;
import com.demo.repository.MessageRepository;
import com.demo.repository.TextRepository;
import com.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class SpringbootTestApplicationTests {

    @Autowired
    private MessageRepository mm;
    @Test
    void contextLoads() {
        Timestamp d = new Timestamp(System.currentTimeMillis());
        Message a = new Message();
        a.setMid(1);
        a.setFid(2);
        a.setFname("2");
        a.setSid(3);
        a.setSname("3");
        a.setText("aoisfjois");
        a.setTime(d);
        mm.save(a);
        System.out.println(mm.findBySid(3));
        mm.deleteById(1);
    }

}
