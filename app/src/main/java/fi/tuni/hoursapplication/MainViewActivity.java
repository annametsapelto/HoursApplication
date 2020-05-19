package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainViewActivity extends AppCompatActivity {
    private int hours;
    private int minutes;
    private String totalTime;

    public String getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(int h, int m) {
        int newH = getHours() + h;
        setHours(newH);
        int newM = getMinutes()+m;
        if(newM>60) {
            setHours(getHours()+1);
            newM = newM-60;
        }
        setMinutes(newM);
        String time = getHours()+ " h "+ getMinutes()+" min";
        totalTime = time;
    }

    public int getHours() {
        return hours;
    }
    public void setHours(int h) {
        if(h<20 && h>=0) {
            hours = h;
        }
    }
    public int getMinutes() {
        return minutes;
    }
    public void setMinutes(int m) {
        if(m<60 && m>=0) {
            minutes = m;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
    }

    public void enteringView(View v) {
        Intent intent = new Intent(this, EnterInformationActivity.class);
        startActivity(intent);
    }
}
