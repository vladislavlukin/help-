package main;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Task {
    private int id;
    private LocalDateTime time;
    private String name;
    private String text;
}
