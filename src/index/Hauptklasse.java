package index;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

public class Hauptklasse extends TimerTask {
	public static void main(String[] args) {
		var timer = new Timer();
		timer.schedule(new Hauptklasse(), 0, 30000);
	}

	@Override
	public void run() {
		Test:
		try {
			String s = http.req.getImpfPortal();

			if (s.indexOf("outOfStock\":true") == -1) {
				System.out.println(s);
				makeANoise();
				break Test;
			}
			s = http.req.getImpfFrauen();
			if (s.indexOf("availabilities\":[],") == -1) {
				System.out.println(s);
				makeANoise();
				break Test;
			}

			else {
				System.out.print("#");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void makeANoise() throws LineUnavailableException   {
		byte[] buf = new byte[1];
		var af = new AudioFormat((float) 44100, 8, 1, true, false);
		var sdl = AudioSystem.getSourceDataLine(af);
		sdl.open();
		sdl.start();
		for (int i = 0; i < 1000 * (float) 44100 / 1000; i++) {
			double angle = i / ((float) 44100 / 440) * 2.0 * Math.PI;
			buf[0] = (byte) (Math.sin(angle) * 100);
			sdl.write(buf, 0, 1);
		}
		sdl.drain();
		sdl.stop();
	}
}