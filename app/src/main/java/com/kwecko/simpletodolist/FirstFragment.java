package com.kwecko.simpletodolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kwecko.simpletodolist.databinding.FragmentFirstBinding;
import com.kwecko.simpletodolist.todosList.TodoListAdapter;
import com.kwecko.simpletodolist.viewModel.TodoViewModel;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private TodoListAdapter todoListAdapter;
    private RecyclerView recyclerView;
    private TodoViewModel todoViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        todoListAdapter = new TodoListAdapter(new TodoListAdapter.WordDiff());
        recyclerView = binding.getRoot().findViewById(R.id.todoRecyclerView);
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoViewModel.getAllTodosByDate().observe(getViewLifecycleOwner(), todos -> {
            todoListAdapter.submitList(todos);
        });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        recyclerView.setAdapter(todoListAdapter);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}