package fi.tuni.hoursapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * This class in the adapter for RecyclerView
 */
public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> entryList;
    Context context;
    private OnNoteListener myOnNoteListener;
    private static final int EMPTY_VIEW = 10;

    public MyAdapter(Context c, ArrayList<String> entries, OnNoteListener myOnNoteListener) {
        context = c;
        entryList = entries;
        this.myOnNoteListener = myOnNoteListener;
    }

    /**
     * This class is an empty view
     */
    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * This inner class is the view holder.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView entry;
        OnNoteListener onNoteListener;

        public MyViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            entry = itemView.findViewById(R.id.entryRow);
            itemView.setOnClickListener(this);
            this.onNoteListener = onNoteListener;

        }
        //This method gets the position of the clicked entry.
        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    //This interface helps getting the position of a clicked item.
    public interface OnNoteListener {
        void onNoteClick(int position);

    }

    /**
     * This method is called when the ViewHolder is created. It returns either an empty view or a TextView.
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        if (viewType == EMPTY_VIEW) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false);
            EmptyViewHolder evh = new EmptyViewHolder(view);
            return evh;
        } else {

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_row, parent, false);
            RecyclerView.ViewHolder vh;
            vh = new MyViewHolder(view, myOnNoteListener);
            return vh;
        }
    }

    /**
     * This method binds the entries to the holder.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).entry.setText(entryList.get(position));
        }
    }

    /**
     * This method tells if the view is empty or not.
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

    if (entryList.size() == 0) {
        return EMPTY_VIEW;
    }
    return super.getItemViewType(position);
}

    /**
     * This method returns the size of the array list.
     * @return
     */
    @Override
    public int getItemCount() {
        return entryList.size() > 0 ? entryList.size() : 1;
    }
}

