/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employs_control.Generic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

/**
 *
 * @author Eduardo Carbajal Reyes
 */
public class Utils {

    public static String getHour() {
        String hora = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            SimpleTimeZone stf = new SimpleTimeZone(0, "GMT");
            sdf.setTimeZone(stf);

            Calendar cal1 = Calendar.getInstance();

            Date dt = sdf.parse(cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND));

            hora = sdf.format(dt);

        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return hora;
    }
    
    public static String getHourGreeting() {
        String hora = null;
        String greeting = null;
        
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            SimpleTimeZone stf = new SimpleTimeZone(0, "GMT");
            sdf.setTimeZone(stf);

            Calendar cal1 = Calendar.getInstance();

            Date dt = sdf.parse(cal1.get(Calendar.HOUR_OF_DAY) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND));

            int hour = cal1.get(Calendar.HOUR_OF_DAY);
            
            if(hour >= 0 && hour <12){
                greeting = "Buenos días la hora es " ;
            }else if(hour >= 12 && hour < 19){
                greeting = "Buenas tardes la hora es " ;
            }else if(hour >= 19 && hour <= 24){
                greeting = "Buenas noches la hora es " ;
            }
            
            hora = sdf.format(dt);

        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return greeting + hora;
    }

    public static String getFechaLetra() {
        Calendar now = Calendar.getInstance();
        // Array con los dias de la semana

        String[] strDays = new String[]{"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
        String[] strMonths = new String[]{"enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};

        return strDays[now.get(Calendar.DAY_OF_WEEK) - 1] + ", " + (now.get(Calendar.DATE))
                + " de "
                + strMonths[now.get(Calendar.MONTH)]
                + " del "
                + now.get(Calendar.YEAR);
    }
    
    public static String getFecha(){
       Calendar now = Calendar.getInstance();
       String dia = (now.get(Calendar.DATE) <= 9) ? "0"+now.get(Calendar.DATE) : now.get(Calendar.DATE)+"";
       String mes = (now.get(Calendar.MONTH)+1 <= 9) ? "0"+(now.get(Calendar.MONTH)+1): (now.get(Calendar.MONTH)+1)+"";
       
       return (dia+"/"+mes+"/"+now.get(Calendar.YEAR));
    }

    public static java.sql.Date getCurrentDate() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Date(today.getTime());
    }

    
}
