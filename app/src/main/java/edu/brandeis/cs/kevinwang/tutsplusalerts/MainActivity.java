package edu.brandeis.cs.kevinwang.tutsplusalerts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAnalytics fa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fa = FirebaseAnalytics.getInstance(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final EditText titleEditText = (EditText)findViewById(R.id.et_title);
        final EditText authorEditText = (EditText)findViewById(R.id.et_author);
        Button submitButton = (Button)findViewById(R.id.btn_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference myRef = database.getReference("articles").push();
                Article article = new Article(titleEditText.getText().toString(),
                        authorEditText.getText().toString());
                myRef.setValue(article);
                // Create a Bundle containing information about
// the analytics event
                Bundle eventDetails = new Bundle();
                eventDetails.putString("my_message", "Clicked that special button");

// Log the event
                fa.logEvent("my_custom_event", eventDetails);
            }
        });
    }

    public void MyProfile(View view) {
        Intent intent = new Intent(this, MyProfileActivity.class);
        startActivity(intent);
    }
}
