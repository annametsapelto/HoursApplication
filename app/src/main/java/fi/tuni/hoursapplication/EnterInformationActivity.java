package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterInformationActivity extends AppCompatActivity {
    EditText hText;
    EditText mText;
    Button cancelButton;
    Button resultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_information);

    }
    public void submit(View v) {
        mText = findViewById(R.id.minutesInput);
        hText = findViewById(R.id.hoursInput);
        if (hText.getText().toString().equals("") || mText.getText().toString().equals("")) {
            Toast.makeText(EnterInformationActivity.this, "Enter hours and minutes.", Toast.LENGTH_SHORT).show();
        } else {
            int h = Integer.parseInt(hText.getText().toString());
            int m = Integer.parseInt(mText.getText().toString());
            Log.d("Enter", "hours "+h);
            Intent intent = new Intent();
            intent.putExtra("hours", h);
            intent.putExtra("minutes", m);

            setResult(RESULT_OK, intent);
            finish();
        }
    }
    public void cancel(View v) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
