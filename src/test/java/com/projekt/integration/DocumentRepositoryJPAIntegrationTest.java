package com.projekt.integration;

import com.projekt.SiteApplication;
import com.projekt.documents.Document;
import com.projekt.documents.DocumentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SiteApplication.class)
public class DocumentRepositoryJPAIntegrationTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    public void givenDocumentRepository_whenSaveAndRetrieveEntity_thenOk() {
        Document document = new Document();
        document.setName("testName");

        Document saved = documentRepository.save(document);
        Document found = documentRepository.findFirstByName("testName").orElse(null);

        assertNotNull(found);
        assertEquals(saved.getName(), found.getName());
    }
}