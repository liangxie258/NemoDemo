package com.nemo.demo.file_system;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.nemo.demo.R;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileOperationActivity extends AppCompatActivity {

    @BindView(R.id.content)
    TextView content;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_operation);
        ButterKnife.bind(this);
        StringBuilder stringBuilder = new StringBuilder();
        String VERSION = Build.VERSION.SDK_INT + "";
        stringBuilder.append("VERSION:    +" + VERSION);
        String externalCacheDir = getExternalCacheDir().getAbsolutePath();
        stringBuilder.append("\n\nexternalCacheDir:    +" + externalCacheDir);
        String externalFilesDir = getExternalFilesDir(null).getAbsolutePath();
        stringBuilder.append("\n\nexternalFilesDir:    +" + externalFilesDir);
        String externalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
        stringBuilder.append("\n\nexternalStorageDirectory:    +" + externalStorageDirectory);
        String rootDirectory = Environment.getRootDirectory().getAbsolutePath();
        stringBuilder.append("\n\nrootDirectory:    +" + rootDirectory);
        String dataDirectory = Environment.getDataDirectory().getAbsolutePath();
        stringBuilder.append("\n\ndataDirectory:    +" + dataDirectory);
        String downloadCacheDirectory = Environment.getDownloadCacheDirectory().getAbsolutePath();
        stringBuilder.append("\n\ndownloadCacheDirectory:    +" + downloadCacheDirectory);
        String DIRECTORY_PODCASTS = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PODCASTS).getAbsolutePath();
        stringBuilder.append("\n\nDIRECTORY_PODCASTS:    +" + DIRECTORY_PODCASTS);
        content.setText(stringBuilder);
    }

    //写入文档
    @OnClick({R.id.btn1, R.id.btn2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                String externalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(externalStorageDirectory, "ceshi.txt");

                try {
                    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                    bufferedWriter.write("hello\n");
                    bufferedWriter.write("world");
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn2:
                externalStorageDirectory = Environment.getExternalStorageDirectory().getAbsolutePath();
                String path = externalStorageDirectory + "/Android/data/com.jd.app.reader/files/data/1039151964";
                file = new File(path);
                content.setText(path);
                if (file.exists()) {
                    boolean delete = file.delete();
                    if (delete) {
                        Toast.makeText(this, "文件已删除", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "删除失败", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(this, "文件不存在", Toast.LENGTH_LONG).show();
                }
                break;
        }

    }


}
