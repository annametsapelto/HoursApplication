package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
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

/**
This Activity holds several buttons for different functionalities in other activities
 */
public class MainActivity extends AppCompatActivity implements CreateProjectDialog.ProjectNameListener {
    //Variables
    String projectName;
    private final static String PREFS = "PREFS";
    SharedPreferences sharedPreferences;
    //Widgets
    Button currentProjectButton;

    /**
     * This method is called when the Activity is opened.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Find the Button
        currentProjectButton = findViewById(R.id.currentProjectButton);
        //Load the project's name
        load();
    }

    /**This method moves the user to MainViewActivity
     * @param v
     */
    public void toMainView(View v) {
        if (projectName.equals("")) {
            //If the project name has not been set, the Toast is shown.
            Toast.makeText(this, "You have not chosen a project.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MainViewActivity.class);
            intent.putExtra("projectName", projectName);
            startActivity(intent);
        }
    }

    /**This method creates a dialog for entering the project name
     * @param v
     */
    public void openCreateDialog(View v) {
        CreateProjectDialog projectNameDialog = new CreateProjectDialog();
        projectNameDialog.show(getSupportFragmentManager(), "Create a Project");
    }

    /**This method moves the user to InstructionsActivity
     * @param v
     */
    public void toInstructions(View v) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
    }

    /**This method moves the user to InfoActivity
     * @param v
     */
    public void toInfo(View v) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    /**
     * This method takes the project name from CreateProjectDialog, saves it and sets it to a Button.
     * @param projectName
     */
    @Override
    public void getProjectName(String projectName) {
        this.projectName = projectName;
        save();
        currentProjectButton.setText("Continue " + projectName);

    }

    /**
     * This method saves the project name to shared preferences using Gson.
     */
    public void save() {
        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String savedProjectName = gson.toJson(projectName);
        editor.putString("project", savedProjectName);

        editor.commit();
        Toast.makeText(this, "Created a new project "+projectName, Toast.LENGTH_SHORT).show();
    }

    /**
     * This method loads the project name from shared preferences using Gson.
     */
    public void load() {
        sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
        Gson gson = new Gson();
        String savedProjectName = sharedPreferences.getString("project", null);
        Type type = new TypeToken<String>() {}.getType();
        projectName = gson.fromJson(savedProjectName, type);
        currentProjectButton.setText("Continue " + projectName);
        //If there is no project name, set it empty and set text in Button.
           if(savedProjectName==null) {
           projectName = "";
           currentProjectButton.setText("No chosen project");
        }
    }
}
