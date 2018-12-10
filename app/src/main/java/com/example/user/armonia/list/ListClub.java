package com.example.user.armonia.list;

//내 동아리에서 동아리 목록 띄우는 리스트뷰 들어갈 클럽 item
public class ListClub {
    private int image_club;//외부에서 이미지 가져올떄는 string으로 변경해야함
    private String club_name;
    private String club_category;
    private String club_info;


    public ListClub(int image_club, String club_name, String club_category, String club_info) {
        this.image_club = image_club;
        this.club_name = club_name;
        this.club_category = club_category;
        this.club_info = club_info;

    }

    public int getImage_club() {
        return image_club;
    }

    public void setImage_club(int image_club) {
        this.image_club = image_club;
    }

    public String getClub_name() {
        return club_name;
    }

    public void setClub_name(String club_name) {
        this.club_name = club_name;
    }

    public String getClub_category() {
        return club_category;
    }

    public void setClub_category(String club_category) {
        this.club_category = club_category;
    }

    public String getClub_info() { return club_info; }

    public void setClub_info(String club_info) { this.club_info = club_info; }
}
