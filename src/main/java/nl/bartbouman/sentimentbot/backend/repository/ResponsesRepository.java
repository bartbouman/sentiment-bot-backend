package nl.bartbouman.sentimentbot.backend.repository;

import nl.bartbouman.sentimentbot.backend.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsesRepository extends JpaRepository<Response, Long> {}
