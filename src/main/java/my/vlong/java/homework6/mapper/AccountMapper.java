package my.vlong.java.homework6.mapper;

import my.vlong.java.homework6.dto.AccountDTO;
import my.vlong.java.homework6.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper extends IMapper<Account, AccountDTO> {
    
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Override
    @Mappings({
        @Mapping(target = "userRole", expression = "java(dto.getUserRole().getCode())")
    })
    public Account toEntity(AccountDTO dto);

    @Override
    @Mappings({
        @Mapping(target = "userRole", expression = "java(my.vlong.java.homework6.dto.UserRole.map(entity.getUserRole()))")
    })
    public AccountDTO toDTO(Account entity);

}
