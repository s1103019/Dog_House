package com.example.special_subject.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.special_subject.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var webView: WebView
    private lateinit var inputMessage: EditText
    private lateinit var sendButton: Button
    private lateinit var outputTextView: TextView
    private lateinit var scrollView: ScrollView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 初始化元件
        webView = binding.webview
        inputMessage = binding.inputMessage
        sendButton = binding.sendButton
        outputTextView = binding.outputTextView
        scrollView = binding.scrollView2

        // 設定 WebView
        setupWebView()

        // 設定發送按鈕的點擊事件
        sendButton.setOnClickListener {
            sendMessage()
        }

        return root
    }

    // 設定 WebView 來顯示 YouTube 直播
    private fun setupWebView() {
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()

        // 加載 YouTube 直播的 URL
        val videoUrl = "https://www.youtube.com/embed/YOUR_VIDEO_ID" // 替換 YOUR_VIDEO_ID 為您要直播的影片 ID
        webView.loadUrl(videoUrl)
    }

    // 文字輸入框發送訊息
    private fun sendMessage() {
        val message = inputMessage.text.toString().trim()
        if (message.isNotEmpty()) {
            // 將新的訊息附加到 TextView
            val currentText = outputTextView.text.toString()
            val newText = "$currentText\nYou: $message"
            outputTextView.text = newText

            // 清空輸入框
            inputMessage.text.clear()

            // 讓 ScrollView 自動滾動到底部
            scrollView.post {
                scrollView.fullScroll(View.FOCUS_DOWN)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




