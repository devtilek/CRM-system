package practice.springcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import practice.springcrm.dto.CourseCreateDTO;
import practice.springcrm.dto.CourseDTO;
import practice.springcrm.dto.UserDTO;
import practice.springcrm.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourse(id));
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestBody CourseCreateDTO courseCreateDTO,
                                            @RequestParam Long teacherId){
        courseService.addCourse(courseCreateDTO,teacherId);
        return new ResponseEntity<>("Курс успешно добавлен пользователю", HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Курс успешно удален");
    }

    @GetMapping("/{courseId}/students")
    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserDTO>> getStudentsByCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.getStudentsByCourse(courseId));
    }

}
