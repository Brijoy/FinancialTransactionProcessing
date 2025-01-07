package repository;

import Entity.AttemptRecordsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptRecordsRepository extends JpaRepository<AttemptRecordsEntity, Long> {


@Query(nativeQuery = true, value ="Select * from attempt_records where id = ?1")
List<AttemptRecordsEntity> findDetailsById(Long id);

List<AttemptRecordsEntity> findByUserId(Long id);
}
