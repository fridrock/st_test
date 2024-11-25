package ru.evanemo.st_test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "test_group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestGroup {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "group_id", nullable = false)
  private UUID groupId;
  @Column(name = "test_id", nullable = false)
  private UUID testId;
}
