package com.example.ergasia1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import android.content.SharedPreferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SQLiteDatabase db = openOrCreateDatabase("gallery.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS maintable (imagename TEXT, paintingdesc TEXT);");
        db.execSQL("DELETE FROM maintable;");

        insertPainting(db, "img", "A beautiful sunset");
        insertPainting(db, "img_1", "A beautiful sunrise");
        insertPainting(db, "img_2", "A beautiful moonlight");


        db.close();

        /*
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("PAINTING_DESC_1", "A beautiful sunset.");
        editor.putString("PAINTING_DESC_2", "A beautiful sunrise.");
        editor.putString("PAINTING_DESC_3", "A beautiful moonlight.");
        editor.apply();
         */

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShowcaseActivity.class);
                //intent.putExtra("KEY_STRING", "PAINTING_DESC_1");
                intent.putExtra("image_name_key", 0);
                startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShowcaseActivity.class);
                //intent.putExtra("KEY_STRING", "PAINTING_DESC_2");
                intent.putExtra("image_name_key", 1);
                startActivity(intent);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShowcaseActivity.class);
                //intent.putExtra("KEY_STRING", "PAINTING_DESC_3");
                intent.putExtra("image_name_key", 2);
                startActivity(intent);
            }
        });
    }

    private void insertPainting(SQLiteDatabase db, String imageName, String paintingDesc) {
        ContentValues values = new ContentValues();
        values.put("imagename", imageName);
        values.put("paintingdesc", paintingDesc);
        db.insert("maintable", null, values);
    }
}