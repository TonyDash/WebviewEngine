package com.cjy.usercenter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.cjy.commonlibrary.eventbus.LoginEvent
import org.greenrobot.eventbus.EventBus

class LoginActivity : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editPass: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editName = findViewById(R.id.editName)
        editPass = findViewById(R.id.editPass)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val name = editName.text.toString()
            val pass = editPass.text.toString()
            EventBus.getDefault().post(LoginEvent(name))
            this@LoginActivity.finish()
        }
    }
}