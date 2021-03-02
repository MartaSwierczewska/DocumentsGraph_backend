package com.projekt.connectingrepo;

import com.projekt.houses.House;
import com.projekt.todos.Todo;
import com.projekt.todos.TodoDTO;
import com.projekt.todos.TodoDescription;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/house")
@CrossOrigin("*")
@Api(value = "Todos and investments managing system", description = "Operations that are involving houses, todos and documents")
public class TodoConnectorController {

    private static final Logger LOG = Logger.getLogger(TodoConnectorController.class);

    private final TodoConnectorService todoConnectorService;

    @Autowired
    public TodoConnectorController(TodoConnectorService todoConnectorService) {
        this.todoConnectorService = todoConnectorService;
    }

    @GetMapping("/{houseId}/{categoryId}/todos")
    @ApiOperation(value = "Finds todos that belongs to a specific house")
    public ResponseEntity<List<TodoConnector>> getHousesTodos(@PathVariable Long houseId, @PathVariable Long categoryId) {
        return new ResponseEntity<>(todoConnectorService.getHouseTodosByHouseIdAndCategory(houseId, categoryId), HttpStatus.ACCEPTED);
    }

    @PostMapping("/{houseId}/{categoryId}")
    @ApiOperation(value = "Updates todos that belongs to a specific house")
    public void updateTodosInHouse(@PathVariable Long houseId,@PathVariable Long categoryId, @RequestBody List<TodoDTO> todos) {
        todoConnectorService.updateTodosInHouse(houseId, categoryId, todos);
    }

    @CrossOrigin
    @PostMapping("/{houseId}/{categoryId}/todo")
    public int addTodoToHouse(@RequestBody TodoDescription todoDescription, @PathVariable Long houseId, @PathVariable Long categoryId){
        todoConnectorService.addTodoToHouse(todoDescription,houseId, categoryId);
        return 0;
    }

    @CrossOrigin
    @PostMapping("/{houseId}/{categoryId}/todos")
    public int addTodoFromCSVToHouse(@RequestParam(value = "file") MultipartFile CSVFile, @PathVariable Long houseId, @PathVariable Long categoryId){
        todoConnectorService.extractCSVToTodos(CSVFile,houseId, categoryId);
        return 0;
    }

    @CrossOrigin
    @DeleteMapping("/{id}/todo")
    public int removeTodoFromHouse(@PathVariable Long id){
        todoConnectorService.removeTodoFromHouse(id);
        return 0;
    }

    @CrossOrigin
    @PostMapping("/document/{generalId}")
    @ApiOperation(value = "Handles file upload")
    public ResponseEntity<Boolean> handleFileUpload(@RequestParam(value = "file") MultipartFile file, @PathVariable Long generalId) throws IOException {
        System.out.println(generalId);
        return new ResponseEntity<>(todoConnectorService.saveDocument(file, generalId), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value = "/document/{id}", produces = MediaType.ALL_VALUE)
    @ApiOperation(value = "Handles file download")
    public byte[] handleFileDownload(@PathVariable Long id) {
        return todoConnectorService.getDocument(id).getContent();
    }


}
