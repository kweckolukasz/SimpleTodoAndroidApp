package com.kwecko.simpletodolist.todosList;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.kwecko.simpletodolist.model.Todo;

public class TodoListAdapter extends ListAdapter<Todo, TodoItemViewHolder> {


    protected TodoListAdapter(@NonNull DiffUtil.ItemCallback<Todo> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TodoItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        Todo current = getItem(position);
        holder.bind(current.getText());
    }

    static class WordDiff extends DiffUtil.ItemCallback<Todo>{
        @Override
        public boolean areItemsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Todo oldItem, @NonNull Todo newItem) {
            return oldItem.getText().equals(newItem.getText());
        }
    }
}
