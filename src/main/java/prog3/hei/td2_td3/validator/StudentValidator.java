package prog3.hei.td2_td3.validator;

import java.util.List;
import org.springframework.stereotype.Component;
import prog3.hei.td2_td3.model.Student;
import prog3.hei.td2_td3.exception.BadRequestException;

@Component
public class StudentValidator {
    public void validate(List<Student> students) {
        for (Student student : students) {
            if (student.getReference() == null || student.getReference().isEmpty()) {
                throw new BadRequestException("Reference is required");
            }
            if (student.getFirstName() == null || student.getFirstName().isEmpty()) {
                throw new BadRequestException("First name is required");
            }
            if (student.getLastName() == null || student.getLastName().isEmpty()) {
                throw new BadRequestException("Last name is required");
            }
        }
    }
}
