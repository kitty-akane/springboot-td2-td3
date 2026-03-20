package prog3.hei.td2_td3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import prog3.hei.td2_td3.model.Student;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public List<Student> getAll() {
        return students;
    }

    public List<Student> addAll(List<Student> newStudents) {
        students.addAll(newStudents);
        return students;
    }

}
