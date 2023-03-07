package com.furkansahan.soundrecorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewRecordingListener {
    private final List<RecordingsList> recordingsLists=new ArrayList<>();
    private RecyclerView recordingRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recordingRecyclerView = findViewById(R.id.recordingsRecyclerView);
        final FloatingActionButton newRecording=findViewById(R.id.newRecording);
        recordingRecyclerView.setHasFixedSize(true);
        recordingRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ||  ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},20 );


        }
        else{
            getRecordings(recordingRecyclerView);
        }
        newRecording.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                RecordingDialog recordingDialog =new RecordingDialog(MainActivity.this,MainActivity.this);
                recordingDialog.setCancelable(false);
                recordingDialog.show();

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
            getRecordings(recordingRecyclerView);
        }
        else{
            Toast.makeText(this,"Please give memory permissions", Toast.LENGTH_SHORT).show();
        }
    }

    private void getRecordings(RecyclerView recyclerView){
        File file= new File (Environment.getExternalStorageDirectory().getAbsolutePath()+"/SoundRecorder");


        if (file.exists()){

            File[] recordings= file.listFiles();

            for (File recording : recordings) {

                final String getFileName = recording.getName();

                RecordingsList recordingsList = new RecordingsList(getFileName, "0", recording.getAbsolutePath());
                recordingsLists.add(recordingsList);


            }
            recyclerView.setAdapter(new RecordingsAdapter(MainActivity.this, recordingsLists));

        }
        else{
            Toast.makeText(this, "No Recording Available", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNewRecord() {
        recordingsLists.clear();
        getRecordings(recordingRecyclerView);
    }
}