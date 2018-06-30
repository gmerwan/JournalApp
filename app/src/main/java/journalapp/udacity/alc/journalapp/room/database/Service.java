package journalapp.udacity.alc.journalapp.room.database;

import android.arch.persistence.room.Room;
import android.content.Context;

public class Service {

    public static class RoomService {

        private static AppDatabase appDatabase;

        public static synchronized AppDatabase getAppDatabase(Context context) {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(context, AppDatabase.class, "diary-database")
                        .allowMainThreadQueries()
                        .build();
            }
            return appDatabase;
        }

    }
}
