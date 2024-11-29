package ru.evanemo.st_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.evanemo.st_test.model.TestGroup;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TestGroupRepository extends JpaRepository<TestGroup, Long> {
  Optional<TestGroup> findByTestIdAndGroupId(UUID testId, UUID groupId);
  List<TestGroup> findByGroupId(UUID groupId);

  List<TestGroup> findByTestId(UUID testId);
}
