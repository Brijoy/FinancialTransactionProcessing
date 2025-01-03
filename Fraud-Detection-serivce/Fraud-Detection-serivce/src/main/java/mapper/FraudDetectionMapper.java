package mapper;

import Entity.FraudDetectionEntity;
import dto.FraudDetectionDTO;
//import org.mapstruct.Mapper;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
public interface FraudDetectionMapper {

//    FraudDetectionMapper INSTANCE = Mappers.getMapper(FraudDetectionMapper.class);

    FraudDetectionDTO toDTO(FraudDetectionEntity entity);
    FraudDetectionEntity toEntity(FraudDetectionDTO dto);

}
