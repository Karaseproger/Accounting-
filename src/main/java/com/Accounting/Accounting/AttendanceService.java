package com.Accounting.Accounting;

import com.Accounting.Accounting.models.Attendance;
import com.Accounting.Accounting.repozitory.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;
    }


    public List<Attendance> getAttendanceByGroup(Long groupId){
        return attendanceRepository.findAllByStudent_Group_Id(groupId);
    }

}
