package com.example.tema2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements UserListAdapter.OnDeleteClickListener{
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userRepository = new UserRepository(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final UserListAdapter userListAdapter = new UserListAdapter(this,this);
        recyclerView.setAdapter(userListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userRepository.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userListAdapter.setUsers(users);
            }
        });

        // text fields
        final EditText full_name_field = findViewById(R.id.name);
        final EditText mark_field = findViewById(R.id.mark);

        // buttons
        Button add_user_btn = findViewById(R.id.add_user);

        add_user_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = UUID.randomUUID().toString();
                String name = full_name_field.getText().toString();
                int mark = Integer.parseInt(mark_field.getText().toString());
                User user = new User(user_id, name, mark);
                userRepository.insert(user);

                Toast.makeText(
                        getApplicationContext(),
                        "Cica a inserat",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void OnDeleteClickListener(User user) {
        userRepository.delete(user);
    }
}
