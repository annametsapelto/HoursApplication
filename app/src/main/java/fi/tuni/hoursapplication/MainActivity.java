package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toMainView(View v) {
        Intent intent = new Intent(this, MainViewActivity.class);
        startActivity(intent);
    }
    public void toInstructions(View v) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }
    public void toInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
    public void toProjectsOverview(View v) {
        Intent intent = new Intent(this, Overview.class);
        startActivity(intent);
    }
}
