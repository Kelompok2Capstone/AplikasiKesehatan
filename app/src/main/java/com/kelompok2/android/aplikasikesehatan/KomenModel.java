package com.kelompok2.android.aplikasikesehatan;

import java.io.Serializable;

/**
 * Created by User on 26/04/2018.
 */

public class KomenModel implements Serializable {

    public String komen;
    public String email;

    public KomenModel() {
    }
    //konstruktor
    public KomenModel(String kom, String email) {

        this.komen = kom;
        this.email = email;
    }
    //getter setter semua variable
    public String getKomen() {
        return komen;
    }

    public void setKomen(String kom) {
        this.komen = kom;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
