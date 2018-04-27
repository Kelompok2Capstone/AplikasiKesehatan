package com.kelompok2.android.aplikasikesehatan;

/**
 * Created by User on 28/04/2018.
 */

public class UserInformation {
    public String name;
    public String address;
    public String phone;
    public String pekerjaan;

    public UserInformation(){

    }
    public UserInformation(String name, String address, String phone, String pekerjaan) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.pekerjaan = pekerjaan;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }
}

