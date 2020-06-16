package fi.tuni.hoursapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
/**In this dialog the user sets a name for their project.
 * The name is given back to MainActivity.
 * Extends the DialogFragment class.
 */
public class CreateProjectDialog extends DialogFragment {
    public ProjectNameListener projectNameListener;
    //Widgets
    EditText name;
    Button cancel, create;
//An interface to help return the project name to MainActivity
    public interface ProjectNameListener {
        void getProjectName(String projectName);
    }

    /**This method is called when the dialog is created.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_new_project, container, false);
        //Find EditText and Buttons by id.
        name = view.findViewById(R.id.nameEdit);
        create = view.findViewById(R.id.create_button);
        cancel = view.findViewById(R.id.cancelButton3);
        //Create a listener for cancel for closing the dialog.
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        //Create a listener for setting the project name and then closing the dialog.
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = name.getText().toString();
                if(!input.equals("")) {
                    projectNameListener.getProjectName(input);
                    getDialog().dismiss();
                }
            }
        });

        return view;
    }

    /**
     * A method for using the ProjectListener.
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            projectNameListener = (ProjectNameListener) getActivity();
    } catch(ClassCastException e) {
        }
    }
}
