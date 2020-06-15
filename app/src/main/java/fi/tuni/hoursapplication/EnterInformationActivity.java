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
//In this activity the user chooses the date and work time for that day and passes the information back to the previous activity.
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
    //This method is called when the user presses a button.
    public void submit(View v) {
        mText = findViewById(R.id.minutesInput);
        hText = findViewById(R.id.hoursInput);

        //If the user hasn't entered information, a toast is shown
        if (date==null || hText.getText().toString().equals("") || mText.getText().toString().equals("")) {
            Toast.makeText(EnterInformationActivity.this, "Enter hours and minutes.", Toast.LENGTH_SHORT).show();

        //If the user has entered information, it will be converted to integers and validated
        } else {
            int h = Integer.parseInt(hText.getText().toString());
            int m = Integer.parseInt(mText.getText().toString());
            if (h<0 || h>20 || m<0 || m>59) {
                Toast.makeText(EnterInformationActivity.this, "Enter valid hours and minutes.", Toast.LENGTH_SHORT).show();

        //If the numbers are possible, they are passed to the previous activity and this one closed.
            } else {
                Intent intent = new Intent();
                intent.putExtra("hours", h);
                intent.putExtra("minutes", m);
                intent.putExtra("date", date);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
    //This method returns to the previous activity when a button is pressed and nothing has been chosen.
    public void cancel(View v) {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    //This method opens the DatePickerDialog when a button has been pressed.
    public void pickADate(View v) {
        DatePickerDialog dialog = new DatePickerDialog(this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
//This method formats the chosen date and sets it to the screen
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = month+1 +"/"+ dayOfMonth + "/" + year;
        pickedDate = findViewById(R.id.picked_date);
        pickedDate.setText("Chosen date: " + date);
    }
}
