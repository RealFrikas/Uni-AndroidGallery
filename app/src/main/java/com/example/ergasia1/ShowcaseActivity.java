package com.example.ergasia1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowcaseActivity extends AppCompatActivity {

    MyTts myTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_showcase);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        TextView description = findViewById(R.id.descriptiontext);
        ImageView image = findViewById(R.id.imageView);
        int imageId = getIntent().getIntExtra("image_name_key", -1);


        SQLiteDatabase db = openOrCreateDatabase("gallery.db", MODE_PRIVATE, null);

        Cursor cursor = db.rawQuery("SELECT * FROM maintable", null);
        cursor.moveToPosition(imageId);
        String imagename = cursor.getString(cursor.getColumnIndexOrThrow("imagename"));
        int imageResId = getResources().getIdentifier(imagename, "drawable", getPackageName());
        image.setImageResource(imageResId);

        String paintingDesc = cursor.getString(cursor.getColumnIndexOrThrow("paintingdesc"));
        description.setText(paintingDesc);

        cursor.close();
        db.close();

        /*
        if (imageId == 0){
            image.setImageResource(R.drawable.img);
        } else if (imageId == 1) {
            image.setImageResource(R.drawable.img_1);
        } else {
            image.setImageResource(R.drawable.img_2);
        }
        */

        //String receivedString = getIntent().getStringExtra("KEY_STRING");
        //SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        //String paintingDesc = sharedPreferences.getString(receivedString, "No description available");
        //description.setText(paintingDesc);


        Button backbutton = findViewById(R.id.button);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowcaseActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        myTts = new MyTts(this);
        Button speechbutton = findViewById(R.id.button4);
        speechbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTts.speak(paintingDesc);
            }
        });
    }
}