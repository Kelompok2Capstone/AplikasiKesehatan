package com.kelompok2.android.aplikasikesehatan;

import java.io.Serializable;

/**
 * Created by User on 25/04/2018.
 */

public class PostingModel implements Serializable {
    public String title;
    public String desc;
    public String email;


    public PostingModel(String tvTitle, String tvPost, String email) {
            this.title = tvTitle;
            this.desc= tvPost;
            this.email=email;


    }

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
