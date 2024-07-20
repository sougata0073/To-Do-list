package com.example.todolist.allTodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.adapters.MyRecyclerViewAdapter;
import com.example.todolist.database.MyViewModel;
import com.example.todolist.databinding.FragmentAllTodoBinding;
import com.example.todolist.entity.ToDo;

import java.util.ArrayList;
import java.util.List;

public class AllTodoFragment extends Fragment {

    private List<ToDo> toDoList = new ArrayList<>();
    private RecyclerView recyclerView;

    private MyRecyclerViewAdapter recyclerViewAdapter;

    private MyViewModel viewModel;

    private FragmentAllTodoBinding dataBinding;

    public AllTodoFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_todo, container, false);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        recyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), toDoList, viewModel);

        recyclerView = dataBinding.recyclerViewAllTodo;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel.getAllTodo().observe(getViewLifecycleOwner(),
                                        todoList -> {
                                            this.toDoList.clear();
                                            this.toDoList.addAll(todoList);
                                            this.recyclerViewAdapter.notifyDataSetChanged();
                                        });

        return dataBinding.getRoot();
    }
}