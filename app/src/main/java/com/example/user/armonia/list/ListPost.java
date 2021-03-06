package com.example.user.armonia.list;

// 동아리 게시판 리스티뷰에 나타나는 post
public class ListPost {

    private String post_Title;
    private String post_Name;
    private String post_Date;
    private String post_content;

    public String getPost_Title() {
        return post_Title;
    }

    public void setPost_Title(String post_Title) {
        this.post_Title = post_Title;
    }

    public String getPost_Name() {
        return post_Name;
    }

    public void setPost_Name(String post_Name) {
        this.post_Name = post_Name;
    }

    public String getPost_Date() {
        return post_Date;
    }

    public void setPost_Date(String post_Date) {
        this.post_Date = post_Date;
    }

    public String getPost_content(){return post_content;};

    public void setPost_content(String post_content) { this.post_content = post_content; }

    public ListPost(String post_Title, String post_Name, String post_Date, String post_content){
        this.post_Date = post_Date;
        this.post_Name = post_Name;
        this.post_Title = post_Title;
        this.post_content = post_content;
    }
}
