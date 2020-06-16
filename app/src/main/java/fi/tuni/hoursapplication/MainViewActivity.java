package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static fi.tuni.hoursapplication.R.drawable.button_border;

/*
This activity shows the information on one project and its work time
 */
public class MainViewActivity extends AppCompatActivity implements MyAdapter.OnNoteListener, ModifyDialog.MyOnInputListener, EntryDialog.PositionListener {

    private int hours;
    private int minutes;
    private String totalTime;
    TextView totalTimeText, projectName;
    private static final int REQUEST_CODE = 1;
    ArrayList<String> entries;
    RecyclerView recyclerView;
    int position;
    MyAdapter adapter;

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
        load();
        setTotalTime();
        setContentView(R.layout.activity_main_view);
        String name = getIntent().getStringExtra("projectName");
        projectName = findViewById(R.id.project_name);
        projectName.setText(name);
        totalTimeText = findViewById(R.id.totalWorkTime);
        totalTimeText.setText("Total working hours: " + getTotalTime());
        adapter = new MyAdapter(this, entries, this);
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

    //This method shows an EntryDialog when an item on the RecyclerView is clicked.
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
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String entryString = gson.toJson(entries);
        editor.putString("entries", entryString);
        editor.putInt("hours", getHours());
        editor.putInt("minutes", getMinutes());
        editor.commit();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }
    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        Gson gson = new Gson();
        int savedHours = sharedPreferences.getInt("hours", 0);
        int savedMinutes = sharedPreferences.getInt("minutes", 0);
        setHours(savedHours);
        setMinutes(savedMinutes);
        String entryString = sharedPreferences.getString("entries", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        entries = gson.fromJson(entryString, type);
        if (entries == null) {
            entries = new ArrayList<String>();
        }
    }

    @Override
    public void getPosition(int position) {
        this.position = position;
        deletePosition(position);
    }

    private void deletePosition(int position) {
        Log.d("debug", "removing"+position);
        entries.remove(position);
        adapter.notifyItemRemoved(position);
    }
}
