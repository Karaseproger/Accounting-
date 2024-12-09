package com.Accounting.Accounting.repozitory;

import com.Accounting.Accounting.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
