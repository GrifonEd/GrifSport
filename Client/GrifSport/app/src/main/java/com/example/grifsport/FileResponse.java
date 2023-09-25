package com.example.grifsport;

import android.graphics.pdf.PdfDocument;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FileResponse {

    private int id;
    private String title;
    private PdfDocument pdf;

    public FileResponse() {
    }

    public FileResponse(int id, String title, PdfDocument pdf) {
        this.id = id;
        this.title = title;
        this.pdf = pdf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PdfDocument getPdf() {
        return pdf;
    }

    public void setPdf(PdfDocument pdf) {
        this.pdf = pdf;
    }
}
