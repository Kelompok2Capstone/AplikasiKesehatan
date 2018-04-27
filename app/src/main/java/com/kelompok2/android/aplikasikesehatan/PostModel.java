package com.kelompok2.android.aplikasikesehatan;

import java.io.Serializable;

/**
 * Created by User on 21/04/2018.
 */

public class PostModel implements Serializable {
    //Deklarasi variable

    public String title;
    public String desc;
    public String email;
//    public String key;

    //konstruktor kosong *diperlukan oleh firebase
    public PostModel() {
    }
    //konstruktor
    public PostModel(String title, String desc, String email) {

        this.title = title;
        this.desc = desc;
        this.email = email;
//        this.key=key;
    }
    //getter setter semua variable

//    public String getKey() {
//        return key;
//    }
//    public void setKey(String key) {
//        this.key = key;
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
