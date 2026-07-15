package practice.springcrm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CourseCreateDTO {

    @NotBlank(message = "Названеи курса не должно быть пустым ")
    @Size(min = 3,max = 50, message = "Название курса должен быть от 3 до 50 символов")
    private String courseName;
}
