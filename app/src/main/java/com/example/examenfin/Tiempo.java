package com.example.examenfin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tiempo {


    private Date fecha;
    private int tempmax,tempmin;
    private String estadocielo;


    public Tiempo() {}

    public String getFecha() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
    }

    public void setFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.fecha = sdf.parse(fecha);
        } catch (ParseException e) {
            this.fecha = null;
        }
    }




    public String getEstadocielo() {
        return estadocielo;
    }

    public void setEstadocielo(String estadocielo) {
        this.estadocielo = estadocielo;
    }



    public int getTempmin() {
        return tempmin;
    }

    public void setTempmin(int tempmin) {
        this.tempmin = tempmin;
    }

    public int getTempmax() {
        return tempmax;
    }

    public void setTempmax(int tempmax) {
        this.tempmax = tempmax;
    }
}
