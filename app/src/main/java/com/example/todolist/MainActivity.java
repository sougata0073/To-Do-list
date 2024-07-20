package com.example.todolist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.todolist.adapters.MyViewPagerAdapter;
import com.example.todolist.allTodo.AllTodoFragment;
import com.example.todolist.clickHandlers.MainActivityClickHandler;
import com.example.todolist.database.TodoDatabase;
import com.example.todolist.databinding.ActivityMainBinding;
import com.example.todolist.todaysTodo.TodaysTodoFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private MyViewPagerAdapter viewPagerAdapter;
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    private TodaysTodoFragment todaysTodoFragment;
    private AllTodoFragment allTodoFragment;

    private ActivityMainBinding dataBinding;

    private TodoDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Data binding
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        database = TodoDatabase.getInstance(this);

        setContentView(dataBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // View pager
        this.viewPager = this.dataBinding.viewPagerMain;

        this.todaysTodoFragment = new TodaysTodoFragment();
        this.allTodoFragment = new AllTodoFragment();

        this.fragmentList.add(this.todaysTodoFragment);
        this.fragmentList.add(this.allTodoFragment);

        this.viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager(), getLifecycle(), this.fragmentList);

        this.viewPager.setAdapter(this.viewPagerAdapter);
        this.viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        this.viewPager.setOffscreenPageLimit(this.viewPagerAdapter.getItemCount());

        // Tab layout
        this.tabLayout = this.dataBinding.tabLayoutMain;

        new TabLayoutMediator(this.tabLayout, this.viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
                        if(i == 0){
                            tab.setText("Today's task");
                        } else if (i == 1) {
                            tab.setText("All tasks");
                        }
                    }
                }).attach();

        // Click handler
        this.dataBinding.setClickHandler(new MainActivityClickHandler());
    }
}