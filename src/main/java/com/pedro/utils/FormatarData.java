package com.pedro.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarData {
    
    public static Date formatarData(String data) throws ParseException{
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        return formatador.parse(data);
    }

}
