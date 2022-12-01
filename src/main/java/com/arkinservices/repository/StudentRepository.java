package com.arkinservices.repository;

import com.arkinservices.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query(value = "select * from student where deleted_flag = 0 order by ?1", nativeQuery = true)
    List<Student> customFindAll(String orderBy);

    @Query(value = "select * from student where deleted_flag = 0 and id = ?1", nativeQuery = true)
    Student customFindById(Integer id);

}