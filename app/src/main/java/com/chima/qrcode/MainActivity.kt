package com.chima.qrcode

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.google.zxing.BarcodeFormat
import me.dm7.barcodescanner.zxing.ZXingScannerView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generateButton = findViewById<Button>(R.id.firstActivityGenerateButton)
        val scanButton = findViewById<Button>(R.id.firstActivityScanButton)

        generateButton.setOnClickListener{
            startActivity(Intent(this, GenerateQrCodeActivity::class.java))
        }

        scanButton.setOnClickListener {
            startActivity(Intent(this, ScanQrCodeActivity::class.java))
        }
    }


}