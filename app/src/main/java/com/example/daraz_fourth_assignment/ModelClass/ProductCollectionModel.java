package com.example.daraz_fourth_assignment.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductCollectionModel {
    @SerializedName("tbl_product")
    @Expose
    List<DarazProductModelClass> darazProductModelClasses;

    public List<DarazProductModelClass> darazProductModelClasses() {
        return darazProductModelClasses;
    }

    public void setCollectionModelList(List<DarazProductModelClass> darazProductModelClasses) {
        this.darazProductModelClasses = darazProductModelClasses;
    }

    @Override
    public String toString() {
        return "ProductCollectionModel{" +
                "darazProductModelClasses=" + darazProductModelClasses +
                '}';
    }

}

