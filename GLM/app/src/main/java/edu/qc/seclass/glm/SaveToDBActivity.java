package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;

public class SaveToDBActivity extends AppCompatActivity {

    public MySQLiteOpenHelper myDB;
    private ArrayList<CheckBox> myCB = new ArrayList<>();
    private String selectedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        myDB = new MySQLiteOpenHelper(SaveToDBActivity.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_to_db);

        Button button = findViewById(R.id.bk4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoList = new Intent();
                gotoList.setClass(SaveToDBActivity.this, ListActivity.class);
                startActivity(gotoList);
            }
        });

        EditText editText1 = findViewById(R.id.newItemName);

        Button button2 = findViewById(R.id.addToDB);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;

                for(int i = 0; i<myCB.size(); i++){
                    if(myCB.get(i).isChecked()){
                        selectedType = myCB.get(i).getText().toString();
                        count++;
                    }
                }
                if(editText1.getText().toString().isEmpty() || selectedType == null){
                    Toast.makeText(SaveToDBActivity.this,
                            "Name or type cannot be empty!",
                            Toast.LENGTH_SHORT).show();
                }else if(count !=1) {
                    Toast.makeText(SaveToDBActivity.this,
                            "You can only select one type!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SaveToDBActivity.this,
                            "New item added!",
                            Toast.LENGTH_SHORT).show();

                    SQLiteDatabase db = myDB.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("type", selectedType);
                    values.put("item", editText1.getText().toString());
                    db.insert("store",null,values);
                    values.clear();

                    Intent gotoList = new Intent();
                    gotoList.setClass(SaveToDBActivity.this, ListActivity.class);
                    startActivity(gotoList);
                }
            }
        });

        Cursor cursor = myDB.getReadableDatabase().rawQuery("SELECT DISTINCT type FROM store",null);
        if(cursor.moveToFirst()){
            do{
                int nameColumn = cursor.getColumnIndex("type");
                String name = cursor.getString(nameColumn);

                LinearLayout ll = findViewById(R.id.showType);
                CheckBox cb = new CheckBox(SaveToDBActivity.this);
                cb.setText(name);
                cb.setTextSize(30);
                myCB.add(cb);
                ll.addView(cb);
            }while(cursor.moveToNext());
        }
    }
}