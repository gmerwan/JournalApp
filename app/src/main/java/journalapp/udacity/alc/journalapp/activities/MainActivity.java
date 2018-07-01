package journalapp.udacity.alc.journalapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.avast.android.dialogs.fragment.SimpleDialogFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import journalapp.udacity.alc.journalapp.App;
import journalapp.udacity.alc.journalapp.R;
import journalapp.udacity.alc.journalapp.adapters.DiaryAdapter;
import journalapp.udacity.alc.journalapp.room.entity.Diary;
import journalapp.udacity.alc.journalapp.utilities.Preferences;

public class MainActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;
    public RecyclerView recyclerView;
    public DiaryAdapter diaryAdapter;

    public List<Diary> diaries = new ArrayList<>();

    private int recyclerViewItemPosition;
    private View childView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate", "onCreate");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddDiaryActivity.class));
                finish();
            }
        });

        loadData();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        diaryAdapter = new DiaryAdapter(this, diaries);
        recyclerView.setAdapter(diaryAdapter);
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            GestureDetector gestureDetector = new GestureDetector(MainActivity.this,
//                    new GestureDetector.SimpleOnGestureListener() {
//
//                @Override
//                public boolean onSingleTapUp(MotionEvent motionEvent) {
//                    return true;
//                }
//            });
//            @Override
//            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
//
//                childView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
//                if (childView != null && !(childView instanceof ImageView) && gestureDetector.onTouchEvent(motionEvent)) {
//                    recyclerViewItemPosition = recyclerView.getChildAdapterPosition(childView);
//                    Diary diary = diaryAdapter.getItem(recyclerViewItemPosition);
//                    showDiaryDialog(diary);
//                }
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "onResume");
        loadData();
        diaryAdapter.notifyDataSetChanged();
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
                signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(MainActivity.this, gso);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Preferences.clearData();
                        Preferences.setLogging(false);
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                });
    }

    public void loadData() {
        diaries = App.getDB().diaryDao().getAll(Preferences.getToken());
    }

}
