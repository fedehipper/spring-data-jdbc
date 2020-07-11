package com.demo.jdbc.repository;

import com.demo.jdbc.domain.Student;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
    
    @Modifying
    List<Student> findByName(String name);
    
}
