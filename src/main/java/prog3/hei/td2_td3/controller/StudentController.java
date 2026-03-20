package prog3.hei.td2_td3.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addStudents(@RequestBody List<Student> newStudents) {

        try {
            List<Student> all = studentService.addAll(newStudents);
            return ResponseEntity.status(HttpStatus.CREATED).body(all);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("500: Internal server error");
        }
    }

    @GetMapping("/students")
    public ResponseEntity<?> getStudents(
            @RequestHeader(value = "Accept", required = false) String accept) {
        try {
            if (accept == null) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("400: Accept header is required");
            }
            if (accept.equals("text/plain")) {
                String names = studentService.getAll().stream()
                        .map(s -> s.getFirstName() + " " + s.getLastName())
                        .collect(Collectors.joining(", "));
                return ResponseEntity.ok(names);
            }
            if (accept.equals("application/json")) {
                return ResponseEntity.ok(studentService.getAll());
            }
            return ResponseEntity
                    .status(HttpStatus.NOT_IMPLEMENTED)
                    .body("501: Format non supporte.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("500: Internal server error");
        }
    }
}
