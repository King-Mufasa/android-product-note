package com.dk.hen.product_block;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Global {

    public static String getToday(){
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String str = sdf.format(currentTime);
        String sub_id = String.valueOf(currentTime.getHours())+String.valueOf(currentTime.getMinutes());
        return currentTime.toString();
    }
}
