package journalapp.udacity.alc.journalapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avast.android.dialogs.fragment.SimpleDialogFragment;

import java.util.List;

import journalapp.udacity.alc.journalapp.R;
import journalapp.udacity.alc.journalapp.activities.AddDiaryActivity;
import journalapp.udacity.alc.journalapp.room.entity.Diary;
import journalapp.udacity.alc.journalapp.utilities.DateUtils;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {
    private Context context;
    private List<Diary> diaries;

    public DiaryAdapter(Context context, List<Diary> diaries) {
        this.context = context;
        this.diaries = diaries;

    }

    @NonNull
    @Override
    public DiaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_diaries, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryAdapter.ViewHolder holder, final int position) {
        Diary diary = diaries.get(position);
        holder.title.setText(diary.getTitle());
        holder.content.setText(diary.getContent());
        holder.editedOn.setText(DateUtils.formatDate(context,diary.getDate()));
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddDiaryActivity.class);
                intent.putExtra("title", diaries.get(position).getTitle());
                intent.putExtra("content", diaries.get(position).getContent());
                intent.putExtra("date", diaries.get(position).getDate());
                intent.putExtra("id", diaries.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDiaryDialog(diaries.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return diaries.size();
    }

    public Diary getItem(int position) {
        return diaries.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, content, editedOn;
        LinearLayout linearLayout;
        ImageView edit;

        ViewHolder(View view) {
            super(view);
            linearLayout = view.findViewById(R.id.linearLayout);

            title = view.findViewById(R.id.title);
            content = view.findViewById(R.id.content);
            editedOn = view.findViewById(R.id.edited_on);
            edit = view.findViewById(R.id.edit_button);
        }
    }

    private void showDiaryDialog(Diary diary) {
        SimpleDialogFragment.createBuilder(context, ((AppCompatActivity)context).getSupportFragmentManager())
                .setTitle(diary.getTitle())
                .setMessage(diary.getContent())
                .setNegativeButtonText("Close")
                .show();
    }
}
