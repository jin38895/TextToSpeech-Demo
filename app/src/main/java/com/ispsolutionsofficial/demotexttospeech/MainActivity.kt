package com.ispsolutionsofficial.demotexttospeech

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import com.ispsolutionsofficial.demotexttospeech.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var binding: ActivityMainBinding? = null
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        tts = TextToSpeech(this, this)

        binding?.btnSpeak?.setOnClickListener {
            speakOut(binding?.etInputText?.text.toString())
        }
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_NOT_SUPPORTED || result == TextToSpeech.LANG_MISSING_DATA) {
                Log.e("TTS", "Language is not supported!")
            }
        } else {
            Log.e("TTS", "Initialization Failed!")
        }
    }

    private fun speakOut(text: String) {
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        super.onDestroy()

        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        binding = null
        tts = null
    }
}