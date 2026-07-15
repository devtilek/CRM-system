package practice.springcrm.dto;

import lombok.Data;
import practice.springcrm.entity.Course;

import java.util.List;
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<CourseDTO> courses;
}
