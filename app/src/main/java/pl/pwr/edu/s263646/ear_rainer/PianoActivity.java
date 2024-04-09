package pl.pwr.edu.s263646.ear_rainer;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
public class PianoActivity extends AppCompatActivity {

    private PianoView view;
    private PianoControler controler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        view=findViewById(R.id.pianoView);
        controler=new PianoControler(this, view);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return controler.onTouchEvent(event);
            }
        });
    }
}

