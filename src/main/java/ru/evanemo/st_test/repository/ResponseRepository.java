package ru.evanemo.st_test.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.evanemo.st_test.model.Response;

import java.util.UUID;

public interface ResponseRepository extends JpaRepository<Response, UUID> {
}
