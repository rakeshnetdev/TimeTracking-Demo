package org.app.timetracker.util;

import java.util.Arrays;
import java.util.Objects;
import org.app.timetracker.Logger;
import org.app.timetracker.data.Category;

public class ArgUtil {

public Arg parseArgs(String[] args){
  //System.out.println("args = " + Arrays.toString(args));
  if(!Validate(args)) throw new RuntimeException("Arguments passed are wrong");


  String command = args[0] ;
  String taskName = args[1];
  Arg arg = new Arg();
  arg.setTaskName(taskName);
  arg.setCategoryName(args.length > 2 ? args[2] : Category.No_Category);

  switch (command) {
    case "Start" -> arg.setCommand(Commands.TASK_START);
    case "Stop" -> arg.setCommand(Commands.TASK_STOP);
    case "Report" -> {
      switch (taskName) {
        case "Task" -> arg.setCommand(Commands.REPORT_TASK);
        default -> {
          if (Objects.equals(taskName, "Category")) {
            arg.setCommand(Commands.REPORT_CATEGORY);
          }
        }
      }
    }
    default -> arg.setCommand(null);
  }
  return arg;
}

  private boolean Validate(String[]  args){
    if(args != null && args.length < 2){
      Logger.Log("Minimum 2 arguments are required");
      return false;
    }
    return true;
  }
}
