package organizer.app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.navigation.ui.AppBarConfiguration;

import localdatabase.DatabaseHandler;
import localdatabase.MyDatabase;
import organizer.app.data.data.Note;
import organizer.app.fragments.NoteViewModel;

import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

import java.util.List;

public class NoteActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    NoteViewModel nViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_main);
        Bundle b = getIntent().getExtras();
        nViewModel = new NoteViewModel(this);
        int value = 0; // or other values
        if(b != null)
        {
            value = b.getInt("noteId");
        }

        button = (Button) findViewById(R.id.saveButton);
        editText = (EditText) findViewById(R.id.text_area);
        nViewModel = new NoteViewModel(this);
        int finalValue = value;
        nViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable final List<Note> words) {
                // Update the cached copy of the words in the adapter.
                editText.setText(nViewModel.getAllNotes().getValue().get(finalValue).getNoteContent());
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    //public void openMainScreenActivity(){
        //Intent intent = new Intent(this, activityMain.class);
        //startActivity(intent);
    //}
}
