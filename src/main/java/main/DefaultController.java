package main;

import main.modul.Task;
import main.modul.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

@Controller
public class DefaultController {
    @Autowired
    TaskRepository taskRepository;
    public String timeNow(){
        LocalDateTime time = LocalDateTime.now();
        return time.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }
    @RequestMapping("/")
    public String index(Model model){
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task task : taskIterable){
            taskArrayList.add(task);
        }
        model.addAttribute("timeNow", timeNow());
        model.addAttribute("tasks", taskArrayList);
        return "index";
    }

}
