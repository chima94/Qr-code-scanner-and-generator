package com.chima.qrcode

import android.content.Context
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.chima.qrcode.helper.EncryptionHelper
import com.chima.qrcode.helper.QRCodeHelper
import com.chima.qrcode.model.UserObject
import com.google.gson.Gson
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

class GenerateQrCodeActivity : AppCompatActivity() {
    private lateinit var fullNameEditText : EditText
    private lateinit var ageEditText : EditText
    private lateinit var generateButton : Button
    private lateinit var qrImageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generate_qr_code)

        fullNameEditText = findViewById(R.id.fullNameEditText)
        ageEditText = findViewById(R.id.ageEditText)
        generateButton = findViewById(R.id.generateQrCodeButton)
        qrImageView = findViewById(R.id.qrCodeImageView)

        generateButton.setOnClickListener {
            if(checkEditText()){
                hideKeyBoard()
                val user = UserObject(fullName = fullNameEditText.text.toString(), age  = Integer.parseInt(ageEditText.text.toString()))
                val serializeString = Gson().toJson(user)
                val encryptionString = EncryptionHelper.getInstance().encryptionString(serializeString).encryptMsg()
                setImageBitmap(encryptionString)

            }
        }

    }


    private fun setImageBitmap(encryptedString : String?){
        val bitmap = QRCodeHelper.newInstance(this).setContent(encryptedString)
            .setErrorCorrectionLevel(ErrorCorrectionLevel.Q).setMargin(2).qrcOde
        qrImageView.setImageBitmap(bitmap)
    }



    private fun hideKeyBoard(){
        val view = this.currentFocus
        if(view != null){
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    private fun checkEditText() : Boolean{
        if(TextUtils.isEmpty(fullNameEditText.text.toString())){
            Toast.makeText(this, "full name cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }else if(TextUtils.isEmpty(ageEditText.text.toString())){
            Toast.makeText(this, "age field cannot be empty", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}