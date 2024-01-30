package org.app.timetracker.data;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task {

  private String TaskName;
  private Category Category;

  private LocalDateTime StartTime;

  private LocalDateTime EndTime;
  private TaskStatus Status;

  public String getTaskName() {
    return TaskName;
  }

  public Duration getDuration() {
    return Duration.between(getStartTime(), getEndTime());
  }

  public void setTaskName(String taskName) {
    TaskName = taskName;
  }

  public org.app.timetracker.data.Category getCategory() {
    return Category;
  }

  public void setCategory(org.app.timetracker.data.Category category) {
    Category = category;
  }

  public LocalDateTime getStartTime() {
    return StartTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    StartTime = startTime;
  }

  public LocalDateTime getEndTime() {
    return EndTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    EndTime = endTime;
  }

  public TaskStatus getStatus() {
    return Status;
  }

  public void setStatus(TaskStatus status) {
    Status = status;
  }


  public Task(String taskName, org.app.timetracker.data.Category category) {
    TaskName = taskName;
    Category = category;
    StartTime = LocalDateTime.now();
    Status = TaskStatus.PENDING;
  }

  public Task(String taskName, org.app.timetracker.data.Category category, LocalDateTime startTime,
      LocalDateTime endTime, TaskStatus status) {
    TaskName = taskName;
    Category = category;
    StartTime = startTime;
    EndTime = endTime;
    Status = status;
  }

  public String getCsvFormatValue() {
    return TaskName +
        "," + Category.toString() +
        "," + StartTime +
        "," + EndTime +
        "," + Status;
  }

  @Override
  public String toString() {
    return "Task{" +
        "TaskName='" + TaskName + '\'' +
        ", Category=" + Category +
        ", StartTime=" + StartTime +
        ", EndTime=" + EndTime +
        ", Status=" + Status +
        '}';
  }
}

