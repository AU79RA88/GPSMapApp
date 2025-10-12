package com.example.gpsmapapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity {

    private ImageView imgView;
    private static final String IMG_URL = "https://i.imgur.com/LMao7Ol.jpeg";
    private float rotationAngle = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imgView = findViewById(R.id.mImageView);
        Button btnDescargar = findViewById(R.id.btnDescargar);
        Button btnRotar = findViewById(R.id.btnRotar);
        Button btnVolver = findViewById(R.id.btnBackMain);

        btnDescargar.setOnClickListener(v -> new Thread(() -> {
            final Bitmap bitmap = descargarImagen(IMG_URL);
            if (bitmap != null) {
                runOnUiThread(() -> imgView.setImageBitmap(bitmap));
            }
        }).start());

        btnRotar.setOnClickListener(v -> {
            rotationAngle += 90f;
            if (rotationAngle >= 360f) rotationAngle = 0f;
            imgView.setRotation(rotationAngle);
        });

        btnVolver.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private Bitmap descargarImagen(String urlStr) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        InputStream input = null;
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            input = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (input != null) input.close();
                if (connection != null) connection.disconnect();
            } catch (Exception ignored) {}
        }
        return bitmap;
    }
}
