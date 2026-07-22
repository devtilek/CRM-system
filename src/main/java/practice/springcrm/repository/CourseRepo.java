package practice.springcrm.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.springcrm.entity.Course;

import java.util.List;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {
    List<Course> findByTeacherId (Long teacherId);

    @EntityGraph(attributePaths = {"teacher"})
    List<Course> findAll();
}
