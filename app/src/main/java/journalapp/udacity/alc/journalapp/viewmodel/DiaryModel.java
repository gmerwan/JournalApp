package journalapp.udacity.alc.journalapp.viewmodel;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import journalapp.udacity.alc.journalapp.activities.MainActivity;
import journalapp.udacity.alc.journalapp.adapters.DiaryAdapter;
import journalapp.udacity.alc.journalapp.room.database.Service;
import journalapp.udacity.alc.journalapp.room.entity.Diary;
import journalapp.udacity.alc.journalapp.utils.Preferences;

public class DiaryModel extends ViewModel {

    public Diary diary = null;
    public List<Diary> diaries = null;

    public void loadData(Activity activity) {
        diaries = Service.RoomService.getAppDatabase(activity).diaryDao().getAll(Preferences.getToken());

        if (diaries.size() != 0) {
            ((MainActivity) activity).recyclerView.setAdapter(new DiaryAdapter(activity, diaries));
        }
    }

//    public void loadDetail(Activity activity, Diary diary) {
//        this.diary = Service.RoomService.getAppDatabase(activity).diaryDao()
//                .findById(Preferences.getToken(), diary.getId());
//        if (this.diary.getDate() != 0) {
//            displayDetail(activity, this.diary);
//        }
//    }

    public void displayDetail(Activity activity, Diary diary) {

    }
}
