package org.example.todolist.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.todolist.model.Task;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final String FILE_PATH = "tasks.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Task> readTasksFromFile(){
        try{
            File file = new File(FILE_PATH);
            if(!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, new TypeReference<List<Task>>() {
            });
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeTasksToFile(List<Task> taskList){
        try{
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), taskList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks(){
        return readTasksFromFile();
    }

    public void addTasks(Task task){
        List<Task> taskList = readTasksFromFile();
        task.setId(generateNewId(taskList));
        taskList.add(task);
        writeTasksToFile(taskList);
    }

    public List<Task> findTasksByUserId(int userID){
        return readTasksFromFile().stream()
                .filter(task -> task.getUserID() == userID).collect(Collectors.toList());

    }

    public void updateTaskDoneStatus(int taskID, Task updatedTask){
        List<Task> taskList = readTasksFromFile();
                taskList.stream().filter(task -> task.getId() == taskID)
                .forEach(task -> task.setDone(updatedTask.getDone()));
        writeTasksToFile(taskList);
    }


    public List<Task> getDoneTasksByUserId(int userID, boolean isDone){
        return readTasksFromFile().stream()
                .filter(task -> task.getUserID() == userID && task.getDone())
                .collect(Collectors.toList());
    }

    public List<Task> getUndoneTasksByUserId(int userID, boolean isDone){
        return readTasksFromFile().stream()
                .filter(task -> task.getUserID() == userID && task.getDone() == isDone)
                .collect(Collectors.toList());
    }

    private int generateNewId(List<Task> taskList){
        return taskList.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0)+1;
    }
}
