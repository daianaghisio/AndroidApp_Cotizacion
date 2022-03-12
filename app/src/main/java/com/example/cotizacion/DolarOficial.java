package com.example.cotizacion;

import android.os.Parcel;
import android.os.Parcelable;


/** This class represents "DolarOficial".
 * @author Daiana Ghisio
 */

public class DolarOficial implements Parcelable {

    private int id;
    private double compra;
    private double venta;
    private long fecha;

    public DolarOficial() {
    }

    public DolarOficial(int id, double compra, double venta, long fecha){
        this.id=id;
        this.compra=compra;
        this.venta=venta;
        this.fecha=fecha;
    }

    protected DolarOficial(Parcel in) {
        id = in.readInt();
        compra = in.readDouble();
        venta = in.readDouble();
        fecha = in.readLong();
    }

    public static final Creator<DolarOficial> CREATOR = new Creator<DolarOficial>() {
        @Override
        public DolarOficial createFromParcel(Parcel in) {
            return new DolarOficial(in);
        }

        @Override
        public DolarOficial[] newArray(int size) {
            return new DolarOficial[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCompra() {
        return compra;
    }

    public void setCompra(double compra) {
        this.compra = compra;
    }

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "DolarOficial{" +
                "id=" + id +
                ", compra=" + compra +
                ", venta=" + venta +
                ", fecha=" + fecha +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(compra);
        dest.writeDouble(venta);
        dest.writeLong(fecha);
    }
}
