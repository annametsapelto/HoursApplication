package fi.tuni.hoursapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/*
This Activity holds several buttons for different functionalities in other activities
 */
public class MainActivity extends AppCompatActivity implements CreateProjectDialog.ProjectNameListener {
    String projectName;
    Button currentProjectButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentProjectButton = findViewById(R.id.currentProjectButton);
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
        currentProjectButton.setText("Continue " + projectName);
    }
}
