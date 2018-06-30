package journalapp.udacity.alc.journalapp.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import journalapp.udacity.alc.journalapp.R;
import journalapp.udacity.alc.journalapp.adapters.DiaryAdapter;
import journalapp.udacity.alc.journalapp.utils.Preferences;
import journalapp.udacity.alc.journalapp.viewmodel.DiaryModel;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddDiaryActivity.class));
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        DiaryModel diaryModel = ViewModelProviders.of(this).get(DiaryModel.class);
        // If the list of cities is null, load the list from DB
        if (diaryModel.diaries == null) {
            diaryModel.loadData(this);
        } else {
            // After the rotation of the screen, use cities of the ViewModel instance
            recyclerView.setAdapter(new DiaryAdapter(this, diaryModel.diaries));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
            case R.id.action_sign_out:
                Preferences.clearData();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
