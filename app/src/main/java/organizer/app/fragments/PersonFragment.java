package organizer.app.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import organizer.app.R;
import organizer.app.adapters.UserAdapter;
import organizer.app.data.data.Person;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class PersonFragment extends Fragment implements UserAdapter.onClickListener {


    private organizer.app.fragments.NoteViewModel mViewModel;

    private FloatingActionButton mainFab;

    private RecyclerView recyclerView;

    private List<Person> personList = new ArrayList<>();

    private PersonViewModel pViewModel;



    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewUser);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pViewModel = new PersonViewModel();
        UserAdapter adapter = new UserAdapter(personList, this);
        pViewModel.getAllPeople().observe(getViewLifecycleOwner(), new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable final List<Person> people) {
                adapter.setNotes(people);
            }
        });
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
        Log.d(TAG, "onClick: clicked with id: " + position);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.edit_person, null));
        Log.d(TAG, "Trying to inflate dialog");
        builder.create();
        builder.show();
    }
}
