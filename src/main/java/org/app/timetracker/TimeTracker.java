package org.app.timetracker;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import org.app.timetracker.data.CurrentTasks;
import org.app.timetracker.util.Arg;
import org.app.timetracker.util.ArgUtil;
import org.app.timetracker.util.FileUtil;


public class TimeTracker {

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter Command, task name, category name (optional) or exit to end");
    while (true){

      var userInput = scanner.nextLine();
      if(userInput.trim().equalsIgnoreCase("exit")) {
        break;
      }


      Logger.Log("Input: " + userInput);
      String[] inputs = userInput.split(" ");
      taskProcessor(inputs);

    }
    scanner.close();
  }

  private static void taskProcessor(String[] args) throws Exception {
//    System.out.println("args = " + Arrays.toString(args));

    ArgUtil argUtil = new ArgUtil();
    Arg arguments = argUtil.parseArgs(args);

    FileUtil fUtil = new FileUtil();
    CurrentTasks currentTask = fUtil.getAllTasks();
//    taskList.startTask("Coding", "Work");
//    Logger.Log(taskList.toString());
//    Logger.Log(currentTask.toString());
    switch (arguments.getCommand()){
      case TASK_START -> {
        // Create a new task add it to the Collection (map)
        currentTask.startTask(arguments.getTaskName(), arguments.getCategoryName());
        fUtil.saveToFile(currentTask);
        Logger.Log(currentTask.toString());
      }
      case TASK_STOP -> {
        currentTask.stopTask(arguments.getTaskName());
        fUtil.saveToFile(currentTask);
        Logger.Log(currentTask.toString());
      }
      case REPORT_TASK -> {
       Logger.Log(currentTask.reportTaskDuration());
      }
      case REPORT_CATEGORY -> {
        Logger.Log(currentTask.reportCategoryDuration());
      }
      default -> throw new IllegalStateException("Unexpected value: " + arguments.getCommand());
    }


  }

}
