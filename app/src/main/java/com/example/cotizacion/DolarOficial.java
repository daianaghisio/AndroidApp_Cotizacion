package com.example.cotizacion;

import java.util.Date;

public class DolarOficial {

    private int id;
    private double compra;
    private double venta;
    private Date fecha;

    public DolarOficial() {
    }

    public DolarOficial(int id, double compra, double venta, Date fecha){
        this.id=id;
        this.compra=compra;
        this.venta=venta;
        this.fecha=fecha;
    }

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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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
}
