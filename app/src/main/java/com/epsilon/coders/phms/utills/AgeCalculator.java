package com.epsilon.coders.phms.utills;

/**
 * Created by nilima on 10/6/2015.
 */
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AgeCalculator {

    public String age;


    int ageYear, ageMonth, ageDay;
    int compareDayValue;
    int compareMonthValue;
    int compareYearValue;

    int birthDayValue = 0, birthDayValue2 = 0;
    int birthMonthValue = 0, birthMonthValue2 = 0;
    int birthYearValue = 0;

    final Calendar c = Calendar.getInstance();


    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    final Date date = new Date();
    final String s = df.format(date);


// Get current date by calender


//   int yyyy=    year = c.get(Calendar.YEAR);
//        month = c.get(Calendar.MONTH);
//        day = c.get(Calendar.DAY_OF_MONTH);


    public String calAge(String ss) {
        //*******************************************************************

        try {

            //  String s1 = birthDate.getText().toString();
            String birthDay = ss.replaceAll("[.,,,*,+,_,:,;,-]", "/");
            String[] birthDayParts = birthDay.split("/");
            birthMonthValue = Integer.valueOf(birthDayParts[0]);

            birthDayValue  = Integer.valueOf(birthDayParts[1]);

            birthYearValue = Integer.valueOf(birthDayParts[2]);


            // String s2 = currentDate.getText().toString();

            String compareDate = s.replaceAll("[.,,,*,+,_,:,;,-]", "/");
            String[] compareDateParts = compareDate.split("/");
            compareDayValue = Integer.valueOf(compareDateParts[0]);
            compareMonthValue = Integer.valueOf(compareDateParts[1]);
            compareYearValue = Integer.valueOf(compareDateParts[2]);


//*******************************************************************


            if (compareDayValue >= birthDayValue) {

                ageDay = compareDayValue - birthDayValue;
            } else {
                compareDayValue = compareDayValue + 30;
                ageDay = compareDayValue - birthDayValue;
                birthMonthValue = birthMonthValue + 1;

            }
            if (compareMonthValue >= birthMonthValue) {

                ageMonth = compareMonthValue - birthMonthValue;
            } else {
                compareMonthValue = compareMonthValue + 12;
                ageMonth = compareMonthValue - birthMonthValue;
                birthYearValue = birthYearValue + 1;

            }
            ageYear = compareYearValue - birthYearValue;
            age = (String.valueOf(ageYear) + " Years "+ (String.valueOf(ageMonth)) +"  Months and " + (String.valueOf(ageDay)+ " Days old."));
            // String ageMessage = ("You are " + (String.valueOf(ageYear) + " Years " + (String.valueOf(ageMonth)) + "  Months and " + (String.valueOf(ageDay)) + " Days old."));
            // return age;


        } catch (Exception e) {
            String selectDateMessage = "Please select your birthday.";
            // showAge.setText(selectDateMessage);
            return "no age";

        }

        return age;
    }
}




