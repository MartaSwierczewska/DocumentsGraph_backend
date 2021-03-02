package com.projekt.integration;

import com.projekt.documents.Document;
import com.projekt.documents.DocumentRepository;
import com.projekt.documents.DocumentService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest //not neccessary ?
public class DocumentServiceTest {

    @InjectMocks
    DocumentService documentService;

    @Mock
    DocumentRepository documentRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindDocumentByName() {
//        when
        Document document = new Document();
        document.setId((long) 1);
        document.setName("doc");

        when(documentRepository.findFirstByName(anyString())).thenReturn(java.util.Optional.of(document));

//        then
        Document returnedDocument = documentService.findDocumentByName("someName");
        assertNotNull(returnedDocument);
        assertEquals(document, returnedDocument);
    }
}