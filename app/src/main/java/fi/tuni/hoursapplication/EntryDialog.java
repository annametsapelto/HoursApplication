package fi.tuni.hoursapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.DialogFragment;

/**
 * This dialog holds the deleting option for an item in RecyclerView.
 */
public class EntryDialog extends DialogFragment {
    //Variables
    private int position;
    public PositionListener positionListener;

    //Widgets
    private Button deleteButton, modifyButton, cancelButton;

    /**
     * This interface helps with returning the position of the deleted item.
     */
    public interface PositionListener {
        void getPosition(int position);
    }

    /**
     * Constructor for EntryDialog.
     * @param bundle
     */
    public EntryDialog(Bundle bundle) {
        position = bundle.getInt("position");
    }

    /**
     * This method is called when the dialog is created.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_custom_dialog, container, false);
        //Find the Buttons
        deleteButton = view.findViewById(R.id.dialog_delete);
 //       modifyButton = view.findViewById(R.id.dialog_modify);
        cancelButton = view.findViewById(R.id.dialog_cancel);

        //Create the listener and give the position of the item to be deleted to MainViewActivity and close the dialog.
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int toBeRemoved = position;
                positionListener.getPosition(toBeRemoved);
                getDialog().dismiss();
            }
        });

        //Opens a new activity for modifying the entries list.
 /*       modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                ModifyDialog mDialog = new ModifyDialog(position);
                mDialog.show(manager, "Modify");
                getDialog().dismiss();

            }
        });

  */

        //Create the listener and close the dialog.
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    /**
     * This method uses the PositionListener
     * @param context
     */
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            positionListener = (PositionListener) getActivity();
        } catch (ClassCastException e) {

        }
    }
}
