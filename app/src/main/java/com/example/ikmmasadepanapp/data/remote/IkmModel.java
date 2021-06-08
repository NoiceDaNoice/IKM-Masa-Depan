package com.example.ikmmasadepanapp.data.remote;

public class IkmModel {

    String id;
    String name;
    String company;
    String phone;
    String type;
    String email;
    String description;

    public IkmModel(){

    }

    public IkmModel(String id, String name, String company, String phone, String type, String email, String description) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.type = type;
        this.email = email;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
