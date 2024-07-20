package com.example.todolist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.clickHandlers.ListItemClickHandler;
import com.example.todolist.database.MyViewModel;
import com.example.todolist.databinding.ItemListLayoutBinding;
import com.example.todolist.entity.ToDo;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.TodoViewHolder> {

    private Context context;
    private List<ToDo> todoList;
    private MyViewModel viewModel;

    public MyRecyclerViewAdapter(Context context, List<ToDo> todoList, MyViewModel viewModel){
        this.context = context;
        this.todoList = todoList;
        this.viewModel = viewModel;
    }

    class TodoViewHolder extends RecyclerView.ViewHolder{
        ItemListLayoutBinding dataBinding;

        public TodoViewHolder(@NonNull ItemListLayoutBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
        }
    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemListLayoutBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_list_layout, parent, false);

        return new TodoViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        ToDo todo = this.todoList.get(position);
        holder.dataBinding.setTodo(todo);
        holder.dataBinding.setClickHandler(new ListItemClickHandler(todo, viewModel));
    }

    @Override
    public int getItemCount() {
        if(todoList == null){
            return 0;
        }
        return this.todoList.size();
    }
}
