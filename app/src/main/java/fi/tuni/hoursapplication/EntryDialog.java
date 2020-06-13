package fi.tuni.hoursapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;

public class EntryDialog extends DialogFragment {
    private Button deleteButton, modifyButton, cancelButton;
    private int position;
    private ArrayList<String> entries;

    public EntryDialog(Bundle bundle) {
        position = bundle.getInt("position");
        entries = bundle.getStringArrayList("entries");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_custom_dialog, container, false);
        deleteButton = view.findViewById(R.id.dialog_delete);
        modifyButton = view.findViewById(R.id.dialog_modify);
        cancelButton = view.findViewById(R.id.dialog_cancel);

        //Delete the clicked entry
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entries.remove(position);
            }
        });
        
        //Opens a new activity for modifying the entries list.
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //On click the dialog closes
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }
}
