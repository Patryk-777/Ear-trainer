package pl.pwr.edu.s263646.ear_rainer;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.util.SparseArray;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AudioSoundPlayer {

    private SparseArray<PlayThread> threadMap = null;
    private Context context;
    static public List<Integer> pianoSoundList = new ArrayList<>();


    static {

        pianoSoundList.add(R.raw.c);
        pianoSoundList.add(R.raw.d);
        pianoSoundList.add(R.raw.e);
        pianoSoundList.add(R.raw.f);
        pianoSoundList.add(R.raw.g);
        pianoSoundList.add(R.raw.a);
        pianoSoundList.add(R.raw.b);
        pianoSoundList.add(R.raw.c_sharp);
        pianoSoundList.add(R.raw.d_sharp);
        pianoSoundList.add(R.raw.f_sharp);
        pianoSoundList.add(R.raw.g_sharp);
        pianoSoundList.add(R.raw.a_sharp);

    }

    public AudioSoundPlayer(Context context) {
        this.context = context;
        threadMap = new SparseArray<>();
    }

    public void playNote(int note) {
        if (!isNotePlaying(note)) {
            PlayThread thread = new PlayThread(note);
            thread.start();
            threadMap.put(note, thread);
        }
    }

    public void stopNote(int note) {
        PlayThread thread = threadMap.get(note);

        if (thread != null) {
            thread.mediaPlayerStop();
            threadMap.remove(note);

        }
    }

    public boolean isNotePlaying(int note) {
        return threadMap.get(note) != null;
    }

    public class PlayThread extends Thread {

        int note;
        private MediaPlayer mediaPlayer;

        public PlayThread(int note) {
            this.note = note;
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setVolume(0.7f, 0.7f);
        }

        @Override
        public void run() {

            mediaPlayer = MediaPlayer.create(context, pianoSoundList.get(note-1));
            mediaPlayer.start();

        }

        public void mediaPlayerStop()
        {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

    }
}
