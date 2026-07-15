package practice.springcrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.springcrm.entity.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

}
