package com.example.daraz_fourth_assignment.ModelClass;

public class FlashModelClass {
    private int saleImage;
    private String amount;

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    private String sold;

    public int getSaleImage() {
        return saleImage;
    }

    public void setSaleImage(int saleImage) {
        this.saleImage = saleImage;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public FlashModelClass(int saleImage, String amount, String sold) {
        this.saleImage = saleImage;
        this.amount = amount;
        this.sold = sold;
    }

}


