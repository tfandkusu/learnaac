package com.tfandkusu.learnaac

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choose.*

class ChooseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose)

        badView.setOnClickListener {
            val intent = Intent(this@ChooseActivity, BadActivity::class.java)
            startActivity(intent)
        }
        goodView.setOnClickListener {
            val intent = Intent(this@ChooseActivity, GoodActivity::class.java)
            startActivity(intent)
        }
    }
}
