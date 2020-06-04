package organizer.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import localdatabase.DatabaseHandler;
import organizer.app.data.data.Note;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText passwordText;
    EditText userText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        Bundle b = getIntent().getExtras();

        button = (Button) findViewById(R.id.login_button);
        userText = (EditText) findViewById(R.id.login_user);
        passwordText = (EditText) findViewById(R.id.login_password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                LoginActivity.this.startActivity(myIntent);
            }
        });
    }

}
