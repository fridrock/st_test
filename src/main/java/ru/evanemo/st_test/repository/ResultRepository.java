package ru.evanemo.st_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.evanemo.st_test.model.Result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResultRepository extends JpaRepository<Result, UUID> {
  Optional<Result> findByUserIdAndTestId(UUID userId, UUID testId);
  List<Result> findByUserId(UUID userId);
}
