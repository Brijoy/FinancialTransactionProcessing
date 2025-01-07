package repository;

import Entity.FraudDetectionEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface FraudDetectionRepository  extends JpaRepository<FraudDetectionEntity, Long> {

    @Query(nativeQuery = true, value = "select * from fraud_detection where idv =?1")
    List<FraudDetectionEntity> findDetailsById(Long id);



}
