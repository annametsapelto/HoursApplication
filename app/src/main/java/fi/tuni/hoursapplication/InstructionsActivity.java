package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * This Activity shows the user some instructions about using this application.
 */
public class InstructionsActivity extends AppCompatActivity {
    /**
     * This method is called when the Activity is opened.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    /**
     * This method is called when the user presses the button to exit. It will close this Activity and return to MainActivity.
     * @param v
     */
    public void back(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}