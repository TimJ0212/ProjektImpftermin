package index;

import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;

public class haupt extends TimerTask{
	public static void main (String[]args) {
		Timer timer = new Timer();
		timer.schedule(new haupt(), 0, 30000);
	}

	@Override
	public void run() {
		try {
			String s = http.req.get();
			if(s.indexOf("outOfStock\":false") >= 0){
				System.out.println("True");
				byte[] buf = new byte[ 1 ];;
			    AudioFormat af = new AudioFormat( (float )44100, 8, 1, true, false );
			    SourceDataLine sdl = AudioSystem.getSourceDataLine( af );
			    sdl.open();
			    sdl.start();
			    for( int i = 0; i < 1000 * (float )44100 / 1000; i++ ) {
			        double angle = i / ( (float )44100 / 440 ) * 2.0 * Math.PI;
			        buf[ 0 ] = (byte )( Math.sin( angle ) * 100 );
			        sdl.write( buf, 0, 1 );
			    }
			    sdl.drain();
			    sdl.stop();
			}else {
				System.out.print("#");
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}