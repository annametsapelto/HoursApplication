package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterInformationActivity extends AppCompatActivity {
    EditText hText;
    EditText mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_information);
        mText = findViewById(R.id.minutesInput);
        hText = findViewById(R.id.hoursInput);
        Button button = findViewById(R.id.returnButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hText.getText().toString().equals("") || mText.getText().toString().equals("")) {
                    Toast.makeText(EnterInformationActivity.this, "Enter hours and minutes.", Toast.LENGTH_SHORT).show();
                } else {
                    int h = Integer.parseInt(hText.toString());
                    int m = Integer.parseInt(mText.toString());
                    Intent intent = new Intent()
                            .putExtra("hours", h)
                            .putExtra("minutes", m);

                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

    }
}
