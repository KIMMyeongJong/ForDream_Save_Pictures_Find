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

		view.label_wait.setText("대기해 주십시오");

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
							// 같은 그림 두번 클릭하면 초기세팅으로 돌아감
							one_Click = false;
							System.out.println("같은그림");
							break;
						} // if end
						if (first_Icon.equals(String.valueOf(
								view.charicter_Button[i][j].getIcon()))) {
							// 처음 버튼의 이미지값과 두번째 버튼의 이미지 값이 같을 경우
							System.out.println("맞는 그림입니다");
							view.charicter_Button[i][j].setVisible(false);
							view.charicter_Button[first_X][first_Y]
									.setVisible(false);
							exit--;
							// 하나 맞출때 마다 1씩 감소 - 초기값 50
							score = score + Timer_Thread.t;
							// 빨리 맞춘 만큼 점수를 더하기 위해 시간을 더해준다
							view.label_Score.setText((String.valueOf(score) + "점"));
							if (exit == 0) {
								JOptionPane.showMessageDialog(null, "축하합니다! "
										+ Timer_Thread.clear_Time
										+ "초 만에 통과하셨습니다");
								System.out.println("종료");
								System.exit(0);
							} // if end
						} else {
							view.charicter_Button[i][j].setBorderPainted(true);
						} // else if end
					} // mouse select end
				} // mouse select end
			} // for end
			one_Click = false;
			view.label_wait.setText("처음 그림을 선택해 주십시오");
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
			System.out.println("처음 클릭");
			one_Click = true;
			view.label_wait.setText("다음 그림을 선택해 주십시오");
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
