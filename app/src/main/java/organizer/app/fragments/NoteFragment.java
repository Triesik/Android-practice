package organizer.app.fragments;

import androidx.lifecycle.Observer;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import localdatabase.MyDatabase;
import organizer.app.NoteActivity;
import organizer.app.R;
import organizer.app.Repositories.NoteRepository;
import organizer.app.adapters.NoteAdapter;
import organizer.app.data.data.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class NoteFragment extends Fragment implements NoteAdapter.onClickListener {

    private NoteViewModel nViewModel;

    private FloatingActionButton mainFab;

    private RecyclerView recyclerView;

    MyDatabase db;

    NoteRepository noteRepository;

    Button button;

    private List<Note> notes = new ArrayList<>();

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_note, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNote);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        db = MyDatabase.getInstance(getContext());
        NoteAdapter adapter = new NoteAdapter(notes, this);
        nViewModel = new NoteViewModel(getContext());
        nViewModel.getAllNotes().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable final List<Note> words) {
                adapter.setNotes(words);
            }
        });
        recyclerView.setAdapter(adapter);
        mainFab = requireActivity().findViewById(R.id.main_fab);
        mainFab.setImageResource(R.drawable.ic_add);

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.create_note_window);
                Button save = (Button) dialog.findViewById(R.id.saveButton);
                final EditText editText = (EditText) dialog.findViewById(R.id.note_input);
                dialog.show();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = editText.getText().toString();
                        Note note = new Note(1, "start your note", text);
                        nViewModel.insert(note);
                        Log.d(TAG, "onClick: clicked with id: " + text);
                    }
                });
            }

        });
        mainFab.show();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onClick(int position, View view) {
        Context context = view.getContext();
        Log.d(TAG, "onClick: clicked with id: " + position);
        Intent noteIntent = new Intent(context,NoteActivity.class);
        Bundle id = new Bundle();
        id.putInt("noteId", position);
        noteIntent.putExtras(id);
        startActivity(noteIntent);
    }

}


