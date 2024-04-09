package pl.pwr.edu.s263646.ear_rainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TrainingActivity extends AppCompatActivity {

    boolean layout;
    private Button[] buttons= new Button[13];
    private Button startBtn, stopBtn;
    TrainingControler trainingControler;
    TrainingModel trainingModel;
    SharedPreferences sharedPreferences;
    Intent mainIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE);

        buttons[0]=findViewById(R.id.minSecBtn);
        buttons[1]=findViewById(R.id.majSecBtn);
        buttons[2]=findViewById(R.id.minThirdBtn);
        buttons[3]=findViewById(R.id.majThirdBtn);
        buttons[4]=findViewById(R.id.fourthBtn);
        buttons[5]=findViewById(R.id.dimFifthBtn);
        buttons[6]=findViewById(R.id.fifthBtn);
        buttons[7]=findViewById(R.id.minSixthBtn);
        buttons[8]=findViewById(R.id.majSixthBtn);
        buttons[9]=findViewById(R.id.minSevBtn);
        buttons[10]=findViewById(R.id.majSevBtn);
        buttons[11]=findViewById(R.id.octBtn);
        buttons[12]=findViewById(R.id.nextBtn);


        startBtn=findViewById(R.id.startBtn);
        stopBtn=findViewById(R.id.stopBtn);

        mainIntent=new Intent(this, MainActivity.class);

        trainingModel=new TrainingModel(sharedPreferences);
        trainingControler=new TrainingControler(buttons, trainingModel, this);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainingControler.start();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toastMessage="Twój wynik to: "+ trainingModel.getGoodAnswers()+"/"+trainingModel.getTotalAnswers();
                Toast toast = Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_LONG);
                toast.show();
                startActivity(mainIntent);

            }
        });



    }
}