package com.example.special_subject.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.special_subject.ui.API.FeedRequest
import com.example.special_subject.ui.API.FeedResponse
import com.example.special_subject.ui.API.RetrofitInstance
import com.example.special_subject.databinding.FragmentDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var webView: WebView
    private lateinit var inputMessage: EditText
    private lateinit var sendButton: Button
    private lateinit var feedButton: Button
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
        feedButton = binding.feed
        outputTextView = binding.outputTextView
        scrollView = binding.scrollView2

        // 設定 WebView
        setupWebView()

        // 設定發送按鈕的點擊事件
        sendButton.setOnClickListener {
            sendMessage()
        }

        // 餵食按鈕點擊事件
        feedButton.setOnClickListener {
            triggerFeedApi()
        }

        return root
    }

    // twitch
    private fun setupWebView() {
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.allowFileAccess = false
        webSettings.allowContentAccess = false

        // 啟用縮放功能
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false // 隱藏縮放按鈕

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                return false // 允許 WebView 直接載入 URL
            }
        }
        webView.webChromeClient = WebChromeClient() // 啟用 WebChromeClient 以支援更多 Web 功能

        // Twitch 嵌入直播 URL
        val twitchUrl = "https://player.twitch.tv/?channel=jim9115&parent=localhost&controls=false"
        webView.loadUrl(twitchUrl)

        // 禁用 WebView 的用戶互動（僅觀看）
        webView.isClickable = false
        webView.isFocusable = false
        webView.setOnTouchListener { _, _ -> true } // 禁用觸摸事件
    }

    // youtube直播影像
//    private fun setupWebView() {
//        val webSettings: WebSettings = webView.settings
//        webSettings.javaScriptEnabled = true
//        webSettings.allowFileAccess = false
//        webSettings.allowContentAccess = false
//        webView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                return true
//            }
//        }
//
//        //  YouTube 行動版 URL
//        val videoId = "nlWfccFlU-w" //  YouTube 影片 ID
//        val videoUrl = "https://m.youtube.com/embed/nlWfccFlU-w?autoplay=1&modestbranding=1&showinfo=0&controls=0&rel=0" // 或者 "https://youtube.com/embed/$videoId"
//        webView.loadUrl(videoUrl)
//
//        // 禁用 WebView 的用戶互動（僅觀看）
//        webView.isClickable = false
//        webView.isFocusable = false
//        webView.setOnTouchListener { _, _ -> true } // 禁用觸摸事件
//
//
//    }

//    private fun setupWebView() {
//        val webSettings: WebSettings = webView.settings
//        webSettings.javaScriptEnabled = true
//        webSettings.allowFileAccess = false
//        webSettings.allowContentAccess = false
//
//        // 設置 WebViewClient 來處理 URL
//        webView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                return true
//            }
//        }
//
//        // 設置 WebChromeClient 來管理全螢幕模式
//        webView.webChromeClient = object : WebChromeClient() {
//            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
//                // 進入全螢幕模式
//                (webView.parent as ViewGroup).apply {
//                    removeView(webView)
//                    addView(view)
//                }
//                webView.visibility = View.GONE
//            }
//
//            override fun onHideCustomView() {
//                // 退出全螢幕模式
//                (webView.parent as ViewGroup).apply {
//                    removeViewAt(childCount - 1)
//                    addView(webView)
//                }
//                webView.visibility = View.VISIBLE
//            }
//        }
//
//        val videoId = "nlWfccFlU-w" // YouTube 影片 ID
//        val videoUrl = "https://youtube.com/embed/$videoId"
//
//        // 加載 YouTube 影片並執行 JavaScript 隱藏控制項
//        webView.loadUrl(videoUrl)
//        webView.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView?, url: String?) {
//                // 注入 JavaScript 隱藏控制項和其他元素
//                webView.evaluateJavascript("""
//                var player = document.getElementsByTagName('video')[0];
//                if (player) {
//                    player.controls = false; // 隱藏控制項
//                    player.autoplay = true; // 自動播放
//                }
//                document.querySelectorAll('.ytp-chrome-top, .ytp-title, .ytp-share-button').forEach(el => el.style.display = 'none'); // 隱藏標題和分享按鈕
//            """.trimIndent(), null)
//            }
//        }
//
//        // 禁用 WebView 的用戶互動（僅觀看）
//        webView.isClickable = false
//        webView.isFocusable = false
//        webView.setOnTouchListener { _, _ -> true } // 禁用觸摸事件
//    }



    private fun sendMessage() {
        val message = inputMessage.text.toString().trim()
        if (message.isNotEmpty()) {
            val currentText = outputTextView.text.toString()
            val newText = "$currentText\nYou: $message"
            outputTextView.text = newText
            inputMessage.text.clear()
            scrollView.post {
                scrollView.fullScroll(View.FOCUS_DOWN)
            }
        }
    }

    private fun triggerFeedApi() {
        val feedRequest = FeedRequest()

        RetrofitInstance.api.feed(feedRequest).enqueue(object : Callback<FeedResponse> {
            override fun onResponse(call: Call<FeedResponse>, response: Response<FeedResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.message ?: "Feeding in progress"
                    outputTextView.text = message
                } else {
                    outputTextView.text = "餵食請求失敗: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<FeedResponse>, t: Throwable) {
                outputTextView.text = "餵食請求失敗: ${t.message}"
                Toast.makeText(requireContext(), "網路錯誤，請重試", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
