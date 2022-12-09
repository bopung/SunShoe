package com.example.sunshoe;

import java.io.Serializable;

public class ordertrans implements Serializable {
    private int id;
    private String product_name;
    private int product_price;
    private int product_size;
    private String product_image;
    private String nama;
    private String nomorhp;
    private String alamat;
    private String norek;
    private String bukti;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_size() {
        return product_size;
    }

    public void setProduct_size(int product_size) {
        this.product_size = product_size;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorhp() {
        return nomorhp;
    }

    public void setNomorhp(String nomorhp) {
        this.nomorhp = nomorhp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNorek() {
        return norek;
    }

    public void setNorek(String norek) {
        this.norek = norek;
    }

    public String getBukti() {
        return bukti;
    }

    public void setBukti(String bukti) {
        this.bukti = bukti;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ordertrans(int id, String product_name, int product_price, int product_size, String product_image, String nama, String nomorhp, String alamat, String norek, String bukti, String status) {
        this.id = id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_size = product_size;
        this.product_image = product_image;
        this.nama = nama;
        this.nomorhp = nomorhp;
        this.alamat = alamat;
        this.norek = norek;
        this.bukti = bukti;
        this.status = status;
    }
}
