package com.example.user.armonia.list;

public class ListClubPage {
    private int image;//외부에서 이미지 가져올떄는 string으로 변경해야함
    private String title;
    private String writer;
    private String date;
    private String contents;

    public ListClubPage(int image, String title, String writer, String date, String contents) {
        this.image = image;
        this.title = title;
        this.writer = writer;
        this.date= date;
        this.contents = contents;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
