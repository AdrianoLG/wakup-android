package com.yellowpineapple.wakup;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.yellowpineapple.wakup.sdk.Wakup;
import com.yellowpineapple.wakup.sdk.WakupOptions;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Wakup
        Wakup.instance(this).launch(
                    new WakupOptions("277eb434-d35f-4684-b05e-735780bcca49").
                            country("ES").
                            defaultLocation(41.38506, 2.17340)
            );
    }
}
