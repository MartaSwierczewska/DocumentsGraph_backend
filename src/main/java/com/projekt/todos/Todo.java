package com.projekt.todos;

import com.projekt.categories.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "todos")
@ApiModel(description = "All details about todo: a process that has to be done to finish the investment procedure.")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    @ApiModelProperty(notes = "The database generated todo ID")
    Long id;

    @Column(name="description")
    String description;

    @OneToOne
    Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Todo(){}

    public Todo(String description, Category category) {
        this.description=description;
        this.category=category;
    }

    public Todo(Long id, String description, Category category) {
        this.id=id;
        this.description=description;
        this.category=category;
    }


}
