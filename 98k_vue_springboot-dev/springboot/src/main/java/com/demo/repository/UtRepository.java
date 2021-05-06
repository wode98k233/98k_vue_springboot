package com.demo.repository;

import com.demo.entity.Ut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UtRepository extends JpaRepository<Ut,Integer> {
    @Query(value = "select * from ut where uid like :uid and tid like :tid",nativeQuery = true)
    List<Ut> findByUidandTid(@Param("uid")Integer uid,
                             @Param("tid")Integer tid);

    @Query(value = "SELECT * FROM liu.ut where collection like :collection and uid like :uid",nativeQuery = true)
    List<Ut> findByUidandCollection(@Param("collection")Integer collection,
                             @Param("uid")Integer uid);
}
