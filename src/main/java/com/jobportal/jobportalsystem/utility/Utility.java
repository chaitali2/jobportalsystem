package com.jobportal.jobportalsystem.utility;

import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Utility {

    public String changeDateFormatter(String inputdate, String dateFormate) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(inputdate);
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormate);
        String strDate = formatter.format(date1);
        return strDate;
    }
}
