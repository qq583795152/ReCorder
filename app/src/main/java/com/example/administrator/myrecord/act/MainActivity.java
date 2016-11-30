package com.example.administrator.myrecord.act;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ExpandedMenuView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.myrecord.R;
import com.example.administrator.myrecord.adapter.Adapter;
import com.example.administrator.myrecord.entity.Record;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
     Button control;
    Button close;
    ListView listView;
    ArrayList<Record> records;
    MediaRecorder recorder;
    MediaPlayer play;
    Adapter adapter;
    Boolean pla=false;

    final String dirname="myrecord";
    //语音文件保存路径
    private String filename = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        control= (Button) findViewById(R.id.control);
        close= (Button) findViewById(R.id.close);
        listView= (ListView) findViewById(R.id.list);
        records=new ArrayList<>();
        File f=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+dirname);
        Log.d("filenumber",Integer.toString(f.listFiles().length));
      //  Log.d("filenumber",Integer.toString((int)f.length()));
       // recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        File[] files=f.listFiles();
        for (File file:files)
        {
            records.add(new Record(file.getName(),file.getAbsolutePath()));
            Log.d("name+path",file.getName()+"     "+file.getAbsolutePath());
        }
         adapter=new Adapter(this,records);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (play==null)
                {
                    play=new MediaPlayer();
                    try {

                        play.setDataSource(records.get(position).getPath());
                        play.prepare();
                        play.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
             else {

                    play.release();
                    play=null;
                }

            }
        });
       control.setOnClickListener(this);
        close.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.control&&!pla)
        {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {
                    control.setText("录音中");
                    File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+dirname);
                if (!file.exists())
                {
                    file.mkdirs();
                }
                    int a=file.listFiles().length;
                    File[] fil=file.listFiles();
                    ArrayList<Integer> listname=new ArrayList();
                    for (File file1:fil)
                    {
                        String name=file1.getName();
                        int b=Integer.parseInt(name.substring(0,name.indexOf(".")));
                        listname.add(b);
                    }
                Collections.sort(listname);
        if (listname.size()==0)
        {
            filename=file.getAbsolutePath()+"/"+"1"+".3gp";
            records.add(new Record("1"+".3gp",filename));
        }
        else
        {
                    filename = file.getAbsolutePath() + "/" + Integer.toString(listname.get(listname.size() - 1) + 1) + ".3gp";
                    records.add(new Record(Integer.toString(listname.get(listname.size() - 1) + 1) + ".3gp",filename));
}
                    recorder=new MediaRecorder();
                    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recorder.setOutputFile(filename);
                    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

                try {
                    recorder.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                recorder.start();




            }
            pla=false;
        }
        if (v.getId()==R.id.close)
        {
            if (recorder!=null)
            {control.setText("开始录音");
                recorder.stop();
                recorder.release();
                //数据更新
                adapter.notifyDataSetChanged();
                recorder=null;
            pla=false;}
        }

    }
}
