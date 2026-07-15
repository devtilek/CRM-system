package practice.springcrm.service;

import practice.springcrm.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    CourseDTO getCourse(Long id);
    List<CourseDTO> getAllCourses();
    void addCourse(CourseDTO courseDTO, Long userId);
    void deleteCourse(Long id);
}
