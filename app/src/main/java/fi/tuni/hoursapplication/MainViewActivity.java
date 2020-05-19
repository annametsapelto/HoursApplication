package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainViewActivity extends AppCompatActivity {
    private int hours;
    private int minutes;
    private String totalTime;
    TextView totalTimeText;
    private static final int REQUEST_CODE = 1;

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
        setTotalTime(getHours(), getMinutes() );
        setContentView(R.layout.activity_main_view);
        totalTimeText = findViewById(R.id.totalWorkTime);
        totalTimeText.setText("Total working hours: " + getTotalTime());
    }

    public void enteringView(View v) {
        Intent intent = new Intent(this, EnterInformationActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode, data);
        if(requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle bundle = data.getExtras();
                setHours(bundle.getInt("hours"));
                setMinutes(bundle.getInt("minutes"));
                setTotalTime(getHours(), getMinutes());
                totalTimeText.setText("Total work time: " + getTotalTime());
            }
            if (resultCode == RESULT_CANCELED) {

            }
        }
    }

}
