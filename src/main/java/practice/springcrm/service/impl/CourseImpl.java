package practice.springcrm.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.springcrm.dto.CourseDTO;
import practice.springcrm.entity.Course;
import practice.springcrm.entity.User;
import practice.springcrm.mapper.CourseMapper;
import practice.springcrm.repository.CourseRepo;
import practice.springcrm.repository.UserRepo;
import practice.springcrm.service.CourseService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseImpl implements CourseService {
    private final CourseMapper courseMapper;
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
    public void addCourse(CourseDTO courseDTO, Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        Course course = courseMapper.toEntity(courseDTO);
        course.setUser(user);
        courseRepo.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepo.existsById(id)){
            throw new EntityNotFoundException("Course not found with id: " + id);
        }
        courseRepo.deleteById(id);
    }
}
