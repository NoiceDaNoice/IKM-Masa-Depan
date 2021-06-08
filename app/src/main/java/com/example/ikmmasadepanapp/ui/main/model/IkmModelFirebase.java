package com.example.ikmmasadepanapp.ui.main.model;

import android.os.Parcel;
import android.os.Parcelable;

public class IkmModelFirebase implements Parcelable {

    String id;
    String name;
    String company;
    String phone;
    String type;
    String email;
    String description;

    public IkmModelFirebase(){

    }
    public IkmModelFirebase(String id, String name, String company, String phone, String type, String email, String description) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.phone = phone;
        this.type = type;
        this.email = email;
        this.description = description;
    }

    protected IkmModelFirebase(Parcel in) {
        id = in.readString();
        name = in.readString();
        company = in.readString();
        phone = in.readString();
        type = in.readString();
        email = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(company);
        dest.writeString(phone);
        dest.writeString(type);
        dest.writeString(email);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<IkmModelFirebase> CREATOR = new Creator<IkmModelFirebase>() {
        @Override
        public IkmModelFirebase createFromParcel(Parcel in) {
            return new IkmModelFirebase(in);
        }

        @Override
        public IkmModelFirebase[] newArray(int size) {
            return new IkmModelFirebase[size];
        }
    };

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
