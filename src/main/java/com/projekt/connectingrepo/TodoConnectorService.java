package com.projekt.connectingrepo;

import com.projekt.categories.Category;
import com.projekt.categories.CategoryRepository;
import com.projekt.documents.Document;
import com.projekt.documents.DocumentRepository;
import com.projekt.houses.House;
import com.projekt.houses.HouseRepository;
import com.projekt.todos.Todo;
import com.projekt.todos.TodoDTO;
import com.projekt.todos.TodoDescription;
import com.projekt.todos.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoConnectorService {

    private final TodoConnectorRepo todoConnectorRepo;
    private final DocumentRepository documentRepository;
    private final HouseRepository houseRepository;
    private final TodoRepository todoRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TodoConnectorService(TodoConnectorRepo todoConnectorRepo, DocumentRepository documentRepository, HouseRepository
            houseRepository, TodoRepository todoRepository, CategoryRepository categoryRepository) {
        this.todoConnectorRepo = todoConnectorRepo;
        this.documentRepository = documentRepository;
        this.houseRepository = houseRepository;
        this.todoRepository = todoRepository;
        this.categoryRepository = categoryRepository;
    }

    public void updateTodosInHouse(Long houseId, Long categoryId, List<TodoDTO> todos){
        List<TodoConnector> list = todoConnectorRepo.findTodoConnectorByHouseId(houseId);
        List<TodoConnector> listSelectedByCategory = list.stream()
                .filter(elem -> elem.getTodo().getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
        int i=0;
        for(TodoConnector todoConnector : listSelectedByCategory){
            todoConnector.setCompleted(todos.get(i).isCompleted());
            i++;
        }
        todoConnectorRepo.saveAll(list);
    }

    public void extractCSVToTodos(MultipartFile CSVFile, Long houseId, Long categoryId){
        BufferedReader br;
        try {
            String line;
            InputStream is = CSVFile.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
               addTodoToHouse(new TodoDescription(line), houseId, categoryId);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeTodoFromHouse(Long id){
        TodoConnector todoConnector = todoConnectorRepo.findTodoConnectorById(id);
        todoConnectorRepo.delete(todoConnector);
        todoRepository.delete(todoConnector.getTodo());
    }

    public List<TodoConnector> getAll(){
        return todoConnectorRepo.findAll();
    }

    public List<TodoConnector> getHouseTodosByHouseIdAndCategory(Long houseId, Long categoryId){
        List<TodoConnector> list = todoConnectorRepo.findTodoConnectorByHouseId(houseId);
        return list.stream()
                .filter(elem -> elem.getTodo().getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }

    public void addTodoToHouse(TodoDescription todoDescription, Long houseId, Long categoryId){
        Optional<House> house = houseRepository.findById(houseId);
        Todo todo = new Todo(todoDescription.getDescription(), categoryRepository.findById(categoryId).orElse(null));
        todoRepository.save(todo);
        todoConnectorRepo.save(new TodoConnector(house.get(), todo, false, documentRepository.findById(Long.parseLong("1")).orElse(null)));
    }




    public boolean saveDocument(MultipartFile file, Long generalId) throws IOException {
        System.out.println(file.getName());
        Document doc = new Document();
        doc.setName(file.getOriginalFilename());
        doc.setContent(file.getBytes());

        Document foundDocument = documentRepository.findFirstByContent(doc.getContent());
        if(foundDocument == null){
            documentRepository.save(doc);
        }

        TodoConnector htsToSave = todoConnectorRepo.findTodoConnectorById(generalId);
        htsToSave.setDocument(documentRepository.findFirstByContent(doc.getContent()));
        todoConnectorRepo.save(htsToSave);
        return true;
    }

    public Document getDocument(Long id){
        System.err.println(todoConnectorRepo.findTodoConnectorById(id).getDocument().getName());
        return todoConnectorRepo.findTodoConnectorById(id).getDocument();
    }


    public void createTodoConnector(House house, List<TodoDTO> todosWithStatuses){
        if(todosWithStatuses!=null){
            List<TodoConnector> list = todosWithStatuses.stream()
                    .filter(TodoDTO::isChecked)
                    .map(t->new TodoConnector(house, new Todo(t.getId(), t.getDescription(), new Category()), false, documentRepository.findById((Long.parseLong("1"))).orElse(null)))
                    .collect(Collectors.toList());

            todoConnectorRepo.saveAll(list);
        } else{
            todoConnectorRepo.save(new TodoConnector(house));
        }

    }

}