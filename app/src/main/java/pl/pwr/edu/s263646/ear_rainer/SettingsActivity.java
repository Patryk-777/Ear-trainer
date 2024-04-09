package pl.pwr.edu.s263646.ear_rainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SeekBar speedBar;
    private TextView speedText;
    private int speedValue=0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Spinner upDownSpinner;
    ArrayAdapter<CharSequence> adapter;
    IntervalDirection direction;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        speedBar=findViewById(R.id.speedBar);
        speedText=findViewById(R.id.speedValue);
        upDownSpinner=findViewById(R.id.up_down);
        adapter=ArrayAdapter.createFromResource(this, R.array.playing_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        upDownSpinner.setAdapter(adapter);
        upDownSpinner.setOnItemSelectedListener(this);
        direction=IntervalDirection.UP;


        sharedPreferences= getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        speedValue = sharedPreferences.getInt("speed", 0);
        speedBar.setProgress(speedValue);
        speedText.setText(""+speedValue);
        upDownSpinner.setSelection(sharedPreferences.getInt("spinner_selection", 0));


        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                speedValue=progress;
                editor.putInt("speed", speedValue);
                editor.apply();
                speedText.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position==0)
            direction=IntervalDirection.UP;
        if(position==1)
            direction=IntervalDirection.DOWN;
        editor.putInt("spinner_selection", position);
        editor.putString("direction", direction.toString());
        editor.apply();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}