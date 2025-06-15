package org.example.todolist.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.todolist.model.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final String FILE_PATH = "users.json";

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<User> readUsersFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) return new ArrayList<>();
            return objectMapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void writeUsersToFile(List<User> userList) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(FILE_PATH), userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return readUsersFromFile();
    }

    public boolean getUserByEmail(String email){
        return readUsersFromFile().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }


    /*public User getUserByEmail(String email) {
        return readUsersFromFile().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }*/

    public User getUserById(int id){
        return readUsersFromFile().stream()
                .filter(user -> user.getID() == id)
                .findFirst()
                .orElse(null);
    }

    public Boolean checkLogin(String email, String password){
        return readUsersFromFile().stream()
                .anyMatch(user -> user.getEmail().equals(email) && user.getPassword().equals(password));
    }

    public void addUser(User user){
        List<User>  userList = readUsersFromFile();
        user.setID(generateNewId(userList));
        userList.add(user);
        writeUsersToFile(userList);
    }

    public void deleteUser(int id){
        List<User> users = readUsersFromFile();
        users.removeIf(user -> user.getID() == id);
        writeUsersToFile(users);
    }

    private int generateNewId(List<User> userList){
        return userList.stream()
                .mapToInt(User :: getID)
                .max()
                .orElse(0) + 1;
    }
}
