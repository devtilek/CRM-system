package practice.springcrm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import practice.springcrm.dto.SignUpRequest;
import practice.springcrm.dto.UserDTO;
import practice.springcrm.entity.User;

@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface UserMapper {
    UserDTO toDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courses",ignore = true)
    User toEntity(SignUpRequest signUpRequest);
}
