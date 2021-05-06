package com.demo.repository;

import com.demo.entity.Uu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UuRepository extends JpaRepository<Uu,Integer> {
    @Query(value = "select * from uu where uid like :uid and vip like :vip",nativeQuery = true)
    List<Uu> findByUid(@Param("uid")Integer uid, @Param("vip")Integer vip);

    @Query(value = "select * from uu where vipuid like :vipuid and vip like :vip",nativeQuery = true)
    List<Uu> findByVipuid(@Param("vipuid")Integer vipuid,@Param("vip")Integer vip);
}
