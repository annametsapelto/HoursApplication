package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/*
This Activity holds several buttons for different functionalities in other activities
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
//This method moves the user to MainViewActivity
    public void toMainView(View v) {
        Intent intent = new Intent(this, MainViewActivity.class);
        startActivity(intent);
    }
    //This method moves the user to InstructionsActivity
    public void toInstructions(View v) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }
    //This method moves the user to InfoActivity
    public void toInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
    //This method moves the user to Overview
    public void toProjectsOverview(View v) {
        Intent intent = new Intent(this, Overview.class);
        startActivity(intent);
    }
}
