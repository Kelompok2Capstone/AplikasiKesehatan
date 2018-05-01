package com.kelompok2.android.aplikasikesehatan;

/**
 * Created by User on 28/04/2018.
 */

public class InfoModel {
    private String nama;
    private String deskripsi;
    private String pengobatan;
    private String penyebab;
    private String jenis;
    private String image;

    public InfoModel(){}

    public InfoModel(String nama, String deskripsi, String pengobatan, String penyebab, String jenis, String image) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.pengobatan = pengobatan;
        this.penyebab = penyebab;
        this.image = image;
        this.jenis = jenis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPengobatan() {
        return pengobatan;
    }

    public void setPengobatan(String pengobatan) {
        this.pengobatan = pengobatan;
    }

    public String getPenyebab() {
        return penyebab;
    }

    public void setPenyebab(String penyebab) {
        this.penyebab = penyebab;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

//    public static String getTitleKey() {
//        return TITLE_KEY;
//    }
//
//    public static String getImageKey() {
//        return IMAGE_KEY;
//    }

//    public static Intent starter(Context context, String title, @DrawableRes int imageResId) {
//        Intent detailIntent = new Intent(context, DetailActivity.class);
//        detailIntent.putExtra(TITLE_KEY, title);
//        detailIntent.putExtra(IMAGE_KEY, imageResId);
//
//        return detailIntent;
//    }
}
