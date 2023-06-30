package edu.qc.seclass.glm;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class TypeActivity extends AppCompatActivity {

    public MySQLiteOpenHelper myDB;
    private ArrayList<String> myData = new ArrayList<>();
    private ArrayList<String> itemName = new ArrayList<>();
    private String listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_listview);

        myDB = new MySQLiteOpenHelper(TypeActivity.this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            myData = bundle.getStringArrayList("type");
            listName = bundle.getString("listName");
        }

        Button button = findViewById(R.id.bk);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListView lv = findViewById(R.id.lv);
        MyAdapter myAdapter = new MyAdapter(myData,this);
        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String typeName = myAdapter.getItem(position).toString();

                itemName.clear();

                Cursor cursor = myDB.getReadableDatabase().rawQuery("SELECT * FROM store WHERE type = '" + typeName+"'",null);
                if(cursor.moveToFirst()){
                    do{
                        int nameColumn = cursor.getColumnIndex("item");
                        String name = cursor.getString(nameColumn);
                        itemName.add(name);
                    }while(cursor.moveToNext());
                }

                Intent gotoList = new Intent();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("item", itemName);
                bundle.putString("type",typeName);
                bundle.putString("listName",listName);
                gotoList.putExtras(bundle);
                gotoList.setClass(TypeActivity.this, ItemActivity.class);
                startActivity(gotoList);
            }
        });

    }
}