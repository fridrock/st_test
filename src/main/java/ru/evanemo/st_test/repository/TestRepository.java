package ru.evanemo.st_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.evanemo.st_test.model.Test;

import java.util.List;
import java.util.UUID;

@Repository
public interface TestRepository extends JpaRepository<Test, UUID> {
  List<Test> findByTeacherId(UUID teacherId);
}
