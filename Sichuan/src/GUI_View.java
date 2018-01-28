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
		// frame ũ�� ����
		panel_Main = new JPanel();
		panel_Main.setLayout(new BorderLayout());
		panel_Top = new JPanel();
		// panel_Top.setSize(new Dimension(900, 50));
		// �ֻ����� ��ġ�� �г� ũ�� ���� �� ũ�� ����
		panel_Center = new JPanel();
		panel_Game_Main = new JPanel(new GridLayout(12, 12));
		// �߾� ��õ�� ������ ���� �� �г� ���� ��ư 11���� �迭 ���� gridlayout���� �� ��� �� 11�� ����
		panel_Bottom = new JPanel();
		// panel_Bottom.setSize(new Dimension(900, 50));
		// �ϴ� �г� ���� �� ũ�� ����
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		progressBar.setPreferredSize(new Dimension(650, 50));
		//���α׷����� ���� �� ������� �ִ�,�ּҰ� ����
		label_wait = new JLabel("ó�� �׸��� ������ �ֽʽÿ�");
		label_wait.setAlignmentX(Label.CENTER);;
		label_wait.setFont( new Font("����", 3, 15));
		label_wait.setPreferredSize(new Dimension(250, 50));
		
		label_Score = new JLabel(String.valueOf(event.score) + "��");
		label_Score.setHorizontalAlignment(10);
		label_Score.setPreferredSize(new Dimension(150, 50));
		//���� ǥ�ø� ���� �� �� �ʱⰪ ����
		for (int i = 0; i < 7; i++) {
			list.add(i);
			list.add(i);
		} // list�� �迭 �ε����� ������ i ����

		for (int i = 1; i < randArr.length; i++) {
			randArr[i] = rand.nextInt(100);
			for (int j = 0; j < i; j++) {
				if (randArr[j] == randArr[i]) {
					i--;
					break;
				} // if end
			}
		} // �̹��� �迭�� ������ ���� �ε��� ����

		for (int i = 0; i < charicter_Image.length; i = i + 2) {
			int temp = rand.nextInt(15);
			// �������� ���ڸ� �����ϰ�
			charicter_Image[randArr[i]] = new ImageIcon("img/" + temp + ".jpg");
			charicter_Image[randArr[i + 1]] = new ImageIcon("img/" + temp + ".jpg");
			// �׸����߱�� �̹����� ¦�� ������ŭ �־�� �ϱ� ������ �ΰ��� ����
		} // for end

		for (int i = 0; i < charicter_Button.length; i++) {
			for (int j = 0; j < charicter_Button[i].length; j++) {
				if (i == 0 || j == 0 || i == charicter_Button.length - 1
						|| j == charicter_Button.length - 1) {
					// �׵θ� �κ� ���� ����
					charicter_Button[i][j] = new JButton();
					charicter_Button[i][j].setPreferredSize(new Dimension(50, 50));
					// ��ư ũ�� ����
					charicter_Button[i][j].setContentAreaFilled(false);
					// ���� �����Ѱ� �߰����ϱ�
					charicter_Button[i][j].setBorderPainted(false);
					// �׵θ� ���̱� X
					charicter_Button[i][j].addMouseListener(event);
					panel_Game_Main.add(charicter_Button[i][j]);
				} else {
					//������ ���� ����
					charicter_Button[i][j] = new JButton(charicter_Image[temp_Num]);
					charicter_Button[i][j].setPreferredSize(new Dimension(50, 50));
					// ��ư ũ�� ����
					charicter_Button[i][j].setContentAreaFilled(false);
					// ���� �����Ѱ� �߰����ϱ�
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
		//////////////////////////////////////�гε� ���� ����///////////////////////////////////////////////////////
		panel_Top.add(label_wait);
		panel_Top.add(label_Score);
		panel_Bottom.add(progressBar);
		
		panel_Main.add(panel_Top, BorderLayout.NORTH);
		panel_Main.add(panel_Center, BorderLayout.CENTER);
		panel_Main.add(panel_Bottom, BorderLayout.SOUTH);
		//�гε� borderLayout ����
		frame.add(panel_Main);
		// �����ӿ� �� �гε� ������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer.start();
		frame.setVisible(true);

	}
}