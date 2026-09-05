package practice.springcrm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseCreateDTO {

    @NotBlank(message = "Course name must not be blank")
    @Size(min = 3, max = 100, message = "Course name must be between 3 and 100 characters")
    private String courseName;
}
