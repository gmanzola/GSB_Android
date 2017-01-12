package fr.yamishadow.gsbandroid.outils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dell on 30/11/2016.
 */

public abstract class MesOutils {

    public static Date convertStringToDate(String unedate){
        //String expectedPattern = "EEE MMM dd hh:mm:ss 'GMT' yyyy";
        //SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        try {
            Date date = formatter.parse(unedate);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateToString(Date uneDate){
        SimpleDateFormat date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        return date.format(uneDate);
    }


}
