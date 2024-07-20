package com.example.todolist.subActivity;

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
import com.example.todolist.databinding.ActivityAddNewTodoBinding;
import com.example.todolist.entity.ToDo;

public class AddNewTodoActivity extends AppCompatActivity {

    private ActivityAddNewTodoBinding dataBinding;

    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Data binding
        this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_todo);

        setContentView(dataBinding.getRoot()); // This will prevent status bar overlapping

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_addNew), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // View model
        this.viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        ToDo toDo = new ToDo();

        this.dataBinding.setTodo(toDo);
        this.dataBinding.setCrudClickHandler(new CRUDClickHandler(viewModel, toDo));
        this.dataBinding.setDatePickerClickHandler(new DateTimeDialogClickHandler(this, dataBinding.datePickerAddNew));
        this.dataBinding.setTimePickerClickHandler(new DateTimeDialogClickHandler(this, dataBinding.timePickerAddNew));


    }
}