package io.pivotal.pal.tracker;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TimeEntryController {
    private TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry entry) {
        var createdEntry = this.repository.create(entry);
        return new ResponseEntity(createdEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        return Optional
            .ofNullable(this.repository.find(id))
            .map(user -> ResponseEntity.ok(user))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(this.repository.list());
    }

    @PutMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry entry) {
        return Optional
            .ofNullable(this.repository.update(id, entry))
            .map(user -> ResponseEntity.ok(user))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        this.repository.delete(id);
        return ResponseEntity.noContent().build();
    }

}