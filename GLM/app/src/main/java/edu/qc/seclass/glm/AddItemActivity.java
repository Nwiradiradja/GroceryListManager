package edu.qc.seclass.glm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {

    private ArrayList<String> myType = new ArrayList<>();
    private ArrayList<String> items = new ArrayList<>();
    public MySQLiteOpenHelper myDB;
    private String listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        myDB = new MySQLiteOpenHelper(AddItemActivity.this);

        Bundle bundle = getIntent().getExtras();
        listName = bundle.getString("listName");

        Button back = findViewById(R.id.bk1);
        back.setOnClickListener(v -> finish());

        Button sb = findViewById(R.id.selectButton);
        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myType.clear();
                Cursor cursor = myDB.getReadableDatabase().rawQuery("SELECT DISTINCT type FROM store",null);
                if(cursor.moveToFirst()){
                    do{
                        int nameColumn = cursor.getColumnIndex("type");
                        String name = cursor.getString(nameColumn);
                        myType.add(name);
                    }while(cursor.moveToNext());
                }

                Intent gotoList = new Intent();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("type", myType);
                bundle.putString("listName",listName);
                gotoList.putExtras(bundle);
                gotoList.setClass(AddItemActivity.this, TypeActivity.class);
                startActivity(gotoList);
            }
        });

        Button sb2 = findViewById(R.id.searchButton);
        sb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ab = new AlertDialog.Builder(AddItemActivity.this);
                ab.setTitle("Enter the item name: ");
                final EditText myET = new EditText(AddItemActivity.this);
                ab.setView(myET);
                ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = myET.getText().toString();
                        items.clear();

                        Cursor cursor = myDB.getReadableDatabase().rawQuery("SELECT item FROM store where item LIKE '%"+name+"%'",null);
                        if(cursor.moveToFirst()){
                            do{
                                int nameColumn = cursor.getColumnIndex("item");
                                String name1 = cursor.getString(nameColumn);
                                items.add(name1);
                            }while(cursor.moveToNext());

                            Intent gotoList = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList("items", items);
                            bundle.putBoolean("isFound",true);
                            bundle.putString("listName",listName);
                            bundle.putString("title","Similar items");
                            gotoList.putExtras(bundle);
                            gotoList.setClass(AddItemActivity.this, ItemActivity.class);
                            startActivity(gotoList);
                        }else{
                            items.add("Not Found. Please click here to add the new item to the DB.");
                            Intent gotoList = new Intent();
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList("items", items);
                            bundle.putBoolean("isFound",false);
                            gotoList.putExtras(bundle);
                            gotoList.setClass(AddItemActivity.this, ItemActivity.class);
                            startActivity(gotoList);
                        }
                    }
                });
                ab.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                ab.setCancelable(true);
                AlertDialog myDlg = ab.create();
                myDlg.setCanceledOnTouchOutside(true);
                myDlg.show();
            }
        });


















    }





}