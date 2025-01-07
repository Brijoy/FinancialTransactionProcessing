package mapper;

import Entity.AttemptRecordsEntity;
import dto.AttemptRecordsDTO;
import org.mapstruct.factory.Mappers;

public interface AttemptRecordsMapper {

    AttemptRecordsMapper INSTANCE = Mappers.getMapper(AttemptRecordsMapper.class);

    AttemptRecordsDTO toDTO(AttemptRecordsEntity entity);
    AttemptRecordsEntity toEntity(AttemptRecordsDTO dto);
}
