package org.app.timetracker.data;

public class Category {

  public static final String No_Category = "NONE";
 private String CategoryName;

  public String getCategoryName() {
    return CategoryName;
  }

  public void setCategoryName(String categoryName) {
    CategoryName = categoryName;
  }

  public Category(String categoryName) {
    CategoryName = categoryName;
  }

  @Override
  public String toString() {
    return CategoryName  ;
  }
}
