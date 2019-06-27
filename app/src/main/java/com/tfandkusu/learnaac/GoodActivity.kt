package com.tfandkusu.learnaac

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity;
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import kotlinx.android.synthetic.main.activity_main.*

/**
 * 良い実装
 */
class GoodActivity : AppCompatActivity() {

    private val _handler = Handler()

    private lateinit var viewModel: GoodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // ViewModelを作成
        viewModel = ViewModelProviders.of(this).get(GoodViewModel::class.java)
        // プログレス更新
        viewModel.progress.observe(this, Observer { flag ->
            flag?.let {
                if (it)
                    progressView.visibility = View.VISIBLE
                else
                    progressView.visibility = View.INVISIBLE
            }
        })
        // ダイアログ表示
        viewModel.countLiveData.observe(this, Observer { count ->
            count?.let {
                if (!viewModel.showFlag)
                    showCountDialog(it)
            }
        })
        fab.setOnClickListener {
            // プログレスを表示
            viewModel.progress.value = true
            _handler.postDelayed(
                {
                    // 3秒後にカウントを1増やして表示する
                    viewModel.count += 1
                    viewModel.showFlag = false
                    viewModel.countLiveData.value = viewModel.count
                    viewModel.progress.value = false
                }, 3000
            )
        }
        progressView.isIndeterminate = true
    }

    /**
     * ダイアログを表示する
     */
    private fun showCountDialog(count: Int) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.title_count))
        builder.setMessage(getString(R.string.message_count).format(count))
        builder.setPositiveButton(getString(R.string.ok)) { _, _ -> }
        val dialog = builder.create()
        dialog.setOnDismissListener {
            // 一度閉じたダイアログを２回表示しない
            viewModel.showFlag = true
        }
        dialog.show()
    }
}
