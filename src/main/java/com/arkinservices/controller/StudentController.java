package com.arkinservices.controller;

import com.arkinservices.entity.Student;
import com.arkinservices.exception.ErrorMessage;
import com.arkinservices.service.StudentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Student payload) {
        try {
            if (payload.getName() == null || payload.getName().equals("")) {
                return new ResponseEntity<>(ErrorMessage.NAME_IS_REQUIRED, HttpStatus.BAD_REQUEST);
            } else {
                Student result = service.save(payload);
                if (result != null) {
                    return new ResponseEntity<>(result, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>("Insert failed.", HttpStatus.BAD_REQUEST);
                }

            }
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<Object> list(@RequestParam(defaultValue = "name") String orderBy) {
        try {
            List<Student> result = service.findAll(orderBy);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> findById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Student payload) {
        try {

            if (payload.getName() == null || payload.getName().equals("")) {
                return new ResponseEntity<>(ErrorMessage.NAME_IS_REQUIRED, HttpStatus.BAD_REQUEST);
            } else {
                Student result = service.update(payload);
                if (result != null) {
                    return new ResponseEntity<>(result, HttpStatus.CREATED);
                } else {
                    return new ResponseEntity<>("Insert failed.", HttpStatus.BAD_REQUEST);
                }

            }
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(ErrorMessage.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
