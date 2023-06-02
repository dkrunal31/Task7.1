package com.example.task71;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.task71.databinding.ActivityCreateBinding;
import com.example.task71.databinding.ActivityMainBinding;

public class CreateActivity extends AppCompatActivity {

    ActivityCreateBinding binding;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //create data binding
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get input data
                String name = binding.nameEditText.getText().toString();
                String phone = binding.phoneEditText.getText().toString();
                String description = binding.descriptionEditText.getText().toString();
                String date = binding.dateEditText.getText().toString();
                String location = binding.locationEditText.getText().toString();
                String lostFound = "";
                //check if item radio button is lost or found
                if(binding.lostRadioButton.isChecked()) {
                    lostFound = "lost";
                } else if (binding.foundRadioButton.isChecked()) {
                    lostFound = "found";
                }

                //if any information is missing send user a toast advising to complete the entire form
                //else insert the item to the database
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(description) ||
                        TextUtils.isEmpty(date) || TextUtils.isEmpty(location) || TextUtils.isEmpty(lostFound)) {
                    Toast.makeText(CreateActivity.this, "Please fill in all information", Toast.LENGTH_SHORT).show();
                } else {
                    Item newItem = new Item(0, name, lostFound, phone, description, date, location);
                    databaseHelper.insertItem(newItem);
                    Intent newIntent = new Intent(CreateActivity.this, MainActivity.class);
                    startActivityForResult(newIntent, 1);
                    finish();
                }


            }
        });

    }
}