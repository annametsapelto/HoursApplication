package fi.tuni.hoursapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class ModifyDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    Button pickerButton, submitButton, cancelButton;
    EditText hoursInput, minutesInput;
    String date;
    TextView pickedDate;
    MyOnInputListener listener;
    int position;

    public ModifyDialog(int position) {
        this.position = position;
        Log.d("debug", "modify "+position);
    }

    public interface MyOnInputListener {
        void sendInput(int position, String date, int hours, int minutes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_modify, container, false);
        pickerButton = view.findViewById(R.id.date_picker2);
        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickADate();
            }
        });

        submitButton = view.findViewById(R.id.submitButton2);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        cancelButton = view.findViewById(R.id.cancelButton2);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
        return view;
    }
    public void submit() {
        hoursInput = getView().findViewById(R.id.hoursInput2);
        minutesInput = getView().findViewById(R.id.minutesInput2);
        if (date == null || hoursInput.getText().toString().equals("") || minutesInput.getText().toString().equals("")) {
            Toast.makeText(getActivity(), "Enter hours and minutes.", Toast.LENGTH_SHORT).show();

            //If the user has entered information, it will be converted to integers and validated
        } else {
            int h = Integer.parseInt(hoursInput.getText().toString());
            int m = Integer.parseInt(minutesInput.getText().toString());
            if (h < 0 || h > 20 || m < 0 || m > 59) {
                Toast.makeText(getActivity(), "Enter valid hours and minutes.", Toast.LENGTH_SHORT).show();
            } else {
                listener.sendInput(position, date, h, m);
            }

        }
    }
    //This method closes the dialog if the user doesn't want to make changes.
    public void cancel() {
        getDialog().dismiss();
    }

    //This method opens the DatePickerDialog when a button has been pressed.
    public void pickADate() {
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }
    //This method formats the chosen date and sets it to the screen
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = month+1 +"/"+ dayOfMonth + "/" + year;
        pickedDate = view.findViewById(R.id.picked_date);
        pickedDate.setText("Chosen date: " + date);
    }
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            listener = (MyOnInputListener) getActivity();
        } catch(ClassCastException c){

        }
    }
}
