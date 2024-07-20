package com.example.todolist.subActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.todolist.R;
import com.example.todolist.clickHandlers.CRUDClickHandler;
import com.example.todolist.clickHandlers.DateTimeDialogClickHandler;
import com.example.todolist.database.MyViewModel;
import com.example.todolist.databinding.ActivityUpdateTodoBinding;
import com.example.todolist.entity.ToDo;

public class UpdateTodoActivity extends AppCompatActivity {

    private ActivityUpdateTodoBinding dataBinding;

    private MyViewModel viewModel;

    private ToDo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_update_todo);

        setContentView(dataBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_update), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        this.todo = (ToDo) intent.getSerializableExtra("todo");

        this.viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        dataBinding.setTodo(this.todo);
        dataBinding.setCrudClickHandler(new CRUDClickHandler(this.viewModel, this.todo));
        dataBinding.setDatePickerClickHandler(new DateTimeDialogClickHandler(this, this.dataBinding.datePickerUpdate));
        dataBinding.setTimePickerClickHandler(new DateTimeDialogClickHandler(this, this.dataBinding.timePickerUpdate));
    }
}