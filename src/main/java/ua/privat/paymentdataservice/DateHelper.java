package ua.privat.paymentdataservice;

import org.springframework.stereotype.Component;
import ua.privat.paymentdataservice.models.RegularPayment;

import java.sql.Timestamp;
import java.util.Calendar;

@Component
public class DateHelper {

    private Timestamp currentDate;

    public RegularPayment prepareDate(RegularPayment regularPayment) {

        boolean isWriteOffDateNull = true;
        if (regularPayment.getWriteOffDate() != null) {
            currentDate = Timestamp.valueOf(regularPayment.getWriteOffDate());
            isWriteOffDateNull = false;
        }

        String[] periodFragments = regularPayment.getWriteOffPeriod()
                .split("/");

        int position = 0;
        for (int i = 0; i < periodFragments.length; i++) {
            if (!periodFragments[i].equals("0")) {
                position = i;
                break;
            }
        }

        Timestamp date = generationDate(position, periodFragments, isWriteOffDateNull);
        regularPayment.setWriteOffDate(String.valueOf(date));
        return regularPayment;
    }

    private Timestamp generationDate(int position, String[] periodFragments, boolean isFirstGenerate) {
        Calendar calendar = Calendar.getInstance();
        if (isFirstGenerate) {
            calendar.setTime(new Timestamp(System.currentTimeMillis()));
        } else {
            calendar.setTime(currentDate);
        }

        switch (position) {
            // Minutes
            case 0 -> calendar.add(Calendar.MINUTE, Integer.parseInt(periodFragments[position]));
            // Times
            case 1 -> calendar.add(Calendar.HOUR, Integer.parseInt(periodFragments[position]));
            // Days
            case 2 -> calendar.add(Calendar.DATE, Integer.parseInt(periodFragments[position]));
        }

        return new Timestamp(calendar.getTimeInMillis());
    }
}
