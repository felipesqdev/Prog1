package com.example.felipe.prog1;

import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by FelipeSQ on 16/09/2014.
 */

public class SpeakWords extends MainActivity implements TextToSpeech.OnInitListener {
    private static final String TAG = SpeakWords.class.getSimpleName();

    //TTS object
    private static TextToSpeech myTTS;

    //status check code
    private static int MY_DATA_CHECK_CODE = 123; /* 0 */

    //activity pointer
    private static Activity MyActivity;

    // Constructor
    public SpeakWords (Activity act) {
        Log.d(TAG, "CONSTRUCTOR");

        MyActivity = act;

        // Passo 1 - Verifica se o Engine TTS estÃ¡ instalado
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        MyActivity.startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
    }

    public void Destroy() {
        Log.d(TAG, "Destroy");

        // DestrÃ³i o TTS
        myTTS.shutdown();
    }

    //act on result of TTS data check
    public void ActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "ActivityResult");

        if (requestCode == MY_DATA_CHECK_CODE) {
            Log.d(TAG, "ActivityResult - MY_CHECK_DATA_CODE");

            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                Log.d(TAG, "ActivityResult - CHECK_VOICE_DATA_PASS");

                // Passo 3 - Cria o objeto TTS...
                // Obs: o passo 2 pode nÃ£o ter sido necessÃ¡rio...
                myTTS = new TextToSpeech(MyActivity.getApplicationContext(), this);
            } else {
                Log.d(TAG, "ActivityResult - NOT CHECK_VOICE_DATA_PASS");

                // Passo 2 - TTS nÃ£o estÃ¡ instalado, entÃ£o instala...
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                MyActivity.startActivity(installTTSIntent);
            }
        }
    }

    //setup TTS
    public void onInit(int initStatus) {
        Log.d(TAG, "onInit");

        //if (TTS_initialized) {
        //    return;
        //}

        //check for successful instantiation
        if (initStatus == TextToSpeech.SUCCESS) {
            Log.d(TAG, "onInit - SUCCESS");

            // Passo 4a - O objeto TTS foi inicializado sem problemas...
            myTTS.setLanguage(Locale.getDefault());

            // if (myTTS.isLanguageAvailable(Locale.US) == TextToSpeech.LANG_AVAILABLE)
            //    myTTS.setLanguage(Locale.US);
        }
        else if (initStatus == TextToSpeech.ERROR) {
            Log.d(TAG, "onInit - ERROR");

            // Passo 4b - Deu erro na inicializaÃ§Ã£o do TTS!
            Context cont = MyActivity.getApplicationContext();
            Toast.makeText(cont, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
        }
    }

    //speak the user text
    public static void DoSpeak(String speech) {
        Log.d(TAG, "doSpeak - " + speech);

        //speak straight away
        myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
    }
}
