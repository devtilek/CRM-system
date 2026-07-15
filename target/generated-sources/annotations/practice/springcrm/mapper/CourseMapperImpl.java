package practice.springcrm.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import practice.springcrm.dto.CourseDTO;
import practice.springcrm.entity.Course;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-14T20:04:18+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseDTO toDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setId( course.getId() );
        courseDTO.setCourseName( course.getCourseName() );

        return courseDTO;
    }

    @Override
    public Course toEntity(CourseDTO courseDTO) {
        if ( courseDTO == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( courseDTO.getId() );
        course.setCourseName( courseDTO.getCourseName() );

        return course;
    }

    @Override
    public List<CourseDTO> courseListDTO(List<Course> courseList) {
        if ( courseList == null ) {
            return null;
        }

        List<CourseDTO> list = new ArrayList<CourseDTO>( courseList.size() );
        for ( Course course : courseList ) {
            list.add( toDTO( course ) );
        }

        return list;
    }
}
