import java.awt.Color;
import java.util.Random;

import javax.swing.JOptionPane;

public class Timer_Thread extends Thread {

	GUI_View view;
	static int t = 100;
	static int clear_Time = 0;
	Color color;
	Random rand = new Random();

	public Timer_Thread(GUI_View gui_View) {
		this.view = gui_View;
	}

	@Override
	public void run() {

		for (int i = 0; i <= 250; i++) {
			try {
				view.progressBar.setValue(t);
				// t를 progressBar의 maximum값과 동일하게 준 후 1초마다 설정해 준다
				Thread.sleep(1000);
				clear_Time++;
				// 1초동안 스레드를 재운다
				if (t <= 10) {
					view.progressBar.setForeground(Color.red);
					// 시간이 10초 남으면 프로그레스 바를 빨간색으로 설정
				} else {
					view.progressBar.setForeground(color = new Color(rand.nextInt(256),
							rand.nextInt(256), rand.nextInt(256)));
				}
				t = t - rand.nextInt(2);
				// 랜덤으로 1초가 감소할 수도 있고 감소하지 않을 수도 있다
				if (t == 0) {
					JOptionPane.showMessageDialog(null, "시간 내에 통과하지 못했습니다.");
					// 알림창 출력
					System.out.println("종료");
					System.exit(0);
					// gui 종료
				}
			} catch (Exception e) {
			}
		}
		super.run();
	}
}
