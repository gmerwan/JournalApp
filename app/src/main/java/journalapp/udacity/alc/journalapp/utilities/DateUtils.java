package journalapp.udacity.alc.journalapp.utilities;

import android.content.Context;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import journalapp.udacity.alc.journalapp.R;

public class DateUtils {

    public static String formatDate(Context mContext, long time) {

        try {

            Calendar calendar1 = Calendar.getInstance();
            Calendar calendar2 = Calendar.getInstance();
            calendar1.setTimeInMillis(time);
            calendar2.setTimeInMillis(System.currentTimeMillis());
            boolean sameDay = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                    calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
            if (sameDay) {
                Timestamp stamp = new Timestamp(time);
                Date date = new Date(stamp.getTime());
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                return mContext.getString(R.string.today)+", "+sdf.format(date);

            }
            boolean yesterday = calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                    (calendar1.get(Calendar.DAY_OF_YEAR) + 1) == calendar2.get(Calendar.DAY_OF_YEAR);
            if (yesterday) {
                return mContext.getString(R.string.yesterday);
            }

            Timestamp stamp = new Timestamp(time);
            Date date = new Date(stamp.getTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.getDefault());

            return simpleDateFormat.format(date);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
