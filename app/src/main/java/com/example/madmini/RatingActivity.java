package com.example.madmini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.madmini.Model.Rating;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RatingActivity extends AppCompatActivity {

    DatabaseReference reff;
    private RatingBar rating1,rating2;
    int totalRating;
    Button btnSubmit,btnView;
    float RatedValue;
    long maxid = 0;
    float totalPercentage;
    Rating rating;
    EditText eTxtName;
    String UserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        rating1 = (RatingBar)findViewById(R.id.ratingBar);

        eTxtName = (EditText)findViewById(R.id.edUserName) ;
        rating1.setMax(5);
        rating1.setRating((float) 3.5);



        btnSubmit=(Button)findViewById(R.id.btn_submit);
        btnView=(Button)findViewById(R.id.btn_view);


        rating = new Rating();

        reff = FirebaseDatabase.getInstance().getReference().child("Ratings");

        //set ID
        reff.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                    maxid=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                totalRating = rating1.getNumStars();


                RatedValue = rating1.getRating();

                totalPercentage = RatedValue/totalRating;


                Toast.makeText(RatingActivity.this, "Your rating is"+RatedValue+"/"+totalRating,Toast.LENGTH_LONG).show();



                        rating.setPrecentage(totalPercentage);
                        rating.setUserName(eTxtName.getText().toString().trim());
                        rating.setRatedvalue(RatedValue);


                        //set ID
                        reff.child(String.valueOf(maxid+1)).setValue(rating);

                        //reff.push().setValue(appoinment);
                        Toast.makeText(RatingActivity.this,"Insert successfuly!!",Toast.LENGTH_LONG).show();



                    }
                });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RatingList.class);
                startActivity(intent);
            }
        });



    }
}