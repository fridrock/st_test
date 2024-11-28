package ru.evanemo.st_test.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "user_id", nullable = false)
  private UUID userId;
  @ManyToOne
  private Test test;
  @Column(name = "correct_amount", nullable = false)
  private Integer correctAmount;
  @Column(name = "max_correct_amount", nullable = false)
  private Integer maxCorrectAmount;
}
