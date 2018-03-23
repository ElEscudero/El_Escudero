package com.local.el_escudero.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ANA on 22/03/2018.
 */

public class Carrito implements Parcelable {
    String nombre, precio;

    protected Carrito(Parcel in) {
        nombre = in.readString();
        precio = in.readString();
    }

    public static final Creator<Carrito> CREATOR = new Creator<Carrito>() {
        @Override
        public Carrito createFromParcel(Parcel in) {
            return new Carrito(in);
        }

        @Override
        public Carrito[] newArray(int size) {
            return new Carrito[size];
        }
    };


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Carrito(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(precio);
    }

}
