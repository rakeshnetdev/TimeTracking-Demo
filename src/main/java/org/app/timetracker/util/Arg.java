package org.app.timetracker.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import org.app.timetracker.Logger;
import org.app.timetracker.data.CurrentTasks;

public class Arg {
  private String TaskName;
  private Commands Command;
  private String CategoryName;

  public String getTaskName() {
    return TaskName;
  }

  public void setTaskName(String taskName) {
    TaskName = taskName;
  }

  public Commands getCommand() {
    return Command;
  }

  public void setCommand(Commands command) {
    Command = command;
  }

  public String getCategoryName() {
    return CategoryName;
  }

  public void setCategoryName(String categoryName) {
    CategoryName = categoryName;
  }

}
