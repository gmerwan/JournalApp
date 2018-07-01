package journalapp.udacity.alc.journalapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import journalapp.udacity.alc.journalapp.App;
import journalapp.udacity.alc.journalapp.R;
import journalapp.udacity.alc.journalapp.room.entity.Diary;
import journalapp.udacity.alc.journalapp.utilities.DateUtils;
import journalapp.udacity.alc.journalapp.utilities.Preferences;

public class AddDiaryActivity extends AppCompatActivity {

    public EditText title, content;
    public TextView updatedOn;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "onCreate");
        setContentView(R.layout.activity_add_diary);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        updatedOn = findViewById(R.id.edited_on);

        if (getIntent().hasExtra("title")) {
            title.setText(getIntent().getStringExtra("title"));
            content.setText(getIntent().getStringExtra("content"));
            updatedOn.setText("Last edited: " + DateUtils.formatDate(AddDiaryActivity.this,
                    getIntent().getLongExtra("date", 0)));
            updatedOn.setVisibility(View.VISIBLE);
            id = getIntent().getIntExtra("id", 0);

        }
    }

    public static void  updateDiary(final Context context,final int id, final String title, final String content) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {

                App.getDB().diaryDao().update(new Diary(id, Preferences.getToken(), title, content,
                        System.currentTimeMillis()));
                Log.d("diary updated", "hello");
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, "Diary updated", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, MainActivity.class));
                ((Activity) context).finish();
            }
        }.execute();
    }

    public static void saveDiary(final Context context, final String title, final String content) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(final Void... params) {

                Diary diary = new Diary(Preferences.getToken(), title, content,
                        System.currentTimeMillis());
                App.getDB().diaryDao().insert(diary);
                Log.d("diary saved", "hello");
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(context, "Diary saved", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, MainActivity.class));
                ((Activity) context).finish();
            }
        }.execute();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(AddDiaryActivity.this, MainActivity.class));
        finish();
        Toast.makeText(getApplicationContext(), "Diary not saved", Toast.LENGTH_SHORT).show();
//
//        if (getIntent().hasExtra("id")) {
//            //update note
//            Log.d("in update section", "hello");
//            updateDiary(this, this.id, title.getText().toString(), content.getText().toString());
//        } else if (!TextUtils.isEmpty(content.getText().toString())
//                || !TextUtils.isEmpty(title.getText().toString())) {
//            Log.d("in save section", "hello");
//            saveDiary(this, title.getText().toString(), content.getText().toString());
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_diary_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();

        } else if (id == R.id.action_save) {
            if (TextUtils.isEmpty(content.getText().toString())
                    && TextUtils.isEmpty(title.getText().toString())) {
                Toast.makeText(getApplicationContext(), "The title and/or body is empty", Toast.LENGTH_SHORT).show();
            } else {
                if (getIntent().hasExtra("id")) {
                    //update note
                    Log.d("in update section", "hello");
                    updateDiary(this, this.id, title.getText().toString(), content.getText().toString());
                } else {
                    Log.d("in save section", "hello");
                    saveDiary(this, title.getText().toString(), content.getText().toString());
                }
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
