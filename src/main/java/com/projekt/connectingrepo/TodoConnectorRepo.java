package com.projekt.connectingrepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoConnectorRepo extends JpaRepository<TodoConnector, Long> {

    List<TodoConnector> findTodoConnectorByHouseId(Long houseId);
    TodoConnector findTodoConnectorById(Long generalId);
}


