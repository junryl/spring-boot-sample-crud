package com.arkinservices.service;

import com.arkinservices.entity.Student;
import com.arkinservices.repository.StudentRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public Student save(Student payload) {
        return repository.save(payload);
    }

    public List<Student> findAll(String orderBy) {
        return repository.customFindAll(orderBy);
    }

    public Student findById(int id) {
        return repository.customFindById(id);
    }

    public Student update(Student payload) throws Exception {
        Student currentRecord = null;
        try {
            currentRecord = repository.findById(payload.getId()).orElse(null);
        } catch (Exception ex) {
            // return error
        }

        if (currentRecord == null) {
            throw new NotFoundException("Id is missing or not found.");
        }
        currentRecord.setName(payload.getName());
        currentRecord.setPresent(payload.getPresent());
        return repository.save(currentRecord);
    }

    public Boolean deleteById(Integer id) throws NotFoundException {
        Student currentRecord = repository.findById(id).orElse(null);
        if (currentRecord == null) {
            throw new NotFoundException("Student Id is  not found.");
        }
        repository.deleteById(id);
        return true;
    }

}
