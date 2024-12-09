package com.Accounting.Accounting.controllers;


import com.Accounting.Accounting.AttendanceService;
import com.Accounting.Accounting.models.Attendance;
import com.Accounting.Accounting.models.Grouper;
import com.Accounting.Accounting.models.Student;
import com.Accounting.Accounting.repozitory.AttendanceRepository;
import com.Accounting.Accounting.repozitory.GroupRepository;
import com.Accounting.Accounting.repozitory.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class controllerRecord {
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentRepository studentRepository;

    public controllerRecord(AttendanceService attendanceService, GroupRepository groupRepository){
        this.attendanceService = attendanceService;
        this.groupRepository = groupRepository;
    }
    // список групп
    @GetMapping("/groups")
    public String getGroup(Model model){
        model.addAttribute("groupaten", groupRepository.findAll());
        return "groupatten";
    }

    @GetMapping("/groups/{groupId}/attendance")
    public String getAttendanceBygroup(@PathVariable Long groupId, Model model){
        List<Attendance> attendances = attendanceService.getAttendanceByGroup(groupId);
        Grouper grouper = groupRepository.findById(groupId).orElseThrow(() -> new IllegalArgumentException("Group not found"));


        model.addAttribute("group", grouper);
        model.addAttribute("attendances", attendances);

        return "attendans";
    }




}
