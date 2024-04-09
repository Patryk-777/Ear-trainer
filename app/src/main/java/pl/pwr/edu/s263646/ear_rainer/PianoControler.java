package pl.pwr.edu.s263646.ear_rainer;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PianoControler  {
    private AudioSoundPlayer soundPlayer;
    PianoView pianoView;
    PianoControler(Context context, PianoView view)
    {
        soundPlayer = new AudioSoundPlayer(context);
        pianoView=view;
    }


    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        boolean isDownAction = action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE;

        for (int touchIndex = 0; touchIndex < event.getPointerCount(); touchIndex++) {
            float x = event.getX(touchIndex);
            float y = event.getY(touchIndex);

            Key k = keyForCoords(x,y);

            if (k != null) {
                k.down = isDownAction;
            }
        }

        ArrayList<Key> tmp = new ArrayList<>(pianoView.getWhites());
        tmp.addAll(pianoView.getBlacks());

        for (Key k : tmp) {
            if (k.down) {
                if (!soundPlayer.isNotePlaying(k.sound)) {
                    soundPlayer.playNote(k.sound);
                    pianoView.postInvalidate();
                } else {
                    releaseKey(k);
                }
            } else {
                soundPlayer.stopNote(k.sound);
                releaseKey(k);
            }
        }

        return true;
    }


    private Key keyForCoords(float x, float y) {
        for (Key k : pianoView.getBlacks()) {
            if (k.rect.contains(x,y)) {
                return k;
            }
        }

        for (Key k : pianoView.getWhites()) {
            if (k.rect.contains(x,y)) {
                return k;
            }
        }

        return null;
    }

    private void releaseKey(final Key k) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                k.down = false;
                handler.sendEmptyMessage(0);
            }
        }, 100);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            pianoView.postInvalidate();
        }
    };

}
