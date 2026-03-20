package prog3.hei.td2_td3.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import prog3.hei.td2_td3.model.Student;
import prog3.hei.td2_td3.service.StudentService;
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public String addStudents(@RequestBody List<Student> newStudents) {
        List<Student> all = studentService.addAll(newStudents);
        return all.stream()
                .map(s -> s.getFirstName() + " " + s.getLastName())
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/students")
    public String getStudents(@RequestHeader("Accept") String accept) {
        if (accept.equals("text/plain")) {
            return studentService.getAll().stream()
                    .map(s -> s.getFirstName() + " " + s.getLastName())
                    .collect(Collectors.joining(", "));
        }
        return "Format non supporté.";
    }
}
