package practice.springcrm.service;

import practice.springcrm.dto.CourseDTO;
import practice.springcrm.dto.UserDTO;

import java.util.List;

public interface CourseService {
    CourseDTO getCourse(Long id);
    List<CourseDTO> getAllCourses();
    void addCourse(CourseDTO courseDTO, Long userId);
    void deleteCourse(Long id);
    void enrollStudent(Long courseId, Long studentId);
    List<CourseDTO> getCoursesByTeacher(Long teacherId);
    List<UserDTO> getStudentsByCourse(Long courseId);
}
