package org.app.timetracker.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.app.timetracker.Logger;
import org.app.timetracker.data.Category;
import org.app.timetracker.data.CurrentTasks;
import org.app.timetracker.data.Task;
import org.app.timetracker.data.TaskStatus;

public class FileUtil {

  private static final String file_path = "time-tracker.csv";

  // Read the file, lines, get Tasks.
  public CurrentTasks getAllTasks() throws IOException {

    Collection<Task> currentTasks = new HashSet<>();

    Path filePath = Path.of(file_path);
    if(Files.notExists(filePath)){
      Files.createFile(filePath);
    }

//     Files.lines(filePath).forEach(line -> {
//       String[] values = line.split(",");
//       if(values.length >= 5){
//         Task task = new Task(values[0], new Category(values[1]), LocalDateTime.parse(values[2]), LocalDateTime.parse(values[3]),
//             TaskStatus.valueOf(values[4]));
//         currentTasks.add(task);
//       }
//     });

   Map<String, Task> taskMap = Files.lines(filePath)
//       .peek(System.out::println)
       .map(line -> line.split(","))
       .filter(value -> value.length == 5)
       .filter(value -> TaskStatus.valueOf(value[4]) == TaskStatus.COMPLETED)
        .map(values -> new Task(values[0], Objects.isNull(values[1]) ? null : new Category(values[1]),  Objects.isNull(values[2]) ? null :LocalDateTime.parse(values[2]),  Objects.isNull(values[3]) || values[3].isBlank() || "null".equals(values[3]) ? null :LocalDateTime.parse(values[3]),
            Objects.isNull(values[4]) ? null : TaskStatus.valueOf(values[4]))).collect(Collectors.toMap(Task::getTaskName,
            Function.identity()));

     return new CurrentTasks(taskMap);
  }



  // Write task to the file.
  public void saveToFile(CurrentTasks tasks) throws IOException {
    Path filePath = Path.of(file_path);
    if(Files.notExists(filePath)){
      Files.createFile(filePath);
    }

    List<String> output = tasks.getCurrentTaskList().values().stream().map(t -> t.getCsvFormatValue()).toList();

    Files.write(filePath, output);
  }

}
