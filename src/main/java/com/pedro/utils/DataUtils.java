package com.pedro.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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

}
