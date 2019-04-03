/*
 * Licencia GPL.
 * Desarrollado por: William Sánchez
 * El software se proporciona "TAL CUAL", sin garantía de ningún tipo,
 * expresa o implícita, incluyendo pero no limitado a las garantías de
 * comerciabilidad y adecuación para un particular son rechazadas.
 * En ningún caso el autor será responsable por cualquier reclamo,
 * daño u otra responsabilidad, ya sea en una acción de contrato,
 * agravio o cualquier otro motivo, de o en relación con el software
 * o el uso u otros tratos en el software.
 * 
 */
package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author William Sanchez
 */
public class DateOperator {
    public static boolean isDate(String stringDate, String format){
        int dd,mm,yy;
        boolean leapyear=false;
        switch(format){
            case "ddMMyy":
            case "ddMMyyyy":
                dd =Integer.parseInt(stringDate.substring(0, 2));
                mm =Integer.parseInt(stringDate.substring(2, 4));
                yy =Integer.parseInt(stringDate.substring(4));
            break;
            case "dd-MM-yyyy":
            case "dd/MM/yyyy":
                dd =Integer.parseInt(stringDate.substring(0, 2));
                mm =Integer.parseInt(stringDate.substring(3, 5));
                yy =Integer.parseInt(stringDate.substring(6));                
            break;            
            default:
                dd=0;mm=0;yy=0;
                break;
        }
        if(yy%2==0){
            if(yy%4==0){leapyear=true;}
        }
        if(mm<1 || mm>12){
            return false;
        }
        else{
            switch(mm){
                case 1: case 3: case 5: case 7: case 8: case 12:
                    if (dd<1||dd>31){return false;}
                    break;
                case 2:
                    if (leapyear){
                        if (dd<1||dd>29){return false;}
                    } 
                    else
                    {
                         if (dd<1||dd>28){return false;}
                    }
                    break;
                default:
                    if(dd<1||dd>30){return false;}
                    break;
            }
        }
        return true;
    }
    public static Date stringToDate(String date, String format){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        }catch(ParseException ex){
            return null;
        }
    }
    public static String dateToString(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    public static int getAge(Date birthDate){
        int age=0;
        if (birthDate==null){
            return age;
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        
        String stringBirthDate = dateToString(birthDate, "yyyy/MM/dd");
        StringTokenizer tokens = new StringTokenizer(stringBirthDate,"/");
        int byear = Integer.parseInt(tokens.nextToken());
        int bmonth = Integer.parseInt(tokens.nextToken());
        int bday = Integer.parseInt(tokens.nextToken());
        
        //Calcular la edad
        age = year - byear;
        if(month<bmonth){
            age = age - 1;
        }
        else{
            if(month==bmonth){
                if(day<bday){
                    age = age -1;
                }
            }
        }        
        return age;
    }
}
