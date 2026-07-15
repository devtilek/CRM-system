package practice.springcrm.mapper;

import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import practice.springcrm.dto.SignUpRequest;
import practice.springcrm.dto.UserDTO;
import practice.springcrm.entity.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-14T20:04:18+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setCourses( courseMapper.courseListDTO( user.getCourses() ) );

        return userDTO;
    }

    @Override
    public User toEntity(SignUpRequest signUpRequest) {
        if ( signUpRequest == null ) {
            return null;
        }

        User user = new User();

        user.setUsername( signUpRequest.getUsername() );
        user.setEmail( signUpRequest.getEmail() );
        user.setPassword( signUpRequest.getPassword() );

        return user;
    }
}
