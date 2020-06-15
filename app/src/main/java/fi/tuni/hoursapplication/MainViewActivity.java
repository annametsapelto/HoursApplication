package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static fi.tuni.hoursapplication.R.drawable.button_border;

/*
This activity shows the information on one project and its work time
 */
public class MainViewActivity extends AppCompatActivity implements MyAdapter.OnNoteListener, ModifyDialog.MyOnInputListener {

    private int hours;
    private int minutes;
    private String totalTime;
    TextView totalTimeText, projectName;
    private static final int REQUEST_CODE = 1;
    ArrayList<String> entries;
    RecyclerView recyclerView;

    public String getTotalTime() {
        return totalTime;
    }
    public void setTotalTime() {
        totalTime = getHours()+ " h "+ getMinutes()+" min";
    }

    public int getHours() {
        return hours;
    }
    public void setHours(int h) {
        if(h<20 && h>=0) {
            hours = getHours() + h;
        }
    }
    public int getMinutes() {
        return minutes;
    }
    public void setMinutes(int m) {
        minutes = getMinutes() + m;
        if(getMinutes()>= 60){
            setHours(1);
            setMinutes(-60);
        }
    }
//This method is called when the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTotalTime();
        setContentView(R.layout.activity_main_view);
        String name = getIntent().getStringExtra("projectName");
        projectName = findViewById(R.id.project_name);
        projectName.setText(name);
        totalTimeText = findViewById(R.id.totalWorkTime);
        totalTimeText.setText("Total working hours: " + getTotalTime());
        entries = new ArrayList<>();
        MyAdapter adapter = new MyAdapter(this, entries, this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

//This method moves the user to EnterInformationActivity
    public void enteringView(View v) {
        Intent intent = new Intent(this, EnterInformationActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
    //This method is called when returning from EnterInformationActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE) {
            Bundle bundle = data.getExtras();
            int h = bundle.getInt(("hours"));
            setHours(h);
            int m = bundle.getInt("minutes");
            String date = bundle.getString("date");
            setMinutes(m);
            setTotalTime();
            totalTimeText.setText("Total work time: " + getTotalTime());
            entries.add(new TimeEntry(date, h, m).toString());
        }
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            int m = savedInstanceState.getInt("minutes");
            setMinutes(m);
            int h = savedInstanceState.getInt("hours");
            setHours(h);
        }
        super.onRestoreInstanceState(savedInstanceState);
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        int minutes = getMinutes();
        outState.putInt("minutes", minutes);
        int hours = getHours();
        outState.putInt("hours", hours);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onNoteClick(final int position) {
        FragmentManager manager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putStringArrayList("EntryList", entries);
        EntryDialog entryDialog = new EntryDialog(bundle);
        entryDialog.show(manager, "Edit entry");

    }

    @Override
    public void sendInput(int position, String date, int hours, int minutes) {
        String modified = new TimeEntry(date, hours, minutes).toString();
        entries.set(position, modified);
    }
    public void save(View v) {

    }
}
