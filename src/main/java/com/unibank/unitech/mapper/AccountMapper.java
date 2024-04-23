package com.unibank.unitech.mapper;

import com.unibank.unitech.dao.entity.AccountEntity;
import com.unibank.unitech.model.dto.AccountDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    List<AccountDto> toAccountList(List<AccountEntity> accountEntities);
}
