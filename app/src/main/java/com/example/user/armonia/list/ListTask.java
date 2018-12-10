package com.example.user.armonia.list;

public class ListTask {
    private String TaskTitle;
    private String TaskContent;


    public String getTask_Title() {return TaskTitle; }

    public void setTask_Title(String task_title) {
        this.TaskTitle = task_title;
    }

    public String getTask_Content() {
        return TaskContent;
    }

    public void setTask_Content(String task_content) {
        this.TaskContent = task_content;
    }


    public ListTask(String task_title, String task_content){
        this.TaskTitle = task_title;
        this.TaskContent = task_content;
    }
}
