package com.projekt.documents;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@ApiModel(description = "All details about the Document.")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated employee ID")
    private Long id;

    @Column
    @ApiModelProperty(notes = "The document's name is taken from file uploaded initial name")
    private String name;

    @Column
    private String contentType;
    @Lob
    @Column
    @ApiModelProperty(notes = "Content of the document: all text, pdf, and other types of contents")
    private byte[] content;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setName(String originalFilename) {
        this.name=originalFilename;
    }

    public void setContent(byte[] bytes) {
        this.content=bytes;
    }

    public byte[] getContent() {
        return this.content;
    }

    public String getName() {
        return this.name;
    }

    public Document(String name) {
        this.name=name;
    }

    public Document() {
    }
}
