package edu.qc.seclass.glm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<String> myData;
    private Context ct;

    public MyAdapter(ArrayList<String> data, Context context){
        this.myData = data;
        this.ct=context;
    }

    @Override
    public int getCount() {
        return myData.size();
    }

    @Override
    public Object getItem(int position) {
        return myData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(ct).inflate(R.layout.type_and_item, parent, false);
        }
        TextView tv = convertView.findViewById(R.id.typeAndItemID);
        tv.setText(myData.get(position));
        return convertView;
    }
}
