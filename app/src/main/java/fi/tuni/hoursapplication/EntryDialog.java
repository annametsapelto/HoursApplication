package fi.tuni.hoursapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class EntryDialog extends DialogFragment {
    private Button deleteButton, modifyButton, cancelButton;
    private int position;
    public PositionListener positionListener;

    public interface PositionListener {
        void getPosition(int position);
    }

    public EntryDialog(Bundle bundle) {
        position = bundle.getInt("position");
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
                int toBeRemoved = position;
                positionListener.getPosition(toBeRemoved);
                getDialog().dismiss();
            }
        });

        //Opens a new activity for modifying the entries list.
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                ModifyDialog mDialog = new ModifyDialog(position);
                mDialog.show(manager, "Modify");
                getDialog().dismiss();

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
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            positionListener = (PositionListener) getActivity();
        } catch (ClassCastException e) {

        }
    }
}
