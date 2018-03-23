package com.local.el_escudero.model;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by preto on 19/03/2018.
 */

public class Product implements Parcelable{

    private String description;
    private String category;
    private String product;
    private float price;
    private String photo;
    private boolean selected;

    public Product(String description, String category, String product, float price, String photo, boolean selected) {
        this.description = description;
        this.category = category;
        this.product = product;
        this.price = price;
        this.photo = photo;
        this.selected = selected;
    }


    public Product(String description, String category, String product, float price, String photo) {
        this.description = description;
        this.category = category;
        this.product = product;
        this.price = price;
        this.photo = photo;
    }

    public Product(Parcel parcel) {
    }

//  TODO: Getters & Setters


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(description);
        parcel.writeString(category);
        parcel.writeFloat(price);
        parcel.writeString(photo);
        parcel.writeString(String.valueOf(selected));

    }


    public static final Parcelable.Creator<Product> CREATOR = new
            Parcelable.Creator<Product>(){
                @Override
                public Product createFromParcel(Parcel parcel) {
                    return new Product(parcel);
                }

                @Override
                public Product[] newArray(int i) {
                    return new Product[i];
                }
            };
}
