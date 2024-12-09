package com.Accounting.Accounting.controllers;

import com.Accounting.Accounting.models.Grouper;
import com.Accounting.Accounting.models.Student;
import com.Accounting.Accounting.repozitory.GroupRepository;
import com.Accounting.Accounting.repozitory.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class controllerCreate {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudentRepository studentRepository;
    //тут список студентов
    @GetMapping("/manage")
    public String showmanagementPage(Model model){
        List<Student> students = studentRepository.findAll();
        List<Grouper> groupers = groupRepository.findAll();
        model.addAttribute("students", students);
        model.addAttribute("groups", groupers);
        return "management";

    }

    @GetMapping("/add-group")
    public String showAddgroupFrom(Model model){
        model.addAttribute("group", new Grouper());
        return "add-group";
    }

    @PostMapping("/add-group")
    public String addGroup(@ModelAttribute Grouper grouper){
        groupRepository.save(grouper);
        return "redirect:/add-group";

    }


    @GetMapping("/add-student")
    public String showAddStudentFrom(Model model){
        model.addAttribute("students", new Student());
        model.addAttribute("groups", groupRepository.findAll());
        return "add-student";
    }


    @PostMapping("/add-student")
    public String addStudent(@ModelAttribute Student student, @RequestParam Long groupId){
        Grouper group = groupRepository.findById(groupId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid group ID: " + groupId));
        student.setGroup(group);
        studentRepository.save(student);
        return "redirect:/add-student";
    }
}

