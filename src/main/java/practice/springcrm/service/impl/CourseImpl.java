package practice.springcrm.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.springcrm.dto.CourseCreateDTO;
import practice.springcrm.dto.CourseDTO;
import practice.springcrm.dto.UserDTO;
import practice.springcrm.entity.Course;
import practice.springcrm.entity.Role;
import practice.springcrm.entity.User;
import practice.springcrm.mapper.CourseMapper;
import practice.springcrm.mapper.UserMapper;
import practice.springcrm.repository.CourseRepo;
import practice.springcrm.repository.UserRepo;
import practice.springcrm.service.CourseService;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    private final CourseRepo courseRepo;
    private final UserRepo userRepo;


    @Override
    public CourseDTO getCourse(Long id) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));
        return courseMapper.toDTO(course);
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courseList = courseRepo.findAll();
        return courseMapper.courseListDTO(courseList);
    }

    @Override
    @Transactional
    public void addCourse(CourseCreateDTO courseCreateDTO, Long teacherId) {
        User teacher = userRepo.findById(teacherId).orElseThrow(() ->
                new EntityNotFoundException("Teacher not found with id: " + teacherId));
        if (teacher.getRole() != Role.ROLE_TEACHER){
            throw new IllegalArgumentException("The user with this ID is not a teacher");
        }
        Course course = courseMapper.toEntity(courseCreateDTO);
        course.setTeacher(teacher);
        courseRepo.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepo.existsById(id)){
            throw new EntityNotFoundException("Course not found with id: " + id);
        }
        courseRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void enrollStudent(Long courseId, Long studentId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course not found"));
        User student = userRepo.findById(studentId).orElseThrow(() -> new EntityNotFoundException("Student not found"));
        course.getStudents().add(student);
        courseRepo.save(course);
    }

    @Override
    public List<CourseDTO> getCoursesByTeacher(Long teacherId) {
        return courseRepo.findByTeacherId(teacherId).stream()
                .map(courseMapper ::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getStudentsByCourse(Long courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(() ->
                new EntityNotFoundException("Course not found"));
        return course.getStudents().stream()
                .map(userMapper::toDTO)
                .toList();
    }
}
