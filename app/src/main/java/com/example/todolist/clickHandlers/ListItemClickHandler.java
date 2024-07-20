package com.example.todolist.clickHandlers;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

import androidx.appcompat.app.AlertDialog;

import com.example.todolist.R;
import com.example.todolist.database.MyViewModel;
import com.example.todolist.entity.ToDo;
import com.example.todolist.subActivity.UpdateTodoActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ListItemClickHandler {
    private ToDo toDo;
    private MyViewModel viewModel;

    public ListItemClickHandler(ToDo toDo, MyViewModel viewModel) {
        this.toDo = toDo;
        this.viewModel = viewModel;
    }

    public void onNotesClicked(View view) {
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(view.getContext());

        alertDialogBuilder.setTitle("Alert");
        alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialogBuilder.setMessage(this.toDo.getNote());

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                int blackColor = view.getContext().getResources().getColor(R.color.black, view.getContext().getTheme());

                Button positiveButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setTextColor(blackColor);
            }
        });

        alertDialog.show();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        this.toDo.setDone(isChecked);
        this.viewModel.updateTodo(this.toDo);
    }

    public void onEditClicked(View view) {
        Intent intent = new Intent(view.getContext(), UpdateTodoActivity.class);
        intent.putExtra("todo", this.toDo);
        view.getContext().startActivity(intent);
    }

    public void onDeleteClicked(View view) {

        if (this.toDo.getDone()) {
            viewModel.deleteTodo(toDo);
            return;
        }

        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(view.getContext());

        alertDialogBuilder.setTitle("Alert");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.deleteTodo(toDo);
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialogBuilder.setMessage("You haven't completed the task yet, do you still want to delete this task?");

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                int blackColor = view.getContext().getResources().getColor(R.color.black, view.getContext().getTheme());
                int redColor = view.getContext().getResources().getColor(R.color.red, view.getContext().getTheme());

                Button positiveButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setTextColor(redColor);

                Button negativeButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeButton.setTextColor(blackColor);
            }
        });

        alertDialog.show();
    }
}
