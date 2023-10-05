package com.example.userregister;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userregister.pojo.User;
import com.example.userregister.presenter.Presenter;
import com.example.userregister.presenter.UserListView;

import java.util.List;

public class AddUserActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextAge;
    private Spinner spinnerSex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextAge = findViewById(R.id.editTextAge);
        spinnerSex = findViewById(R.id.spinnerSex);
        //editTextSex = findViewById(R.id.editTextSex);
    }

    public void onClickAddUser(View view) {
        String t_name = editTextName.getText().toString().trim();
        String t_lastname = editTextLastName.getText().toString().trim();
        int t_age = Integer.parseInt(editTextAge.getText().toString());
        String t_sex = spinnerSex.getSelectedItem().toString().trim();
        if (!t_name.isEmpty() && !t_lastname.isEmpty() && t_age > 0 && !t_sex.isEmpty()) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("name", t_name);
            intent.putExtra("lastname", t_lastname);
            intent.putExtra("age", t_age);
            intent.putExtra("sex", t_sex);
            intent.putExtra("key_load", true);
            //  presenter.loadData(t_name, t_lastname, t_age, t_sex);
            startActivity(intent);
        }
    }
}