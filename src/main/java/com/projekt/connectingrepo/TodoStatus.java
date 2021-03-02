package com.projekt.connectingrepo;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Class created to receive perticular data from frontend")
public class TodoStatus {
    String description;
    @Getter
    boolean completed;

    public boolean isCompleted() {
        return completed;
    }
}
