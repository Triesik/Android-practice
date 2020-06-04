package organizer.app.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.Dialog;
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

import localdatabase.DatabaseHandler;
import organizer.app.R;
import organizer.app.adapters.UserAdapter;
import organizer.app.data.data.Note;
import organizer.app.data.data.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class fragment_users extends Fragment implements UserAdapter.onClickListener {


    private organizer.app.fragments.NoteViewModel mViewModel;

    private FloatingActionButton mainFab;

    private RecyclerView recyclerView;

    private List<Person> users = new ArrayList<>();

    DatabaseHandler db;


    public static fragment_users newInstance() {
        return new fragment_users();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewUser);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        db = new DatabaseHandler(getActivity());

        UserAdapter adapter = new UserAdapter(users, this);

        recyclerView.setAdapter(adapter);
        mainFab = requireActivity().findViewById(R.id.main_fab);
        mainFab.setImageResource(R.drawable.ic_add);

        mainFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.create_user_window);
                Button save = (Button) dialog.findViewById(R.id.save_button_create);
                final EditText editTextName = (EditText) dialog.findViewById(R.id.name_input_create);
                final EditText editTextPhone = (EditText) dialog.findViewById(R.id.name_input_create);
                dialog.show();
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String textName = editTextName.getText().toString();
                        String textPhone = editTextPhone.getText().toString();
                        Log.d(TAG, "onClick: clicked with id: " + textPhone);
                        int count = db.getPersonCount();
                        Log.d(TAG, String.valueOf(count));
                        Person person = new Person(count, textName, textPhone);
                        db.addPerson(person);
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
        //mViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
    }

    @Override
    public void onClick(int position, View view) {
        Log.d(TAG, "onClick: clicked with id: " + position);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.edit_person, null));
        Log.d(TAG, "Trying to inflate dialog");
        builder.create();
        builder.show();
    }
}
