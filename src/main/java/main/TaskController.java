package main;

import main.modul.Task;
import main.modul.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @GetMapping("/tasks/")
    public List<Task> list(){
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task task : taskIterable){
            taskArrayList.add(task);
        }
        return taskArrayList;
    }
    @PostMapping("/tasks/")
    public int addTask(Task task){
        Task newTask = taskRepository.save(task);
        return newTask.getId();
    }
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity delTask(@PathVariable int id){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }taskRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @PatchMapping ("/tasks/{id}")
    public ResponseEntity RefTask(@PathVariable int id, boolean done){
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(!optionalTask.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Task newTask = new Task();
        newTask.setId(id);
        newTask.setDone(done);
        taskRepository.save(newTask);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id){

        Optional<Task> optionalTask = taskRepository.findById(id);
        if(!optionalTask.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalTask.get(), HttpStatus.OK);
    }
}

