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
@Table(name = "question_responses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Response {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(name = "response_text", nullable = false)
  private String responseText;
  @Column(nullable = false)
  private Boolean correctness;
  @ManyToOne
  private Question question;
}
