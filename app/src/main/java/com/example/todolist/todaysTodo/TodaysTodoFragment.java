package com.example.todolist.todaysTodo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todolist.R;
import com.example.todolist.adapters.MyRecyclerViewAdapter;
import com.example.todolist.database.MyViewModel;
import com.example.todolist.databinding.FragmentTodaysTodoBinding;
import com.example.todolist.entity.ToDo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TodaysTodoFragment extends Fragment {

    private List<ToDo> toDoList = new ArrayList<>();
    private RecyclerView recyclerView;

    private MyRecyclerViewAdapter recyclerViewAdapter;

    private MyViewModel viewModel;

    private FragmentTodaysTodoBinding dataBinding;

    public TodaysTodoFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_todays_todo, container, false);

        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        recyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), toDoList, viewModel);

        recyclerView = dataBinding.recyclerViewTodayTodo;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerViewAdapter);

        viewModel.getAllTodo().observe(getViewLifecycleOwner(),
                newTodoList -> {
                    this.toDoList.clear();

                    Calendar calendar = Calendar.getInstance();

                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);
                    int day = calendar.get(Calendar.DAY_OF_MONTH);

                    String todayDate = day + "-" + month + "-" + year;

                    for(int i = 0; i < newTodoList.size(); i++){
                        ToDo t = newTodoList.get(i);
                        if(t.getDate().equals(todayDate)){
                            toDoList.add(t);
                        }
                    }

                    this.recyclerViewAdapter.notifyDataSetChanged();
                });

        return dataBinding.getRoot();
    }
}