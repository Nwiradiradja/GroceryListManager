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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {

    private ArrayList<String> myData = new ArrayList<>();
    private String typeName = new String();
    public MySQLiteOpenHelper myDB;
    private boolean isFound = new Boolean(true);
    private String listName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myDB = new MySQLiteOpenHelper(ItemActivity.this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_listview);

        Button button = findViewById(R.id.bk2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();

        if(bundle.getStringArrayList("item") != null){
            myData = bundle.getStringArrayList("item");
            typeName = bundle.getString("type");
        }else{
            myData = bundle.getStringArrayList("items");
            isFound = bundle.getBoolean("isFound");
        }

        if(bundle.getString("title") != null){
            TextView textView = findViewById(R.id.itemTitle);
            textView.setText(bundle.getString("title"));
        }

        listName = bundle.getString("listName");

        ListView lv = findViewById(R.id.itemListView);
        MyAdapter myAdapter = new MyAdapter(myData,this);
        lv.setAdapter(myAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itName = myData.get(position);

                if(isFound){
                    Cursor cursor = myDB.getReadableDatabase().rawQuery("SELECT * FROM store where item = '"+itName+"'",null);
                    if(cursor.moveToFirst()){
                        do{
                            int nameColumn = cursor.getColumnIndex("type");
                            String name = cursor.getString(nameColumn);
                            typeName = name;
                        }while(cursor.moveToNext());
                    }

                    AlertDialog.Builder ab = new AlertDialog.Builder(ItemActivity.this);
                    ab.setTitle("Enter the Quantity: ");
                    final EditText myET = new EditText(ItemActivity.this);
                    myET.setRawInputType(Configuration.KEYBOARD_12KEY);
                    ab.setView(myET);
                    ab.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String qtt = myET.getText().toString();

                            Boolean isInt = false;
                            for(int i=0; i<qtt.length();i++){
                                if(qtt.charAt(0) == '0'){
                                    break;
                                }else if(!Character.isDigit(qtt.charAt(i))){
                                    isInt = false;
                                    break;
                                }else{
                                    isInt = true;
                                }
                            }
                            if(isInt){

                                SQLiteDatabase db = myDB.getWritableDatabase();

                                ContentValues values = new ContentValues();
                                values.put("list", listName);
                                values.put("type", typeName);
                                values.put("item", itName);
                                values.put("quantity", qtt);
                                values.put("status","-");
                                db.insert("info",null,values);
                                values.clear();

                                Intent gotoList = new Intent();
                                Bundle bundle = new Bundle();
                                bundle.putString("listName",listName);
                                gotoList.putExtras(bundle);
                                gotoList.setClass(ItemActivity.this, ListInnerActivity.class);
                                startActivity(gotoList);
                            }else{
                                Toast.makeText(ItemActivity.this,"Input is not an Integer.",
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
                }else{
                    Intent gotoList = new Intent();
                    gotoList.setClass(ItemActivity.this, SaveToDBActivity.class);
                    startActivity(gotoList);
                }

            }
        });
    }
}