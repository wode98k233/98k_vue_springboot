package com.demo.repository;

import com.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findBySid(Integer sid);

    Message findBySname(String sname);
}
