package com.example.userregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.userregister.adapter.UserAdapter;
import com.example.userregister.pojo.User;
import com.example.userregister.presenter.Presenter;
import com.example.userregister.presenter.UserListView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UserListView {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    public Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);
        recyclerView = findViewById(R.id.recyclerViewUsers);
        adapter = new UserAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setUsers(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String t_name = extras.getString("name");
            String t_lastname = extras.getString("lastname");
            int t_age = extras.getInt("age");
            String t_sex = extras.getString("sex");
            Boolean key_load = extras.getBoolean("key_load");
            if (key_load) {
                presenter.loadData(t_name, t_lastname, t_age, t_sex);
            }
            //The key argument here must match that used in the other activity
        }
    }

    public void onClickAddUser(View view) {
        Intent intent = new Intent(MainActivity.this, AddUserActivity.class);
        startActivity(intent);
    }

    @Override
    public void showData(List<User> users) {
        adapter.setUsers(users);
    }

    @Override
    public void showError(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void completeLoad(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


}