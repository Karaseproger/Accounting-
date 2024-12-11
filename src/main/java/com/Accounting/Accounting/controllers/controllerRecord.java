package com.Accounting.Accounting.controllers;


import com.Accounting.Accounting.Servise.AttendanceService;
import com.Accounting.Accounting.models.Attendance;
import com.Accounting.Accounting.models.Grouper;
import com.Accounting.Accounting.repozitory.GroupRepository;
import com.Accounting.Accounting.repozitory.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
    public String getAttendanceByGroup(@PathVariable Long groupId, Model model) {
        List<Attendance> attendances = attendanceService.getAttendanceByGroup(groupId);

        // Проверяем, существует ли группа
        Grouper grouper = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Group not found"));

        // Проверяем, есть ли данные о посещаемости
        if (attendances == null || attendances.isEmpty()) {
            throw new IllegalStateException("No attendance data found for this group");
        }

        model.addAttribute("group", grouper);
        model.addAttribute("attendances", attendances);

        return "attendance";
    }





}
