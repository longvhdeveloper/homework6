package my.vlong.java.homework6.mapper;

import java.util.List;

public interface IMapper<E, DTO> {

    DTO toDTO(E entity);

    E toEntity(DTO dto);

    List<DTO> toDTOs(List<E> entities);
    
    List<E> toEntities(List<DTO> dtos);
}
