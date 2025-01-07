package services;

import Entity.AttemptRecordsEntity;
import dto.AttemptRecordsDTO;
import mapper.AttemptRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AttemptRecordsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AttemptRecordsService {

    @Autowired
    private AttemptRecordsRepository attemptRecordsRepository;

    private final AttemptRecordsMapper attemptRecordsMapper = AttemptRecordsMapper.INSTANCE;

    public AttemptRecordsDTO getAttemptRecordById(Long id) {
        Optional<AttemptRecordsEntity> entity = attemptRecordsRepository.findById(id);
        return entity.map(attemptRecordsMapper::toDTO).orElse(null);
    }


    public AttemptRecordsDTO saveAttemptRecord(AttemptRecordsDTO dto) {
        AttemptRecordsEntity entity = attemptRecordsMapper.toEntity(dto);
        AttemptRecordsEntity savedEntity = attemptRecordsRepository.save(entity);
        return attemptRecordsMapper.toDTO(savedEntity);
    }

    public void deleteAttemptRecord(Long id) {
        attemptRecordsRepository.deleteById(id);
    }
}