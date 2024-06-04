package com.example.membermanagement;

public class Member {
    private String name;
    private String description;
    private String avatar;

    public Member() {
    }

    public Member(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Member(String name, String description, String avatar) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
