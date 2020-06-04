package organizer.app.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import organizer.app.NoteActivity;
import organizer.app.data.data.Note;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import organizer.app.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    List<Note> notes;
    private onClickListener onClickListener;

    public NoteAdapter(List<Note> notes, onClickListener onClickListener) {
        this.notes = notes;
        this.onClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        Note current = notes.get(position);
        holder.noteName.setText(current.getNoteName());

    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view, onClickListener);
    }


    public int getItemCount() {
        return notes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView noteName;
        ImageView noteIcon;

        onClickListener onClickListener;

        ViewHolder(View itemView, onClickListener onClickListener) {
            super(itemView);
            noteIcon = itemView.findViewById(R.id.note_image);
            noteName = itemView.findViewById(R.id.note_name);
            this.onClickListener = onClickListener;

            noteIcon.setOnClickListener(this);
        }

        @Override
        public void onClick(View itemView) {
            onClickListener.onClick(getAdapterPosition(), itemView);
        }
    }

    public interface onClickListener {
        void onClick(int position, View view);

    }

    public void setNotes(List<Note> notesList){
        notes = notesList;
        notifyDataSetChanged();
    }

}
