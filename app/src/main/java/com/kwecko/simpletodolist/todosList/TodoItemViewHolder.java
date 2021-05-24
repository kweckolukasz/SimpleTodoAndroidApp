package com.kwecko.simpletodolist.todosList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwecko.simpletodolist.R;

public class TodoItemViewHolder extends RecyclerView.ViewHolder {

    private final TextView todoText;

    public TodoItemViewHolder(@NonNull View itemView) {
        super(itemView);
        todoText = itemView.findViewById(R.id.todoItemText);
    }

    public void bind(String text){
        todoText.setText(text);
    }

    static TodoItemViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new TodoItemViewHolder(view);
    }
}
