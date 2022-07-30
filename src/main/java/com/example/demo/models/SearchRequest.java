package com.example.demo.models;

import javax.validation.constraints.NotBlank;

public class SearchRequest {

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private long id;

    @NotBlank(message = "aa is mandatory")
    private String aa;

    private String bb;

    public SearchRequest(String aa, String bb) {
        this.aa = aa;
        this.bb = bb;
    }


    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }

    public String getBb() {
        return bb;
    }

    public void setBb(String bb) {
        this.bb = bb;
    }

    @Override
    public String toString() {
        return aa;
    }
}