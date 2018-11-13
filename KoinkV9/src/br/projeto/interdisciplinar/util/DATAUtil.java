package br.projeto.interdisciplinar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DATAUtil {
    
   public static Date transforma_String_Em_Data(String dateInString){
       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       try {

            Date date = formatter.parse(dateInString.replaceAll("Z$", "+0000"));
            System.out.println(formatter.format(date));
            return date; 

        } catch (ParseException e) {
            System.out.println("Não foi possível transformar a string em data. " + e);
        }
       // tratar esse erro
       return null;
   }
    
}
