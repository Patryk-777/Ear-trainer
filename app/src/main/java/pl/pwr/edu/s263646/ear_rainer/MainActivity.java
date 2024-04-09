package pl.pwr.edu.s263646.ear_rainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button settingsBtn, trainingBtn, pianoBtn;
    Intent trainingIntent, pianoIntent, settingsIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settingsBtn=findViewById(R.id.settingsBtn);
        pianoBtn=findViewById(R.id.pianoBtn);
        trainingBtn=findViewById(R.id.trainingBtn);


        trainingIntent=new Intent(this, TrainingActivity.class);
        pianoIntent=new Intent(this, PianoActivity.class);
        settingsIntent=new Intent(this, SettingsActivity.class);

        trainingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(trainingIntent);
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(settingsIntent);
            }
        });

        pianoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(pianoIntent);
            }
        });


    }
}