package pl.pwr.edu.s263646.ear_rainer;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.Button;


import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;


public class TrainingControler extends Thread {


    TrainingModel trainingModel;
    private MediaPlayer mediaPlayer;
    Button[] buttons = new Button[12];
    Button nextBtn;
    private Context context;
    private int interval;
    boolean clicked = false;

    TrainingControler(Button[] buttons, TrainingModel trainingModel, Context context) {
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setVolume(0.7f, 0.7f);
        for (int i = 0; i < 12; ++i) {
            this.buttons[i] = buttons[i];
        }

        nextBtn=buttons[12];
        this.trainingModel = trainingModel;
        this.context = context;
    }


    public void run() {

        game();

        while (true) {
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game();

                }
            });

        }
    }

    private void game()
    {
        boolean upDown=true;
        interval = drawInterval();
        int randSound=drawSoundIdx();
        clicked = false;
        resetButtons();
        if(trainingModel.getDirection()==IntervalDirection.DOWN)
            upDown=false;
        playInterval(randSound,interval, upDown);
        visualizeAnswers(randSound, upDown);
    }

    private static int drawInterval() {

        Random random = new Random();
        int randomNumber = random.nextInt(11)+1;

        return randomNumber;
    }

    private int drawSoundIdx()
    {
        Random random = new Random();
        int randomSoundIndex = random.nextInt(trainingModel.pianoSoundList.size()-12);
        return randomSoundIndex;
    }
    private void playInterval(int idx1, int idx2, boolean upDown)
    {
        AtomicInteger secondSoundIndex= new AtomicInteger();
        if(upDown==false)
            idx1+=12;
        mediaPlayer = MediaPlayer.create(context, trainingModel.pianoSoundList.get(idx1));
        mediaPlayer.start();

        int finalIdx = idx1;
        Thread secondSoundThread = new Thread(() -> {
            try {
                Thread.sleep((long) (trainingModel.getTempo()*1000)); // Wait for 2 seconds
                if(upDown==true)
                    secondSoundIndex.set(finalIdx + idx2);
                else
                    secondSoundIndex.set(finalIdx - idx2);
                if (secondSoundIndex.get() < trainingModel.pianoSoundList.size()) {
                    mediaPlayer = MediaPlayer.create(context, trainingModel.pianoSoundList.get(secondSoundIndex.get()));
                    mediaPlayer.start();
                    Thread.sleep((long) (trainingModel.getTempo()*1000));
                    mediaPlayer.stop();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        secondSoundThread.start();
    }

    private void colorButtons(int randomNumber) {

        for (Button button : buttons) {
            button.setBackgroundColor(Color.RED);
        }
        buttons[randomNumber-1].setBackgroundColor(Color.GREEN);

    }

    public void resetButtons() {
        for (Button button : buttons) {
            button.setEnabled(true);
            button.setBackgroundColor(Color.rgb(103, 58, 183));
        }
    }

    private void visualizeAnswers(int randSound, boolean upDown){
        for (int i = 0; i < 12; i++) {
            int finalI = i+1;

            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(clicked) {
                            playInterval(randSound, finalI, upDown);
                    }
                    else{
                        colorButtons(interval);
                        if(finalI==interval)
                        {
                            trainingModel.incrementAllAnswers();
                            trainingModel.invrementGoodAnswers();
                        }
                        else
                        {
                            trainingModel.incrementAllAnswers();
                        }
                    }
                    clicked = true;

                }
            });
        }
    }

}

