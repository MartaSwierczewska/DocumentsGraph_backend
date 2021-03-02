package com.projekt.houses;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "houses")
@ApiModel(description = "All details about the investment.")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    @ApiModelProperty(notes = "The database generated investment ID")
    private Long id;
    @Column
    @ApiModelProperty(notes = "Name that is shown to the user")
    private String nameToShow;
    @Column
    @ApiModelProperty(notes = "Description of the investment")
    private String description;
    @Column
    private String fileNamePath;

    private String file;

    public Long getId() {
        return id;
    }

    public House(String nameToShow, String description, String fileNamePath, String file) {
        this.nameToShow=nameToShow;
        this.description=description;
        this.fileNamePath=fileNamePath;
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setId(Long id) {
        this.id = id;
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
}

