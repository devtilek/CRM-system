package practice.springcrm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.springcrm.dto.CourseDTO;
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

    @PostMapping("/addCourse")
    public ResponseEntity<String> addCourse(@RequestBody CourseDTO courseDTO,@RequestParam Long userId){
        courseService.addCourse(courseDTO,userId);
        return new ResponseEntity<>("Курс успешно добавлен пользователю", HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Курс успешно удален");
    }
}
