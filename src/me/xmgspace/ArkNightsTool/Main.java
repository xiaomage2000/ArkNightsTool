package me.xmgspace.ArkNightsTool;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

	private JFrame frmArknightstool;
	private JTextField startTextField_1_1;
	private JTextField startTextField_1_2;
	private JTextField startTextField_2_1;
	private JTextField startTextField_2_2;
	private JTextField overTextField_1;
	private JTextField overTextField_2;
	private JTextField sanityTextField;
	private JTextField onceSanityTextField;
	private JTextField timeTextField;
	private JTextField mousePositionTextField_1;
	private JTextField mousePositionTextField_2;
	private JTextArea logTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Main frame = new Main();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.frmArknightstool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			BufferedReader in = new BufferedReader(new FileReader("ArkNightsData"));
			frame.startTextField_1_1.setText(in.readLine());
			frame.startTextField_1_2.setText(in.readLine());
			frame.startTextField_2_1.setText(in.readLine());
			frame.startTextField_2_2.setText(in.readLine());
			frame.overTextField_1.setText(in.readLine());
			frame.overTextField_2.setText(in.readLine());
			frame.onceSanityTextField.setText(in.readLine());
			frame.timeTextField.setText(in.readLine());
			in.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true) {
			Point point = MouseInfo.getPointerInfo().getLocation();
			frame.mousePositionTextField_1.setText(Double.toString(point.getX()));
			frame.mousePositionTextField_2.setText(Double.toString(point.getY()));
		    try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Create the application.
	 */
	public Main() {
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmArknightstool = new JFrame();
		frmArknightstool.setAlwaysOnTop(true);
		frmArknightstool.setResizable(false);
		frmArknightstool.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/me/xmgspace/ArkNightsTool/icon.jpg")));
		frmArknightstool.setTitle("ArkNightsTool");
		frmArknightstool.setBounds(100, 100, 665, 577);
		frmArknightstool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel initPanel = new JPanel();
		initPanel.setBorder(new TitledBorder(null, "\u521D\u59CB\u5316PRTS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new TitledBorder(null, "\u63A7\u5236\u4E2D\u5FC3", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JScrollPane logScrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frmArknightstool.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(initPanel, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(15, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(logScrollPane)
							.addGap(17))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(initPanel, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						.addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(logScrollPane, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(208, Short.MAX_VALUE))
		);
		//
		logTextArea = new JTextArea();
		logTextArea.setEditable(false);
		logScrollPane.setViewportView(logTextArea);
		JButton startButton = new JButton("开始行动！");
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Start t = new Start();
				t.start();
			}
		});
		startButton.setToolTipText("开始行动！");
		
		JButton stopButton = new JButton("有内鬼终止行动");
		stopButton.addMouseListener(new MouseAdapter() {
//			@SuppressWarnings({ "deprecation", "unused" })
			@Override
			public void mouseClicked(MouseEvent arg0) {
//					t.stop();
				logTextArea.setText(logTextArea.getText()+"===当前功能正在开发===\n");
			}
		});
		stopButton.setToolTipText("有内鬼终止行动");
		
		JButton exitButton = new JButton("退出程序");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitButton.setToolTipText("退出程序");
		GroupLayout gl_controlPanel = new GroupLayout(controlPanel);
		gl_controlPanel.setHorizontalGroup(
			gl_controlPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_controlPanel.createSequentialGroup()
							.addGap(30)
							.addComponent(startButton))
						.addGroup(gl_controlPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(stopButton))
						.addGroup(gl_controlPanel.createSequentialGroup()
							.addGap(38)
							.addComponent(exitButton)))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		gl_controlPanel.setVerticalGroup(
			gl_controlPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_controlPanel.createSequentialGroup()
					.addGap(50)
					.addComponent(startButton)
					.addGap(63)
					.addComponent(stopButton)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(exitButton)
					.addGap(63))
		);
		controlPanel.setLayout(gl_controlPanel);
		
		JLabel startLabel_1 = new JLabel("开始行动1按钮位置");
		startLabel_1.setToolTipText("开始行动1按钮位置");
		
		JLabel startLabel_2 = new JLabel("开始行动2按钮位置");
		startLabel_2.setToolTipText("开始行动2按钮位置");
		
		JLabel timeLabel = new JLabel("通关时间(秒)");
		timeLabel.setToolTipText("通关时间");
		
		JLabel sanityLabel = new JLabel("当前理智");
		sanityLabel.setToolTipText("当前理智");
		
		JLabel onceSanityLabel = new JLabel("通关消耗理智");
		
		JLabel overLabel = new JLabel("结束行动点击位置");
		overLabel.setToolTipText("结束行动点击位置");
		
		JLabel mousePositionLabel = new JLabel("当前鼠标指针位置");
		
		startTextField_1_1 = new JTextField();
		startTextField_1_1.setColumns(10);
		
		startTextField_1_2 = new JTextField();
		startTextField_1_2.setColumns(10);
		
		startTextField_2_1 = new JTextField();
		startTextField_2_1.setColumns(10);
		
		startTextField_2_2 = new JTextField();
		startTextField_2_2.setColumns(10);
		
		overTextField_1 = new JTextField();
		overTextField_1.setColumns(10);
		
		overTextField_2 = new JTextField();
		overTextField_2.setColumns(10);
		
		sanityTextField = new JTextField();
		sanityTextField.setColumns(10);
		
		onceSanityTextField = new JTextField();
		onceSanityTextField.setColumns(10);
		
		timeTextField = new JTextField();
		timeTextField.setColumns(10);
		
		mousePositionTextField_1 = new JTextField();
		mousePositionTextField_1.setEditable(false);
		mousePositionTextField_1.setColumns(10);
		
		mousePositionTextField_2 = new JTextField();
		mousePositionTextField_2.setEditable(false);
		mousePositionTextField_2.setColumns(10);
		GroupLayout gl_initPanel = new GroupLayout(initPanel);
		gl_initPanel.setHorizontalGroup(
			gl_initPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_initPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_initPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_initPanel.createSequentialGroup()
							.addComponent(startLabel_1)
							.addGap(18)
							.addComponent(startTextField_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(startTextField_1_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_initPanel.createSequentialGroup()
							.addGroup(gl_initPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(startLabel_2)
								.addComponent(overLabel)
								.addComponent(sanityLabel)
								.addComponent(onceSanityLabel)
								.addComponent(timeLabel)
								.addComponent(mousePositionLabel))
							.addGap(18)
							.addGroup(gl_initPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_initPanel.createSequentialGroup()
									.addComponent(mousePositionTextField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(mousePositionTextField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(timeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(onceSanityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sanityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_initPanel.createSequentialGroup()
									.addComponent(overTextField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(overTextField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_initPanel.createSequentialGroup()
									.addComponent(startTextField_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(startTextField_2_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(74, Short.MAX_VALUE))
		);
		gl_initPanel.setVerticalGroup(
			gl_initPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_initPanel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(startLabel_1)
						.addComponent(startTextField_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(startTextField_1_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(startLabel_2)
						.addComponent(startTextField_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(startTextField_2_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(overLabel)
						.addComponent(overTextField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(overTextField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(sanityLabel)
						.addComponent(sanityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(onceSanityLabel)
						.addComponent(onceSanityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(timeLabel)
						.addComponent(timeTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(mousePositionLabel)
						.addComponent(mousePositionTextField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(mousePositionTextField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		initPanel.setLayout(gl_initPanel);
		frmArknightstool.getContentPane().setLayout(groupLayout);
	}
	
	class Start extends Thread {
		public void run() {
			Robot robot = null;
			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			Random random = new Random();
			int count = 0;
			try {
				count = (Integer.parseInt(sanityTextField.getText()))/(Integer.parseInt(onceSanityTextField.getText()));
			} catch(Exception e) {
				e.printStackTrace();
				logTextArea.setText(logTextArea.getText()+"请输入正确的理智数值！\n");
			}
			int start1_1 = 0;
			try {
				start1_1 = Integer.parseInt(startTextField_1_1.getText());
			} catch(Exception e) {
				e.printStackTrace();
				logTextArea.setText(logTextArea.getText()+"请输入正确的位置！\n");
			}
			int start1_2 = 0;
			try {
				start1_2 = Integer.parseInt(startTextField_1_2.getText());
			} catch(Exception e) {
				e.printStackTrace();
				logTextArea.setText(logTextArea.getText()+"请输入正确的位置！\n");
			}
			int start2_1 = 0;
			try {
				start2_1 = Integer.parseInt(startTextField_2_1.getText());
			} catch(Exception e) {
				e.printStackTrace();
				logTextArea.setText(logTextArea.getText()+"请输入正确的位置！\n");
			}
			int start2_2 = 0;
			try {
				start2_2 = Integer.parseInt(startTextField_2_2.getText());
			} catch(Exception e) {
				e.printStackTrace();
				logTextArea.setText(logTextArea.getText()+"请输入正确的位置！\n");
			}
			int over1 = 0;
			try {
				over1 = Integer.parseInt(overTextField_1.getText());
			} catch(Exception e) {
				e.printStackTrace();
				logTextArea.setText(logTextArea.getText()+"请输入正确的位置！\n");
			}
			int over2 = 0;
			try {
				over2 = Integer.parseInt(overTextField_2.getText());
			} catch(Exception e) {
				e.printStackTrace();
				logTextArea.setText(logTextArea.getText()+"请输入正确的位置！\n");
			}
			int one_time = 999;
			try {
				one_time = Integer.parseInt(timeTextField.getText());
			} catch(Exception e) {
				e.printStackTrace();
				logTextArea.setText(logTextArea.getText()+"请输入正确的通关时间\n");
			}
			logTextArea.setText(logTextArea.getText()+"Rojer！一共要执行"+count+"次任务 任务期间请不要做其他事情\n");
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter("ArkNightsData"));
				out.write(startTextField_1_1.getText()+"\n");
				out.write(startTextField_1_2.getText()+"\n");
				out.write(startTextField_2_1.getText()+"\n");
				out.write(startTextField_2_2.getText()+"\n");
				out.write(overTextField_1.getText()+"\n");
				out.write(overTextField_2.getText()+"\n");
				out.write(onceSanityTextField.getText()+"\n");
				out.write(timeTextField.getText()+"\n");
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logTextArea.setText("===Version 2021.9.20===\n");
			logTextArea.setText(logTextArea.getText()+"===任务开始===\n");
			logTextArea.setCaretPosition(logTextArea.getText().length());
			for(int i=1;i<=count;i++) {
				logTextArea.setText(logTextArea.getText()+"===开始执行第"+ i +"次任务===\n");
				logTextArea.setCaretPosition(logTextArea.getText().length());
				int _start1_1 = start1_1+random.nextInt(50);
				int _start1_2 = start1_2+random.nextInt(15);
				robot.mouseMove(_start1_1,_start1_2);
				robot.mouseMove(_start1_1,_start1_2);
				robot.mouseMove(_start1_1,_start1_2);
				logTextArea.setText(logTextArea.getText()+"开始行动1点击位置："+_start1_1+","+_start1_2+"\n");
				logTextArea.setCaretPosition(logTextArea.getText().length());
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.delay(150+random.nextInt(10));
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				logTextArea.setText(logTextArea.getText()+"开始行动1\n");
				logTextArea.setCaretPosition(logTextArea.getText().length());
				robot.delay(5000+random.nextInt(500));
				int _start2_1 = start2_1+random.nextInt(50);
				int _start2_2 = start2_2+random.nextInt(100);
				robot.mouseMove(_start2_1,_start2_2);
				robot.mouseMove(_start2_1,_start2_2);
				robot.mouseMove(_start2_1,_start2_2);
				logTextArea.setText(logTextArea.getText()+"开始行动2点击位置："+_start2_1+","+_start2_2+"\n");
				logTextArea.setCaretPosition(logTextArea.getText().length());
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.delay(150+random.nextInt(10+random.nextInt(10)));
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				logTextArea.setText(logTextArea.getText()+"开始行动2，行动开始\n");
				logTextArea.setCaretPosition(logTextArea.getText().length());
				robot.delay(60000);
				int _one_time = one_time;
				while(_one_time*1000-60000 > 0) {
					robot.delay(_one_time*1000-60000);
					_one_time-=60;
					logTextArea.setText(logTextArea.getText()+"行动仍然在进行中...\n");
					logTextArea.setCaretPosition(logTextArea.getText().length());
				}
				robot.delay(random.nextInt(10000)); 
				int _over1 = over1+random.nextInt(150);
				int _over2 = over2+random.nextInt(80);
				logTextArea.setText(logTextArea.getText()+"结束行动点击位置："+_over1+","+_over2+"\n");
				logTextArea.setText(logTextArea.getText()+"行动结束\n");
				logTextArea.setCaretPosition(logTextArea.getText().length());
				robot.mouseMove(_over1,_over2);
				robot.mouseMove(_over1,_over2);
				robot.mouseMove(_over1,_over2);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.delay(150+random.nextInt(10));
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				logTextArea.setText(logTextArea.getText()+"回到选关界面\n");
				logTextArea.setText(logTextArea.getText()+"===第"+ i +"次任务执行完毕===\n");
				logTextArea.setCaretPosition(logTextArea.getText().length());
				robot.delay(8000+random.nextInt(2000)); 
			}
			logTextArea.setText(logTextArea.getText()+"=====所有任务均已执行完毕=====\n");
			logTextArea.setCaretPosition(logTextArea.getText().length());
		}
	};
}