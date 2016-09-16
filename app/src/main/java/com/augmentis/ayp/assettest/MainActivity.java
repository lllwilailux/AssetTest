package com.augmentis.ayp.assettest;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    private static final String FolderName = "HelloWorld";

    private static final String TAG = "MainActivity";

    InputStream inputStream = null;
    String str;
    String[] fileName;
    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.txt_view);

        AssetManager am = getAssets();
        try {
            fileName = am.list(FolderName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String assetPath = FolderName + File.separator + fileName[0];

        try {
            inputStream = am.open(assetPath);
            byte[] bytes = new byte[1024];
            while ((index = inputStream.read(bytes)) != -1) {
                str = new String(bytes);
                textView.setText(str);
                Log.d(TAG,"str : " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
