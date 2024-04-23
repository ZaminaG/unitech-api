package com.unibank.unitech.mapper;

import com.unibank.unitech.dao.entity.UserEntity;
import com.unibank.unitech.model.dto.UserRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    UserEntity toUserEntity(UserRegisterDto userRegisterDto);
}
