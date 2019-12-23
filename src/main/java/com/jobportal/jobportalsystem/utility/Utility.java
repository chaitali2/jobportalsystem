package com.jobportal.jobportalsystem.utility;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Utility {

    public Date changeDateFormatter(String inputdate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(inputdate);
        return date;
    }

    public String dateToString(Date inputDate){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(inputDate);
        return date;
    }
}
