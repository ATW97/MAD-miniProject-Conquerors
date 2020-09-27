package com.example.madmini;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.madmini.ui.Login;
import com.google.firebase.auth.FirebaseAuth;

public class Adminpanel extends AppCompatActivity {
    Button logout;

    private Button category;

    private Button checkOrdersbtn,appoinments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpanel);
        category= findViewById(R.id.btn_category);
        checkOrdersbtn = findViewById(R.id.btnShowOrders);
        appoinments = findViewById(R.id.appointmentsShow);
        logout=findViewById(R.id.btnadminpanel);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Adminpanel.this,AdminPetCategory.class);
                startActivity(intent);

            }
        });

        checkOrdersbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Adminpanel.this, AdminNewOrderActivity.class);
                startActivity(intent);
            }
        });

      appoinments.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(Adminpanel.this,AdminAPPOListActivity.class);
              startActivity(intent);
          }
      });
    }
}