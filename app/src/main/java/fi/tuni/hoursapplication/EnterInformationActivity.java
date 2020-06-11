package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class EnterInformationActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText hText;
    EditText mText;
    String date;
    TextView pickedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_information);

    }
    public void submit(View v) {
        mText = findViewById(R.id.minutesInput);
        hText = findViewById(R.id.hoursInput);
        if (date=="" || hText.getText().toString().equals("") || mText.getText().toString().equals("")) {
            Toast.makeText(EnterInformationActivity.this, "Enter hours and minutes.", Toast.LENGTH_SHORT).show();
        } else {
            int h = Integer.parseInt(hText.getText().toString());
            int m = Integer.parseInt(mText.getText().toString());
            Intent intent = new Intent();
            intent.putExtra("hours", h);
            intent.putExtra("minutes", m);
            intent.putExtra("date", date);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
    public void cancel(View v) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
    public void pickADate(View v) {
        DatePickerDialog dialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = month+1 +"/"+ dayOfMonth + "/" + year;
        pickedDate = findViewById(R.id.picked_date);
        pickedDate.setText(date);
    }
}
