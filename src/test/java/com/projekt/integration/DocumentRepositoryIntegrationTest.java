package com.projekt.integration;

import com.projekt.documents.Document;
import com.projekt.documents.DocumentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DocumentRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void whenFindByName_thenReturnDocument() {

        //given
        Document document = new Document();
        document.setName("name");
        entityManager.persist(document);
        //entityManager.flush();

        //when
        Optional<Document> found = documentRepository.findFirstByName(document.getName());

        //then
        assertTrue(found.isPresent());
        assertThat(found.get().getName()).isEqualTo(document.getName());
    }

    @Test
    public void whenSave_thenReturnTrue() {

        Document document = new Document();
        document.setName("toAdd");

        //when
        Document saved = documentRepository.save(document);

        //then
        assertTrue(documentRepository.findFirstByName("toAdd").isPresent());
        assertEquals(documentRepository.findFirstByName("toAdd").get().getName(), saved.getName());
    }
}
