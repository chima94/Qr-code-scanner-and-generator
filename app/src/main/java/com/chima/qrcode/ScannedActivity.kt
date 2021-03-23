package com.chima.qrcode

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.chima.qrcode.helper.EncryptionHelper
import com.chima.qrcode.model.UserObject
import com.google.gson.Gson
import java.lang.RuntimeException

class ScannedActivity : AppCompatActivity() {

    companion object {
        private const val SCANNED_STRING: String = "scanned_string"
        fun getScannedActivity(callingClassContext: Context, encryptedString: String): Intent {
            return Intent(callingClassContext, ScannedActivity::class.java)
                .putExtra(SCANNED_STRING, encryptedString)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanned)
        if(intent.getSerializableExtra(SCANNED_STRING) == null){
            throw RuntimeException("No encryption string found in intent")
        }
        val scanFullNameTextView = findViewById<TextView>(R.id.scannedFullNameTextView)
        val ageTextView = findViewById<TextView>(R.id.scannedAgeTextView)

        val decryptedString = EncryptionHelper.getInstance().getDecryptionString(intent.getStringExtra(
            SCANNED_STRING))
        val userObject = Gson().fromJson(decryptedString, UserObject::class.java)
        scanFullNameTextView.text = userObject.fullName
        ageTextView.text = userObject.age.toString()
    }
}