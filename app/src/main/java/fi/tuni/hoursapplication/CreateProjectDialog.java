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

public class CreateProjectDialog extends DialogFragment {
    public ProjectNameListener projectNameListener;
    EditText name;
    Button cancel, create;

    public interface ProjectNameListener {
        void getProjectName(String projectName);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_new_project, container, false);
        name = view.findViewById(R.id.nameEdit);
        create = view.findViewById(R.id.create_button);
        cancel = view.findViewById(R.id.cancelButton3);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            projectNameListener = (ProjectNameListener) getActivity();
    } catch(ClassCastException e) {
        }
    }
}
