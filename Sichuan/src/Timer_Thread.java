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
				// t�� progressBar�� maximum���� �����ϰ� �� �� 1�ʸ��� ������ �ش�
				Thread.sleep(1000);
				clear_Time++;
				// 1�ʵ��� �����带 ����
				if (t <= 10) {
					view.progressBar.setForeground(Color.red);
					// �ð��� 10�� ������ ���α׷��� �ٸ� ���������� ����
				} else {
					view.progressBar.setForeground(color = new Color(rand.nextInt(256),
							rand.nextInt(256), rand.nextInt(256)));
				}
				t = t - rand.nextInt(2);
				// �������� 1�ʰ� ������ ���� �ְ� �������� ���� ���� �ִ�
				if (t == 0) {
					JOptionPane.showMessageDialog(null, "�ð� ���� ������� ���߽��ϴ�.");
					// �˸�â ���
					System.out.println("����");
					System.exit(0);
					// gui ����
				}
			} catch (Exception e) {
			}
		}
		super.run();
	}
}
