package com.Accounting.Accounting.controllers;

import com.Accounting.Accounting.models.Attendance;
import com.Accounting.Accounting.models.Grouper;
import com.Accounting.Accounting.models.Student;
import com.Accounting.Accounting.repozitory.AttendanceRepository;
import com.Accounting.Accounting.repozitory.GroupRepository;
import com.Accounting.Accounting.repozitory.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/check")
public class controllerCheck {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Получение всех групп
    @GetMapping
    public String getAllGroups(Model model) {
        Iterable<Grouper> groups = groupRepository.findAll();
        model.addAttribute("groups", groups);
        return "check"; // Отображает список групп
    }

    // Просмотр студентов группы
    @GetMapping("/{id}")
    public String getStudentsByGroup(@PathVariable("id") Long id, Model model) {
        // Проверяем, существует ли группа
        Optional<Grouper> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty()) {
            return "redirect:/check"; // Если группы нет, перенаправляем назад
        }

        Grouper group = optionalGroup.get();
        List<Student> students = group.getStudents(); // Получаем студентов группы
        model.addAttribute("group", group);
        model.addAttribute("students", students);
        return "student_list"; // Отображает студентов выбранной группы
    }

    // Сохранение посещаемости
    @PostMapping("/{id}/attendance")
    public String saveAttendance(
            @PathVariable("id") Long groupId,
            @RequestParam("studentId") List<Long> studentIds,
            @RequestParam("status") List<Attendance.AttendanceStatus> statuses) {

        // Сохраняем посещаемость для каждого студента
        for (int i = 0; i < studentIds.size(); i++) {
            Long studentId = studentIds.get(i);
            Attendance.AttendanceStatus status = statuses.get(i);

            Optional<Student> optionalStudent = studentRepository.findById(studentId);
            if (optionalStudent.isPresent()) {
                Attendance attendance = new Attendance();
                attendance.setStudent(optionalStudent.get());
                attendance.setDate(java.time.LocalDate.now());
                attendance.setStatus(status);
                attendanceRepository.save(attendance);
            }
        }
        return "redirect:/check/" + groupId; // Возвращаемся к странице группы
    }
}
