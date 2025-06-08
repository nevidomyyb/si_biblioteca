package com.pedro.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class DataUtils {
    


    public static Date stringToSqlDate(String dataStr) {
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date utilDate = formatter.parse(dataStr);
            return new Date(utilDate.getTime());
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDataSqlAtual(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = formatter.format(new java.util.Date());
        return stringToSqlDate(dataAtual);
    }

    public static Date getFuturaSqlDate(int dias){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, dias);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dataFutura = formatter.format(calendar.getTime());
        return stringToSqlDate(dataFutura);
    }

}
