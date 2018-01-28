import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EventListener implements MouseListener {

	GUI_View view;
	int first_X;
	int first_Y;
	int second_X;
	int second_Y;
	int exit = 50;
	int score = 0;
	String first_Icon;
	boolean one_Click = false;

	public EventListener(GUI_View gui_View) {
		this.view = gui_View;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		view.label_wait.setText("����� �ֽʽÿ�");

		if (one_Click == true) {
			System.out.println("one click true");
			for (int i = 0; i < view.charicter_Button.length; i++) {
				for (int j = 0; j < view.charicter_Button[i].length; j++) {
					if (e.getSource() == view.charicter_Button[i][j]) {
						view.charicter_Button[i][j].setVisible(true);
						System.out.println("button");
						second_X = i;
						second_Y = j;
						if (first_X == second_X && first_Y == second_Y) {
							// ���� �׸� �ι� Ŭ���ϸ� �ʱ⼼������ ���ư�
							one_Click = false;
							System.out.println("�����׸�");
							break;
						} // if end
						if (first_Icon.equals(String.valueOf(
								view.charicter_Button[i][j].getIcon()))) {
							// ó�� ��ư�� �̹������� �ι�° ��ư�� �̹��� ���� ���� ���
							System.out.println("�´� �׸��Դϴ�");
							view.charicter_Button[i][j].setVisible(false);
							view.charicter_Button[first_X][first_Y]
									.setVisible(false);
							exit--;
							// �ϳ� ���⶧ ���� 1�� ���� - �ʱⰪ 50
							score = score + Timer_Thread.t;
							// ���� ���� ��ŭ ������ ���ϱ� ���� �ð��� �����ش�
							view.label_Score.setText((String.valueOf(score) + "��"));
							if (exit == 0) {
								JOptionPane.showMessageDialog(null, "�����մϴ�! "
										+ Timer_Thread.clear_Time
										+ "�� ���� ����ϼ̽��ϴ�");
								System.out.println("����");
								System.exit(0);
							} // if end
						} else {
							view.charicter_Button[i][j].setBorderPainted(true);
						} // else if end
					} // mouse select end
				} // mouse select end
			} // for end
			one_Click = false;
			view.label_wait.setText("ó�� �׸��� ������ �ֽʽÿ�");
		} // if end
		else if (one_Click == false) {
			for (int i = 0; i < view.charicter_Button.length; i++) {
				for (int j = 0; j < view.charicter_Button[i].length; j++) {
					if (e.getSource() == view.charicter_Button[i][j]) {
						first_X = i;
						first_Y = j;
						first_Icon = String
								.valueOf(view.charicter_Button[i][j].getIcon());
						view.charicter_Button[i][j].setVisible(true);
					} // mouse select end
				}
			}
			System.out.println("ó�� Ŭ��");
			one_Click = true;
			view.label_wait.setText("���� �׸��� ������ �ֽʽÿ�");
		} // else if end
	}// mouse event end

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

}
