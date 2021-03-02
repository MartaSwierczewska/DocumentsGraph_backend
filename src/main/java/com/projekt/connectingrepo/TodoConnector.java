package com.projekt.connectingrepo;

import com.projekt.documents.Document;
import com.projekt.houses.House;
import com.projekt.todos.Todo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

//this table connects concrete house with all todos and their different statuses (state: completed or not)
@Data
@Entity
@ApiModel(description = "Details about TodoConnector: class that enables to connect an investment with it's state of todos, and also with uploaded documents.")
public class TodoConnector implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated todo-connector ID")
    private Long id;

    @OneToOne
    @ApiModelProperty(notes = "connection with investment")
    private House house;

    @OneToOne
    @ApiModelProperty(notes = "connection with todo")
    private Todo todo;

    @OneToOne
    @ApiModelProperty(notes = "connection with document")
    private Document document;

    @Column
    @ApiModelProperty(notes = "indicates whether todo is completed or not")
    private boolean completed;

    public Todo getTodo() {
        return todo;
    }

    public Document getDocument() {
        return this.document;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed=completed;
    }

    public void setDocument(Document firstByContent) {
        this.document=firstByContent;
    }

    public TodoConnector() {
    }

    public TodoConnector(House house){
        this.house=house;
    }

    public TodoConnector(House house, Todo todo, boolean completed, Document document) {
        this.house=house;
        this.todo=todo;
        this.completed=completed;
        this.document=document;
    }


}
