package common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utils{

    public static String getCurrentDateTime(){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return sdf1.format(timestamp);
    }

    public static String getTimeStamp(){
        SimpleDateFormat sdf1 = new SimpleDateFormat("ddMMMyyyy_HHmmss");
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return sdf1.format(timestamp);
    }

    public static int getRandomInteger(int upperBound){
        Random rand = new Random();
        return rand.nextInt(upperBound);
    }
}
