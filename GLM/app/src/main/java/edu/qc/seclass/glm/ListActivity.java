package edu.qc.seclass.glm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.*;


public class ListActivity extends AppCompatActivity {

    public MySQLiteOpenHelper myDB;

    List<CheckBox> myCB = new ArrayList<>();
    String listName = "";
    ArrayList<String> myList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myDB = new MySQLiteOpenHelper(ListActivity.this);

        Cursor cursor = myDB.getReadableDatabase().rawQuery("SELECT name FROM myList",null);
        if(cursor.moveToFirst()){
            do{
                int nameColumn = cursor.getColumnIndex("name");
                String name = cursor.getString(nameColumn);

                LinearLayout linearLayout = findViewById(R.id.myList);
                CheckBox cb1 = new CheckBox(ListActivity.this);
                cb1.setText(name);
                cb1.setTextSize(30);
                cb1.setGravity(Gravity.CENTER);
                myCB.add(cb1);
                myList.add(name);
                linearLayout.addView(cb1);
            }while(cursor.moveToNext());
        }

        CheckBox cb0 = findViewById(R.id.listSystem);
        cb0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cb0.isChecked()){
                    for(int i=0;i<myCB.size();i++){
                        myCB.get(i).setChecked(true);
                    }
                }else{
                    for(int i=0;i<myCB.size();i++){
                        myCB.get(i).setChecked(false);
                    }
                }
            }
        });
    }

    @SuppressLint("ResourceType")
    public void clickEnter(View view){
        int count = 0;

        for(int i = 0; i<myCB.size(); i++){
            if(myCB.get(i).isChecked()){
                listName = myCB.get(i).getText().toString();
                count++;
            }
        }
        if(count == 0){
            Toast.makeText(ListActivity.this,"You must select one list to go in.",
                    Toast.LENGTH_LONG).show();
        }else if(count != 1){
            Toast.makeText(ListActivity.this,"You can only select one list to go in.",
                    Toast.LENGTH_LONG).show();
        }else{
            Intent gotoList = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("listName", listName);
            gotoList.putExtras(bundle);
            gotoList.setClass(ListActivity.this, ListInnerActivity.class);
            startActivity(gotoList);
        }
    }


    public void clickAdd(View view ){
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Enter a new list name: ");
        final EditText myET = new EditText(this);
        ab.setView(myET);
        ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String name = myET.getText().toString();
                if(myCB.size()>10){
                    Toast.makeText(ListActivity.this,"Failed. You have too much lists.",
                            Toast.LENGTH_LONG).show();
                }else if(myList.contains(name)){
                    Toast.makeText(ListActivity.this,"Lists cannot have the same name.",
                            Toast.LENGTH_LONG).show();
                }else{
                    LinearLayout ll = findViewById(R.id.myList);
                    CheckBox cb1 = new CheckBox(ListActivity.this);
                    cb1.setText(name);
                    cb1.setTextSize(30);
                    cb1.setGravity(Gravity.CENTER);
                    myCB.add(cb1);
                    myList.add(cb1.getText().toString());
                    ll.addView(cb1);

                    SQLiteDatabase db = myDB.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put("name", name);
                    db.insert("myList",null,cv);
                    cv.clear();
                };
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

    public void clickDel(View view){
        int count = 0;
        for(int i=0; i<myCB.size(); i++){
            if(myCB.get(i).isChecked()){
                count++;
            }
        }
        if(count == 0){
            Toast.makeText(ListActivity.this,"You must select at least one list to delete.",
                    Toast.LENGTH_LONG).show();
        }else{
            LinearLayout ll = findViewById(R.id.myList);
            for(int i=0;i<myCB.size();i++){
                if(myCB.get(i).isChecked()){
                    SQLiteDatabase db = myDB.getWritableDatabase();
                    db.delete("myList","name = ?",new String[] {myCB.get(i).getText().toString()});
                    db.delete("info","list = ?",new String[] {myCB.get(i).getText().toString()});
                    ll.removeView(myCB.get(i));
                    myList.remove(i);
                    myCB.remove(i);
                    i--;
                }
            }
            Toast.makeText(ListActivity.this,"Deleted.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void clickRename(View view){
        int count = 0;
        for(int i=0;i<myCB.size();i++){
            if(myCB.get(i).isChecked()){
                count++;
            }
        }
        if(count == 0){
            Toast.makeText(ListActivity.this,"You must select one list to rename.",
                    Toast.LENGTH_LONG).show();
        }else if(count != 1){
            Toast.makeText(ListActivity.this,"You can only select one list to rename.",
                    Toast.LENGTH_LONG).show();
        }else{
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("Rename the list: ");
            final EditText myET = new EditText(this);
            ab.setView(myET);
            ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String name = myET.getText().toString();
                    String oldName = "";
                    if(myList.contains(name)){
                        Toast.makeText(ListActivity.this,"Lists cannot have the same name.",
                                Toast.LENGTH_LONG).show();
                    }else {
                        for (int i = 0; i < myCB.size(); i++) {
                            if (myCB.get(i).isChecked()) {
                                oldName = myCB.get(i).getText().toString();
                                myList.set(i,name);
                                myCB.get(i).setText(name);
                            }
                        }
                        SQLiteDatabase db = myDB.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("name",name);
                        db.update("myList",cv,"name= ?",new String[]{oldName});
                        cv.clear();

                        cv.put("list",name);
                        db.update("info",cv,"list= ?",new String[]{oldName});
                        cv.clear();
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
    }

}