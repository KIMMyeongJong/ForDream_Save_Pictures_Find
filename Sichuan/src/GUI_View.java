import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class GUI_View extends JFrame {

	static final int MATRIX = 12;
	JFrame frame;
	JPanel panel_Top, panel_Center, panel_Bottom, panel_Game_Main, panel_Main;
	JButton[][] charicter_Button = new JButton[MATRIX][MATRIX];
	ArrayList<Integer> list = new ArrayList<Integer>();
	ImageIcon[] charicter_Image = new ImageIcon[100];
	int[] randArr = new int[100];
	int temp_Num = 0;
	Random rand = new Random();
	EventListener event = new EventListener(this);
	JProgressBar progressBar = new JProgressBar();
	Timer_Thread timer = new Timer_Thread(this);
	JLabel label_wait , label_Score;
	Font font;

	public GUI_View() {
		frame = new JFrame("SCHUAN");
		frame.setSize(700, 780);
		// frame.setLayout(new BorderLayout());
		// frame 크기 설정
		panel_Main = new JPanel();
		panel_Main.setLayout(new BorderLayout());
		panel_Top = new JPanel();
		// panel_Top.setSize(new Dimension(900, 50));
		// 최상위에 위치할 패널 크기 생성 및 크기 지정
		panel_Center = new JPanel();
		panel_Game_Main = new JPanel(new GridLayout(12, 12));
		// 중앙 사천성 게임이 시작 될 패널 생성 버튼 11개씩 배열 위해 gridlayout생성 후 행과 열 11로 지정
		panel_Bottom = new JPanel();
		// panel_Bottom.setSize(new Dimension(900, 50));
		// 하단 패널 생성 및 크기 지정
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(650, 50));
		//프로그래스바 설정 후 사이즈와 최대,최소값 설정
		label_wait = new JLabel("처음 그림을 선택해 주십시오");
		label_wait.setAlignmentX(Label.CENTER);;
		label_wait.setFont( new Font("돋움", 3, 15));
		label_wait.setPreferredSize(new Dimension(250, 50));
		
		label_Score = new JLabel(String.valueOf(event.score) + "점");
		label_Score.setHorizontalAlignment(10);
		label_Score.setPreferredSize(new Dimension(150, 50));
		//점수 표시를 위한 라벨 및 초기값 설정
		for (int i = 0; i < 7; i++) {
			list.add(i);
			list.add(i);
		} // list에 배열 인덱스를 가져올 i 삽입

		for (int i = 1; i < randArr.length; i++) {
			randArr[i] = rand.nextInt(100);
			for (int j = 0; j < i; j++) {
				if (randArr[j] == randArr[i]) {
					i--;
					break;
				} // if end
			}
		} // 이미지 배열에 접근할 랜덤 인덱스 생성

		for (int i = 0; i < charicter_Image.length; i = i + 2) {
			int temp = rand.nextInt(15);
			// 랜덤으로 숫자를 생성하고
			charicter_Image[randArr[i]] = new ImageIcon("img/" + temp + ".jpg");
			charicter_Image[randArr[i + 1]] = new ImageIcon("img/" + temp + ".jpg");
			// 그림맞추기는 이미지가 짝수 갯수만큼 있어야 하기 때문에 두개씩 생성
		} // for end

		for (int i = 0; i < charicter_Button.length; i++) {
			for (int j = 0; j < charicter_Button[i].length; j++) {
				if (i == 0 || j == 0 || i == charicter_Button.length - 1
						|| j == charicter_Button.length - 1) {
					// 테두리 부분 사진 제외
					charicter_Button[i][j] = new JButton();
					charicter_Button[i][j].setPreferredSize(new Dimension(50, 50));
					// 버튼 크기 설정
					charicter_Button[i][j].setContentAreaFilled(false);
					// 내용 볼록한거 추가안하기
					charicter_Button[i][j].setBorderPainted(false);
					// 테두리 보이기 X
					charicter_Button[i][j].addMouseListener(event);
					panel_Game_Main.add(charicter_Button[i][j]);
				} else {
					//사진이 들어가는 영역
					charicter_Button[i][j] = new JButton(charicter_Image[temp_Num]);
					charicter_Button[i][j].setPreferredSize(new Dimension(50, 50));
					// 버튼 크기 설정
					charicter_Button[i][j].setContentAreaFilled(false);
					// 내용 볼록한거 추가안하기
					charicter_Button[i][j].addMouseListener(event);
					charicter_Button[i][j].setBorderPainted(false);
					panel_Game_Main.add(charicter_Button[i][j]);
					temp_Num++;
				} // if else end
			} // for end
		}
		
		panel_Center.add(panel_Game_Main);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		panel_Game_Main.setBackground(Color.pink);
		panel_Top.setBackground(Color.white);
		panel_Center.setBackground(Color.pink);
		panel_Bottom.setBackground(Color.pink);
		//////////////////////////////////////패널들 배경색 설정///////////////////////////////////////////////////////
		panel_Top.add(label_wait);
		panel_Top.add(label_Score);
		panel_Bottom.add(progressBar);
		
		panel_Main.add(panel_Top, BorderLayout.NORTH);
		panel_Main.add(panel_Center, BorderLayout.CENTER);
		panel_Main.add(panel_Bottom, BorderLayout.SOUTH);
		//패널들 borderLayout 설정
		frame.add(panel_Main);
		// 프레임에 각 패널들 더해줌
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer.start();
		frame.setVisible(true);

	}
}