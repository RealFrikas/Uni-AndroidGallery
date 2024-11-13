package com.example.ergasia1;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

public class MyTts {
    private final TextToSpeech textToSpeech;
    private TextToSpeech.OnInitListener initListener =
            new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    if (status==TextToSpeech.SUCCESS){
                        textToSpeech.setLanguage(Locale.ENGLISH);
                    }
                }
            };
    public MyTts(Context context){
        textToSpeech = new TextToSpeech(context,initListener);
    }
    public void speak(String message){
        textToSpeech.speak(message,TextToSpeech.QUEUE_ADD,null,null);
    }
}
