package com.example.maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeActivity extends AppCompatActivity {
    private RadioGroup permitTypeGroup;
    private RadioButton permitTypeButton;
    private Button submit;
    private String Permit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        permitTypeGroup =(RadioGroup)findViewById(R.id.radioGroup);

        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = permitTypeGroup.getCheckedRadioButtonId();
                permitTypeButton = (RadioButton)findViewById(selectedId);
                Permit = (String) permitTypeButton.getText();
                openMapActivity();
            }
        });
    }
    public void openMapActivity(){
        // Show user type selection screen
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("user", "Employee");
        intent.putExtra("permit", Permit);
        startActivity(intent);
    }
}