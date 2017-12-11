package com.example.ayushib.todolist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
/**
 * Created by Ayushi B on 11-Dec-17.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<Task> task;
    protected Context context;
    public RecycleViewAdapter(Context context, List<Task> task) {
        this.task = task;
        this.context = context;
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewHolders viewHolder = null;
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list, parent, false);
        viewHolder = new RecyclerViewHolders(layoutView, task);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.categoryTitle.setText(task.get(position).getTask());
    }
    @Override
    public int getItemCount() {
        return this.task.size();
    }
}
