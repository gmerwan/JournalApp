package journalapp.udacity.alc.journalapp.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import journalapp.udacity.alc.journalapp.App;
import journalapp.udacity.alc.journalapp.activities.MainActivity;
import journalapp.udacity.alc.journalapp.adapters.DiaryAdapter;
import journalapp.udacity.alc.journalapp.room.entity.Diary;
import journalapp.udacity.alc.journalapp.utilities.Preferences;

public class DiaryModel extends ViewModel {

    public Diary diary = null;
    public List<Diary> diaries = null;

    public void loadData(Activity activity) {
        diaries = App.getDB().diaryDao().getAll(Preferences.getToken());

    }
}
