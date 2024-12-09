package com.Accounting.Accounting.repozitory;

import com.Accounting.Accounting.models.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByStudent_Group_Id(Long groupId);
}
