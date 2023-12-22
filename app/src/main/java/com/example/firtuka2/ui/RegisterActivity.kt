package com.example.firtuka2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.firtuka2.R
import com.example.firtuka2.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}