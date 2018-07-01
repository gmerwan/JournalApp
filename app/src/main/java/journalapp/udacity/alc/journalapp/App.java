package journalapp.udacity.alc.journalapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import journalapp.udacity.alc.journalapp.room.database.AppDatabase;
import journalapp.udacity.alc.journalapp.utilities.Preferences;

public class App extends Application {

    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        Preferences.initSharedPreferences(this);
        String DATABASE_NAME = "diary-database";
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    public static AppDatabase getDB() {
        return database;
    }
}
