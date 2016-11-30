package com.example.administrator.myrecord.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.myrecord.R;
import com.example.administrator.myrecord.entity.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by zzz on 2016/11/28.
 */

public class Adapter extends BaseAdapter {
    ArrayList<Record> arrayList;
    Context context;
    LayoutInflater inflater;
    public  Adapter(Context context,ArrayList<Record> records){
        inflater= LayoutInflater.from(context);
        this.context=context;
        arrayList=records;

    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
       if (convertView==null)
       {
           convertView=inflater.inflate(R.layout.item_record,parent,false);
           holder=new Holder();
           holder.textView= (TextView) convertView.findViewById(R.id.record);
           convertView.setTag(holder);

       }else{
           holder= (Holder) convertView.getTag();
       }
        holder.textView.setText(arrayList.get(position).getName());
        return  convertView;
    }
    class Holder{
        TextView textView;
//        Boolean swit=false;
//        public void click(final Record record)
//        {
//            textView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    MediaPlayer player=new MediaPlayer();
//                    try {
//                        player.setDataSource(record.getPath());
//                        if (swit=false){
//
//                        player.prepare();
//                        player.start();
//                        swit=true;}
//                        else {
//                            player.stop();
//                            swit=false;
//                        }
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            });
//        }
    }
}
