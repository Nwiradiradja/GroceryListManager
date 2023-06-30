package edu.qc.seclass.glm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;



public class ListInnerActivity extends AppCompatActivity {

    public MySQLiteOpenHelper myDB;
    private String listName;

    private ArrayList<String> item = new ArrayList<>();
    private ArrayList<TextView> itemObj = new ArrayList<>();
    private ArrayList<String> type = new ArrayList<>();
    private ArrayList<TextView> typeObj = new ArrayList<>();
    private ArrayList<String> quantity = new ArrayList<>();
    private ArrayList<TextView> quantityObj = new ArrayList<>();
    private ArrayList<String> status = new ArrayList<>();
    private ArrayList<TextView> checkOffItem = new ArrayList<>();
    private ArrayList<CheckBox> myCheckBox = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDB = new MySQLiteOpenHelper(ListInnerActivity.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_inner);

        Bundle bundle = getIntent().getExtras();
        listName = bundle.getString("listName");

        TextView textView = findViewById(R.id.listID);
        textView.setText(listName);

        Button back = findViewById(R.id.bk3);
        back.setOnClickListener(v -> {
            Intent gotoList = new Intent();
            gotoList.setClass(ListInnerActivity.this, ListActivity.class);
            startActivity(gotoList);
        });


        CheckBox checkBox = findViewById(R.id.checkAll);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()){
                    for(int i=0;i<myCheckBox.size();i++){
                        myCheckBox.get(i).setChecked(true);
                    }
                }else{
                    for(int i=0;i<myCheckBox.size();i++){
                        myCheckBox.get(i).setChecked(false);
                    }
                }
            }
        });


        Cursor cursor = myDB.getReadableDatabase().rawQuery("SELECT * FROM info",null);
        if(cursor.moveToFirst()) {
            cursor = myDB.getReadableDatabase().rawQuery("SELECT * FROM info WHERE list = '" + listName+"'",null);

            if (cursor.moveToFirst()) {
                do {
                    int nameColumn = cursor.getColumnIndex("type");
                    String temp = cursor.getString(nameColumn);
                    type.add(temp);
                } while (cursor.moveToNext());
            }

            if (cursor.moveToFirst()) {
                do {
                    int nameColumn = cursor.getColumnIndex("item");
                    String temp = cursor.getString(nameColumn);
                    item.add(temp);
                } while (cursor.moveToNext());
            }

            if (cursor.moveToFirst()) {
                do {
                    int nameColumn = cursor.getColumnIndex("quantity");
                    String temp = cursor.getString(nameColumn);
                    quantity.add(temp);
                } while (cursor.moveToNext());
            }

            if (cursor.moveToFirst()) {
                do {
                    int nameColumn = cursor.getColumnIndex("status");
                    String temp = cursor.getString(nameColumn);
                    status.add(temp);
                } while (cursor.moveToNext());
            }


            if (item.size() != 0) {

                for (int i = 0; i < item.size(); i++) {
                    GridLayout gridLayout = findViewById(R.id.gridlayout);

                    CheckBox cb = new CheckBox(ListInnerActivity.this);
                    cb.setGravity(Gravity.CENTER);
                    cb.setWidth(180);
                    myCheckBox.add(cb);
                    gridLayout.addView(cb);

                    TextView tv = new TextView(ListInnerActivity.this);
                    tv.setText(item.get(i));
                    tv.setGravity(Gravity.CENTER);
                    tv.setWidth(180);
                    itemObj.add(tv);
                    gridLayout.addView(tv);

                    TextView tv2 = new TextView(ListInnerActivity.this);
                    tv2.setText(type.get(i));
                    tv2.setGravity(Gravity.CENTER);
                    tv2.setWidth(180);
                    typeObj.add(tv2);
                    gridLayout.addView(tv2);

                    TextView tv3 = new TextView(ListInnerActivity.this);
                    tv3.setText(quantity.get(i));
                    tv3.setGravity(Gravity.CENTER);
                    tv3.setWidth(180);
                    quantityObj.add(tv3);
                    gridLayout.addView(tv3);

                    TextView tv4 = new TextView(ListInnerActivity.this);
                    tv4.setGravity(Gravity.CENTER);
                    tv4.setWidth(180);
                    tv4.setText(status.get(i));
                    checkOffItem.add(tv4);
                    gridLayout.addView(tv4);
                }
            }
        }
    }

    public void clickAddItem(View view){
        Intent gotoList = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString("listName",listName);
        gotoList.putExtras(bundle);
        gotoList.setClass(ListInnerActivity.this, AddItemActivity.class);
        startActivity(gotoList);
    }

    public void clickDelItem(View view){
        int count = 0;
        for(int i=0;i<myCheckBox.size();i++){
            if(myCheckBox.get(i).isChecked()){
                count++;
            }
        }
        if(count == 0){
            Toast.makeText(ListInnerActivity.this,"You must select at least one item to delete.",
                    Toast.LENGTH_LONG).show();
        }else{
            GridLayout gridLayout = findViewById(R.id.gridlayout);
            for(int i=0;i<myCheckBox.size();i++){
                if(myCheckBox.get(i).isChecked()){
                    SQLiteDatabase db = myDB.getWritableDatabase();

                    db.delete("info","item = ? and list= ?",new String[] {item.get(i), listName});
                    gridLayout.removeView(myCheckBox.get(i));
                    myCheckBox.remove(i);
                    gridLayout.removeView(itemObj.get(i));
                    itemObj.remove(i);
                    item.remove(i);
                    gridLayout.removeView(typeObj.get(i));
                    typeObj.remove(i);
                    type.remove(i);
                    gridLayout.removeView(quantityObj.get(i));
                    quantityObj.remove(i);
                    quantity.remove(i);
                    gridLayout.removeView(checkOffItem.get(i));
                    status.remove(i);
                    checkOffItem.remove(i);
                    i--;
                }
            }
            Toast.makeText(ListInnerActivity.this,"Deleted.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void clickChange(View view){
        int count = 0;
        int index = 0;
        for(int i = 0; i<myCheckBox.size(); i++){
            if(myCheckBox.get(i).isChecked()){
                count++;
                index = i;
            }
        }
        String tempName = item.get(index);

        if(count == 0){
            Toast.makeText(ListInnerActivity.this,"You must select one item to change quantity.",
                    Toast.LENGTH_LONG).show();
        }else if(count != 1){
            Toast.makeText(ListInnerActivity.this,"You can only select one item to change quantity.",
                    Toast.LENGTH_LONG).show();
        }else{
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("Enter the quantity: ");
            final EditText myET = new EditText(this);
            myET.setRawInputType(Configuration.KEYBOARD_12KEY);
            ab.setView(myET);
            int finalIndex = index;
            ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String newQtt = myET.getText().toString();

                    Boolean isInt = false;
                    for(int i=0; i<newQtt.length();i++){
                        if(newQtt.charAt(0) == '0'){
                            break;
                        }else if(!Character.isDigit(newQtt.charAt(i))){
                            isInt = false;
                            break;
                        }else{
                            isInt = true;
                        }
                    }
                    if(isInt){
                        SQLiteDatabase db = myDB.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("quantity",newQtt);
                        db.update("info",values,"item= ? and list= ?",new String[]{tempName,listName});
                        values.clear();
                        quantity.set(finalIndex,newQtt);
                        quantityObj.get(finalIndex).setText(newQtt);

                    }else{
                        Toast.makeText(ListInnerActivity.this,"Input is not an Integer.",
                                Toast.LENGTH_LONG).show();
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

    public void clickCekItem(View view){
        int count = 0;
        for(int i=0;i<myCheckBox.size();i++){
            if(myCheckBox.get(i).isChecked()){
                count++;
            }
        }
        if(count==0){
            Toast.makeText(ListInnerActivity.this,"You must select at least one item to check off.",
                    Toast.LENGTH_LONG).show();
        }else{
            for(int i = 0; i< myCheckBox.size();i++){
                if(myCheckBox.get(i).isChecked()){
                    SQLiteDatabase db = myDB.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("status","checked");
                    db.update("info",values,"item= ? and list= ?",new String[]{item.get(i),listName});
                    values.clear();
                    status.set(i,"checked");
                    checkOffItem.get(i).setText("checked");
                }
            }
        }
    }

    public void clickClear(View view){
        for(int i = 0; i< item.size();i++){
            if(status.get(i).equals("checked")){
                SQLiteDatabase db = myDB.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("status"," ");
                db.update("info",values,"item= ? and list= ?",new String[]{item.get(i),listName});
                values.clear();
                status.set(i," ");
                checkOffItem.get(i).setText(" ");
            }
        }
    }
}