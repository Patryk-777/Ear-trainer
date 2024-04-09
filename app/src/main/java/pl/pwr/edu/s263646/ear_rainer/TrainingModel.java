package pl.pwr.edu.s263646.ear_rainer;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class TrainingModel {


    public List<Integer> pianoSoundList = new ArrayList<>();
    private double speed;
    private int goodAnswers;
    private int totalAnswers;
    SharedPreferences sharedPreferences;
    private IntervalDirection direction;





    TrainingModel(SharedPreferences sharedPreferences)
    {
        addSounds();
        this.sharedPreferences=sharedPreferences;
        speed=sharedPreferences.getInt("speed", -1);
        direction=IntervalDirection.valueOf(sharedPreferences.getString("direction", "UP"));

    }

    private void addSounds()
    {
        pianoSoundList.add(R.raw.c_low);
        pianoSoundList.add(R.raw.c_sharp_low);
        pianoSoundList.add(R.raw.d_low);
        pianoSoundList.add(R.raw.d_sharp_low);
        pianoSoundList.add(R.raw.e_low);
        pianoSoundList.add(R.raw.f_low);
        pianoSoundList.add(R.raw.f_sharp_low);
        pianoSoundList.add(R.raw.g_low);
        pianoSoundList.add(R.raw.g_sharp_low);
        pianoSoundList.add(R.raw.a_low);
        pianoSoundList.add(R.raw.a_sharp_low);
        pianoSoundList.add(R.raw.b_low);
        pianoSoundList.add(R.raw.c);
        pianoSoundList.add(R.raw.c_sharp);
        pianoSoundList.add(R.raw.d);
        pianoSoundList.add(R.raw.d_sharp);
        pianoSoundList.add(R.raw.e);
        pianoSoundList.add(R.raw.f);
        pianoSoundList.add(R.raw.f_sharp);
        pianoSoundList.add(R.raw.g);
        pianoSoundList.add(R.raw.g_sharp);
        pianoSoundList.add(R.raw.a);
        pianoSoundList.add(R.raw.a_sharp);
        pianoSoundList.add(R.raw.b);
        pianoSoundList.add(R.raw.c_high);
        pianoSoundList.add(R.raw.c_sharp_high);
        pianoSoundList.add(R.raw.d_high);
        pianoSoundList.add(R.raw.d_sharp_high);
        pianoSoundList.add(R.raw.e_high);
        pianoSoundList.add(R.raw.f_high);
        pianoSoundList.add(R.raw.f_sharp_high);
        pianoSoundList.add(R.raw.g_high);
        pianoSoundList.add(R.raw.g_sharp_high);
        pianoSoundList.add(R.raw.a_high);
        pianoSoundList.add(R.raw.a_sharp_high);
        pianoSoundList.add(R.raw.b_high);
    }

    public int getSoundResourceId(int index) {
        if (index >= 0 && index < pianoSoundList.size()) {
            return pianoSoundList.get(index);
        } else {
            // W przypadku błędnego indeksu, zwróć -1 lub inną wartość sygnalizującą błąd
            return -1;
        }
    }

    public double getTempo() {
        return 1.0/(speed/60.0);
    }
    public IntervalDirection getDirection()
    {
        return direction;
    }

    public void incrementAllAnswers()
    {
        totalAnswers++;
    }
    public void invrementGoodAnswers()
    {
        goodAnswers++;
    }

    public int getGoodAnswers(){
        return  goodAnswers;
    }
    public int getTotalAnswers() {
        return totalAnswers;
    }
}
