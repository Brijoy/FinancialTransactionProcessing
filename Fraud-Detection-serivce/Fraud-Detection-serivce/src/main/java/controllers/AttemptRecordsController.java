package controllers;

import dto.AttemptRecordsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.AttemptRecordsService;

@RestController
@RequestMapping("/attempt-records")
public class AttemptRecordsController {

    @Autowired
    private AttemptRecordsService attemptRecordsService;

    @PostMapping(value = "/create")
    public AttemptRecordsDTO createAttemptRecord(@RequestBody AttemptRecordsDTO dto) {
        return attemptRecordsService.saveAttemptRecord(dto);
    }

    @GetMapping("/{id}")
    public AttemptRecordsDTO getAttemptRecord(@PathVariable Long id) {
        return attemptRecordsService.getAttemptRecordById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttemptRecord(@PathVariable Long id) {
        attemptRecordsService.deleteAttemptRecord(id);
        return ResponseEntity.noContent().build();
    }
}