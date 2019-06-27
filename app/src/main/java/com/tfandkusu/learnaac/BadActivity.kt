package com.tfandkusu.learnaac

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity;
import android.view.View
import androidx.appcompat.app.AlertDialog

import kotlinx.android.synthetic.main.activity_main.*

/**
 * 良くない実装
 */
class BadActivity : AppCompatActivity() {

    private val _handler = Handler()

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // カウント0を表示
        progressView.isIndeterminate = true
        fab.setOnClickListener {
            // プログレスを表示
            progressView.visibility = View.VISIBLE
            _handler.postDelayed(
                {
                    // 2秒後にカウントを1増やして表示する
                    count += 1
                    //プログレス非表示
                    progressView.visibility = View.INVISIBLE
                    // ダイアログ表示
                    showCountDialog()
                }, 3000
            )
        }
    }

    /**
     * ダイアログを表示する
     */
    private fun showCountDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_count))
        builder.setMessage(getString(R.string.message_count).format(count))
        builder.setPositiveButton(getString(R.string.ok)) { _, _ ->}
        builder.show()
    }
}
