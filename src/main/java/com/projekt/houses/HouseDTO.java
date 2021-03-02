package com.projekt.houses;

import com.projekt.todos.TodoDTO;

import java.io.File;
import java.util.List;

public class HouseDTO {
    String nameToShow;
    String description;
    String fileNamePath;
    String file;
    List<TodoDTO> todos;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getNameToShow() {
        return nameToShow;
    }

    public void setNameToShow(String nameToShow) {
        this.nameToShow = nameToShow;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileNamePath() {
        return fileNamePath;
    }

    public void setFileNamePath(String fileNamePath) {
        this.fileNamePath = fileNamePath;
    }

    public List<TodoDTO> getTodos() {
        return todos;
    }

    public void setTodos(List<TodoDTO> todos) {
        this.todos = todos;
    }
}
