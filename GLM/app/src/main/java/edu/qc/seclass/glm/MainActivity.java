package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySQLiteOpenHelper myDB = new MySQLiteOpenHelper(MainActivity.this);
        myDB.getWritableDatabase();

        Button start = findViewById(R.id.startButton);
        Button init = findViewById(R.id.initialiazation);

        start.setOnClickListener(v -> {
            myDB.initialDB();

            Intent gotoList = new Intent();
            gotoList.setClass(MainActivity.this, ListActivity.class);
            startActivity(gotoList);
        });

        init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDB.getWritableDatabase();
                db.execSQL("delete from "+ "myList");
                db.execSQL("delete from "+ "info");
                db.execSQL("delete from "+ "store");

                Toast.makeText(MainActivity.this,
                        "Data cleared successfully!!",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}