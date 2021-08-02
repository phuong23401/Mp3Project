package com.karaoke.mp3project.controller;

import com.karaoke.mp3project.dto.respon.MessageResponse;
import com.karaoke.mp3project.model.Check;
import com.karaoke.mp3project.model.User;
import com.karaoke.mp3project.service.impl.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("check")
public class CheckController {
    @Autowired
    CheckService checkService;
    @PostMapping
    public ResponseEntity<?> createCheck(@Valid @RequestBody Check check){
        checkService.save(check);
        return new ResponseEntity<>(new MessageResponse("yes"), HttpStatus.OK);
    }
@GetMapping()
    public ResponseEntity<?> listCheck(){
        List<Check> checkList = checkService.findAll();
        if(checkList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(checkList, HttpStatus.OK);
}


}
