package com.example.maps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UsertypeActivity extends AppCompatActivity {
    private RadioGroup userTypeGroup;
    private RadioButton userTypeButton;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usertype);
        userTypeGroup =(RadioGroup)findViewById(R.id.radioGroup);

        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = userTypeGroup.getCheckedRadioButtonId();
                userTypeButton = (RadioButton)findViewById(selectedId);
                if (userTypeButton.getText().equals("Student")){
                    openStudentActivity();
                }
                else if (userTypeButton.getText().equals("Employee")){
                    openEmployeeActivity();
                }
                else {
                    openGuestActivity();
                }
            }
        });
    }
    public void openStudentActivity(){
        // Show user type selection screen
        Intent intent = new Intent(this, StudentActivity.class);
        startActivity(intent);
    }

    public void openEmployeeActivity(){
        // Show user type selection screen
        Intent intent = new Intent(this, EmployeeActivity.class);
        startActivity(intent);
    }

    public void openGuestActivity(){
        // Show user type selection screen
        Intent intent = new Intent(this, GuestActivity.class);
        startActivity(intent);
    }
}