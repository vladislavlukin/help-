package main;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {
    @GetMapping("/tasks/")
    public List<Task> list(){
        return SoTime.getAllTasks();
    }
    @PostMapping("/tasks/")
    public int addTask(Task task){
        return SoTime.addTask(task);
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id){
        Task task = SoTime.getTask(id);
        if(task == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }
}

