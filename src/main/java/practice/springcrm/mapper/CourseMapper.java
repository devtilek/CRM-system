package practice.springcrm.mapper;

import org.mapstruct.Mapper;
import practice.springcrm.dto.CourseCreateDTO;
import practice.springcrm.dto.CourseDTO;
import practice.springcrm.entity.Course;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toDTO(Course course);

    Course toEntity(CourseDTO courseDTO);

    List<CourseDTO> courseListDTO(List<Course> courseList);

    Course toEntity(CourseCreateDTO courseCreateDTO);
}
