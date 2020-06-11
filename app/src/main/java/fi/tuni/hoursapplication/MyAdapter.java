package fi.tuni.hoursapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<String> entryList;
    Context context;
    private static final int EMPTY_VIEW = 10;

    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
    public MyAdapter(Context c, ArrayList<String> entries) {
        context = c;
        entryList = entries;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView entry;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            entry = itemView.findViewById(R.id.entryRow);
        }
    }


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
            vh = new MyViewHolder(view);
            return vh;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).entry.setText(entryList.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {

    if (entryList.size() == 0) {
        return EMPTY_VIEW;
    }
    return super.getItemViewType(position);
}

    @Override
    public int getItemCount() {
        return entryList.size() > 0 ? entryList.size() : 1;
    }
}

