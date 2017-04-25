package com.example.sondongyoung.demo2;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;

import android.graphics.Bitmap;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;

import android.os.BatteryManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Set;

import static android.R.attr.data;
import static com.example.sondongyoung.demo2.R.id.imageView;

public class MainActivity extends Activity {
    private static final int CAMERA_CAPTURE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Button captureButton = (Button) findViewById(R.id.capture);
        Button handleButton = (Button) findViewById(R.id.handle);

        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //  i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File("/sdcard/image.jpg")));

                startActivityForResult(i, CAMERA_CAPTURE);
            }
        });

        handleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap captureBMP = null;
                File file = new File("/sdcard/image.jpg");
                try {
                    captureBMP = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
/*
                int width = captureBMP.getWidth();
                int height = captureBMP.getHeight();
                Bitmap tmpBMP = captureBMP.copy(Bitmap.Config.ARGB_8888, true);
                for(int y = 0 ; y<height; y++)
                {
                    for(int x=0 ; x<width ; x++)
                    {
                        int value = captureBMP.getPixel(x,y);
                        if(value < 0xff808080)
                        {
                            tmpBMP.setPixel(x, y, 0xff000000);
                        }else
                        {
                            tmpBMP.setPixel(x, y, 0xffffffff);
                        }
                    }
                }
*/
                ImageView imgView = (ImageView) findViewById(imageView);
                imgView.setImageBitmap(captureBMP);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Bitmap captureBMP = null;

/*
        if (resultCode == RESULT_OK && requestCode == CAMERA_CAPTURE) {
            File file = new File("/sdcard/image.jpg");
            try {
                captureBMP = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(file));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        */
        ImageView imgView = (ImageView) findViewById(imageView);
        imgView.setImageBitmap(captureBMP);
    }
}

