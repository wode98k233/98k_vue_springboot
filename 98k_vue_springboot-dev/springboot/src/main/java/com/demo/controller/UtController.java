package com.demo.controller;


import com.demo.entity.Text;
import com.demo.entity.User;
import com.demo.entity.Ut;
import com.demo.repository.UtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ut")
public class UtController {
    @Autowired
    private UtRepository utRepository;
    @GetMapping("/findByUidandTid")
    public List<Ut> findByUidandTid(@RequestParam("uid")Integer uid,
                            @RequestParam("tid")Integer tid) {
        return utRepository.findByUidandTid(uid,tid);
    }
    @GetMapping("/findByUidandCollection")
    public List<Ut> findByUidandCollection(@RequestParam("uid")Integer uid,
                                    @RequestParam("collection")Integer collection) {
        return utRepository.findByUidandCollection(collection,uid);
    }
    @PostMapping("/insert")
    public Ut InsertUt(@RequestBody Ut ut){
        return utRepository.save(ut);
    }
    @PutMapping("/update")
    public Ut updateUt(@RequestBody Ut ut){
        return utRepository.save(ut);
    }

}
