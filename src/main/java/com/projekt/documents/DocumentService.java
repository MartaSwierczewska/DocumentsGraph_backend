package com.projekt.documents;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository){
        this.documentRepository = documentRepository;
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document findDocumentByName(String name) {
        return this.documentRepository.findFirstByName(name).orElse(null);
    }

    public boolean addDocument(Document document) {
        if (documentRepository.findFirstByName(document.getName()).isPresent()) {
            return false;
        }
        documentRepository.save(document);
        return true;
    }


    public boolean deleteDocument(Document document) {
        if (documentRepository.findFirstByName(document.getName()).isPresent()) {
            documentRepository.delete(document);
            return true;
        }
        return false;
    }

}
