package index;

import java.util.Timer;
import java.util.TimerTask;

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
			}else {
				System.out.print("#");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}