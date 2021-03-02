package com.projekt.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findFirstByName(String name);
    Document findFirstByContent(byte[] content);
}
