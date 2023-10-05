package com.example.userregister.presenter;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.userregister.pojo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Presenter {

    private UserListView view;

    private List<User> users;

    private FirebaseFirestore db;

    private String DB_NAME = "base_users";
    private String KEY_NAME = "name";
    private String KEY_LAST_NAME = "lastname";
    private String KEY_AGE = "age";
    private String KEY_SEX = "sex";


    public Presenter(UserListView view) {
        this.view = view;
        this.db = FirebaseFirestore.getInstance();
        users = new ArrayList<>();
        db.collection(DB_NAME).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                /*if (error != null) {
                    view.showError(error.getMessage());
                    return;
                }
                if (value != null && !value.isEmpty()) {

                    users.clear();
                    for (QueryDocumentSnapshot document :
                            value) {
                        // Log.i("MyBase", document.getId()+"=>"+document.getData());
                        //  Toast.makeText(MainActivity.this, document.getId()+"=>"+, Toast.LENGTH_SHORT).show();
                        if(document.get(KEY_NAME)!=null&&document.get(KEY_LAST_NAME)!=null&&document.get(KEY_SEX)!=null) {
                             User user = new User( document.get(KEY_NAME).toString(),document.get(KEY_LAST_NAME).toString() , Integer.parseInt(document.get(KEY_AGE).toString()), document.get(KEY_SEX).toString());
                             users.add(user);
                         }
                        }
                    view.showData(users);
                }*/
                if (value != null && !value.isEmpty()) {
                    users.clear();
                    users = value.toObjects(User.class);
                    view.showData(users);
                }
            }
        });
    }

    public void loadData(String name, String lastname, int age, String sex) {

        User user = new User(name, lastname, age, sex);

        /*Map<String, Object> data = new HashMap<>();
        data.put(KEY_NAME, user.getName());
        data.put(KEY_LAST_NAME, user.getLastname());
        data.put(KEY_AGE, user.getAge());
        data.put(KEY_SEX, user.getSex());*/

        db.collection(DB_NAME)
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        view.completeLoad("DocumentSnapshot added with ID: " + documentReference.getId());
                        Log.i("MyBase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        view.showError("Error adding document" + e.toString());
                        Log.i("MyBase","Error adding document" + e.toString());
                    }
                });

    }
}
