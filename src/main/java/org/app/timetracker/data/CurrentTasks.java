package org.app.timetracker.data;

import static javax.management.Query.plus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.app.timetracker.Logger;

public class CurrentTasks {

  public Map<String, Task> currentTaskList = new HashMap<>();

  public CurrentTasks(Map<String, Task> taskMap) {
    currentTaskList = taskMap;
  }

  public Map<String, Task> getCurrentTaskList() {
    return currentTaskList;
  }

  public void setCurrentTaskList(
      Map<String, Task> currentTaskList) {
    this.currentTaskList = currentTaskList;
  }

  public void startTask(String taskName, String categoryName) throws Exception {
    var category =  categoryName == null || categoryName.isBlank() ? null: new Category(categoryName);
    var task = new Task(taskName, category);
    if(currentTaskList.containsKey(taskName)){
      throw new Exception("Task has already started!");
    } else {
      currentTaskList.put(task.getTaskName(), task);
    }
  }

  @Override
  public String toString() {
   return getCurrentTaskList().values().stream().map(v -> String.format("TaskName: %s | Category Name: %s | Status: %s | StartTime: %s | EndTIme: %s", v.getTaskName(),v.getCategory(), v.getStatus(), v.getStartTime(), v.getEndTime())).collect(
        Collectors.joining("\n"));

  }

  public void stopTask(String taskName) throws Exception {
    if(getCurrentTaskList().containsKey(taskName)){
      var task = getCurrentTaskList().get(taskName);
      task.setEndTime(LocalDateTime.now());
      task.setStatus(TaskStatus.COMPLETED);
    } else {
      Logger.Log("Task not found!");
    }
  }

  public String reportTaskDuration(){
    if(getCurrentTaskList().values().size() > 0){
    return  getCurrentTaskList().values().stream().filter(task -> task.getStatus() == TaskStatus.COMPLETED)
          .map(task -> "Task Name: " + task.getTaskName() + " Duration: " + task.getDuration().toMillis())
          .collect(Collectors.joining("\n"));
    }
    return null;
  }

  public String reportCategoryDuration(){

    Map<String,Duration> categoryReport = new HashMap<>();
    if(getCurrentTaskList().values().size() > 0){
        getCurrentTaskList().values().stream().filter(task -> task.getStatus() == TaskStatus.COMPLETED)
          .forEach(task -> {
            Duration categoryDuration =  categoryReport.getOrDefault(task.getCategory().getCategoryName(), Duration.ZERO);
            categoryReport.put(task.getCategory().getCategoryName(), categoryDuration.plus(task.getDuration()));
          });

      return categoryReport.entrySet().stream().map(task -> "Category Name: " + task.getKey() + " Duration: " + task.getValue().toMillis())
          .collect(Collectors.joining("\n"));
    }
    return null;
  }
}
