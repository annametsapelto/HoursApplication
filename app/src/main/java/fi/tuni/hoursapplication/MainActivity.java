package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/*
This Activity holds several buttons for different functionalities in other activities
 */
public class MainActivity extends AppCompatActivity implements CreateProjectDialog.ProjectNameListener {
    String projectName;
    Button currentProjectButton;
    private final static String PREFS = "PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentProjectButton = findViewById(R.id.currentProjectButton);
        load();
    }
//This method moves the user to MainViewActivity
    public void toMainView(View v) {
        if (!projectName.equals("")) {
            Intent intent = new Intent(this, MainViewActivity.class);
            intent.putExtra("projectName", projectName);
            startActivity(intent);
        } else {
            Toast.makeText(this, "You have not chosen a project.", Toast.LENGTH_SHORT).show();
        }
    }
    //This method creates a dialog for entering the project name
    public void openCreateDialog(View v) {
        CreateProjectDialog projectNameDialog = new CreateProjectDialog();
        projectNameDialog.show(getSupportFragmentManager(), "Create a Project");
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

    @Override
    public void getProjectName(String projectName) {
        this.projectName = projectName;
        save();
        currentProjectButton.setText("Continue " + projectName);
    }
    public void save() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String savedProjectName = gson.toJson(projectName);
        editor.putString("project", savedProjectName);

        editor.commit();
        Toast.makeText(this, "Created a new project "+projectName, Toast.LENGTH_SHORT).show();
    }
    public void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        Gson gson = new Gson();
        String savedProjectName = sharedPreferences.getString("project", null);
        Type type = new TypeToken<String>() {}.getType();
        projectName = gson.fromJson(savedProjectName, type);
           if(savedProjectName==null) {
           projectName = "";
        }
    }
}
