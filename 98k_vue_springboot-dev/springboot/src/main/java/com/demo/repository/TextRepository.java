package com.demo.repository;

import com.demo.entity.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TextRepository extends JpaRepository<Text, Integer> {
    List<Text> findByAuthor(String author);
    List<Text> findByPath(String path);

    List<Text> findByName(String name);
}
