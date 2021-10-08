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
import java.io.FileReader;
import java.util.Random;

public class Main {
	// 游戏版本
	public static final String VERSION = "2021.10.05";
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
//	模拟点击的进程
	private Start thread;
//	日志内容
	private String logData = "";
//	用户输入数据
	private int[] inputData = new int[10];

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
		// 循环,获取当前鼠标位置并显示
		while (true) {
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
		frmArknightstool.setIconImage(
				Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/me/xmgspace/ArkNightsTool/icon.jpg")));
		frmArknightstool.setTitle("ArkNightsTool");
		frmArknightstool.setBounds(100, 100, 665, 577);
		frmArknightstool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel initPanel = new JPanel();
		initPanel.setBorder(
				new TitledBorder(null, "\u521D\u59CB\u5316PRTS", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(
				new TitledBorder(null, "\u63A7\u5236\u4E2D\u5FC3", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JScrollPane logScrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frmArknightstool.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(initPanel, GroupLayout.PREFERRED_SIZE, 436, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(15, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup().addComponent(logScrollPane).addGap(17)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(initPanel, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						.addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addComponent(logScrollPane, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(208, Short.MAX_VALUE)));
		//
		logTextArea = new JTextArea();
		logTextArea.setEditable(false);
		logScrollPane.setViewportView(logTextArea);
		JButton startButton = new JButton("开始行动！");
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

//				
				startOperate();
			}
		});
		startButton.setToolTipText("开始行动！");

		JButton stopButton = new JButton("有内鬼终止行动");
		stopButton.addMouseListener(new MouseAdapter() {
//			@SuppressWarnings({ "deprecation", "unused" })
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (thread != null) {
					thread.kill();
				} else {
					logAppend("没有行动在执行");
				}

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
		gl_controlPanel.setHorizontalGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_controlPanel.createSequentialGroup().addGap(26)
						.addGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(exitButton, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
								.addComponent(stopButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(startButton, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
						.addGap(31)));
		gl_controlPanel.setVerticalGroup(gl_controlPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_controlPanel.createSequentialGroup().addGap(50).addComponent(startButton).addGap(71)
						.addComponent(stopButton).addGap(73).addComponent(exitButton)
						.addContainerGap(62, Short.MAX_VALUE)));
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
		gl_initPanel.setHorizontalGroup(gl_initPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_initPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_initPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_initPanel.createSequentialGroup().addComponent(startLabel_1).addGap(18)
										.addComponent(startTextField_1_1, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(startTextField_1_2, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_initPanel.createSequentialGroup()
										.addGroup(gl_initPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(startLabel_2).addComponent(overLabel)
												.addComponent(sanityLabel).addComponent(onceSanityLabel)
												.addComponent(timeLabel).addComponent(mousePositionLabel))
										.addGap(18)
										.addGroup(gl_initPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_initPanel.createSequentialGroup()
														.addComponent(mousePositionTextField_1,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(mousePositionTextField_2,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
												.addComponent(timeTextField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(onceSanityTextField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(
														sanityTextField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_initPanel.createSequentialGroup()
														.addComponent(overTextField_1, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(
																overTextField_2, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_initPanel.createSequentialGroup()
														.addComponent(startTextField_2_1, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
														.addGap(18).addComponent(startTextField_2_2,
																GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)))))
						.addContainerGap(144, Short.MAX_VALUE)));
		gl_initPanel.setVerticalGroup(gl_initPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_initPanel
				.createSequentialGroup().addGap(21)
				.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE).addComponent(startLabel_1)
						.addComponent(startTextField_1_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(startTextField_1_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE).addComponent(startLabel_2)
						.addComponent(startTextField_2_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(startTextField_2_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE).addComponent(overLabel)
						.addComponent(overTextField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(overTextField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_initPanel
						.createParallelGroup(Alignment.BASELINE).addComponent(sanityLabel).addComponent(sanityTextField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE).addComponent(onceSanityLabel)
						.addComponent(onceSanityTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_initPanel
						.createParallelGroup(Alignment.BASELINE).addComponent(timeLabel).addComponent(timeTextField,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_initPanel.createParallelGroup(Alignment.BASELINE).addComponent(mousePositionLabel)
						.addComponent(mousePositionTextField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(mousePositionTextField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(49, Short.MAX_VALUE)));
		initPanel.setLayout(gl_initPanel);
		frmArknightstool.getContentPane().setLayout(groupLayout);
	}

	/**
	 * 模拟鼠标点击进程类
	 * 
	 *
	 */
	class Start extends Thread {
		/**
		 * orderStatus 进程状态 指令标记,true 进程继续执行,false 进程终止 random 产生随机数值,用于 点击位置随机位移 robot
		 * 模拟鼠标点击 count 执行游戏次数 cycle 游戏运行周期 datas 整型数组, 依次为 三个坐标值(3*2),
		 * 当前理智,单次理智消耗,游戏运行周期,游戏运行次数
		 */
		private boolean orderStatus = false;
		private Random random = new Random();
		private Robot robot = null;
		int sanity;
		int count;
		int cycle_time;
		int[] datas;

		/**
		 * 传入用户输入数据
		 * 
		 * @param args
		 */
		public Start(int[] args) {
			datas = args;
			this.count = args[9];
			this.cycle_time = args[8];
		}

		public void run() {
			// 允许进程执行
			this.orderStatus = true;

			try {
				// 实例化 Robot 
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
				logTextArea.setText(logTextArea.getText() + "发生异常 程序终止\n");
				logTextArea.setCaretPosition(logTextArea.getText().length());
				//				发生异常,禁止进程继续执行
				this.orderStatus = false;
				return;
			}

			logAppend(String.format("===Version %s===", VERSION));
			logAppend("===\t任务开始\t ===");
			logAppend(String.format("当前 %d 理智,每次消耗 %d 理智,可以执行  %d 次", this.datas[6], this.datas[7], this.datas[9]));
			int i = 0;

			for (i = 1; this.orderStatus && i <= count; i++) {

				logAppend("===开始执行第" + i + "次任务===");
				// 点击第一个坐标点
				this.moveAndPressMouse(this.datas[0], this.datas[1]);

				robot.delay(5000 + random.nextInt(500));
				// 点击第二个坐标点
				this.moveAndPressMouse(this.datas[2], this.datas[3]);

				// 等待游戏运行 
				{
					int _one_time = cycle_time;
					String current_log = logTextArea.getText();

					while (_one_time > 0) {

						robot.delay(1000);
						_one_time--;
						String log_str = String.format("等待游戏运行..., 剩余时间  %d 秒", _one_time);
						logLive(log_str);

					}

				}

				robot.delay(random.nextInt(10000));
				// 点击第三个坐标点
				this.moveAndPressMouse(this.datas[4], this.datas[5]);

				logAppend("回到选关界面");
				sanity = (count - i) * this.datas[7];
				setSanity("" + sanity);

				logAppend(String.format("===第  %d 次任务执行完毕,剩余%d理智 ===", i, sanity));

				robot.delay(8000 + random.nextInt(2000));
			}
			if (!this.orderStatus) {
				logAppend("*****操作暂停*****");
			}

		}

		public void kill() {
			this.orderStatus = false;

		}

		public void moveAndPressMouse(int a, int b) {
			int _start1_1 = a + random.nextInt(50);
			int _start1_2 = b + random.nextInt(15);

			robot.mouseMove(_start1_1, _start1_2);
			robot.mouseMove(_start1_1, _start1_2);
			robot.mouseMove(_start1_1, _start1_2);

			robot.mousePress(InputEvent.BUTTON1_MASK);
			robot.delay(150 + random.nextInt(10));
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
			logAppend(String.format("点击坐标: %d , %d ", _start1_1, _start1_2));
			System.out.println(_start1_1 + "  " + _start1_2);
		}

	}

	public void startOperate() {
		if (checkInput()) {
			
			try {
				thread = new Start(inputData);
				Thread.sleep(1000);
				thread.start();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logAppend("程序异常!!!!!");
			}
			
		}

	}
	/**
	 * 检查输入窗口,保存到全局变量
	 * @return true 全部输入 符合规则
	 */
	public boolean checkInput() {
		boolean result = false;
		int count = 0;
		int start1_1 = 0;
		int start1_2 = 0;
		int start2_1 = 0;
		int start2_2 = 0;
		int over1 = 0;
		int over2 = 0;
		int cycle_time = 999;

		int spend_resource = 0;
		int current_resource = 0;
		int step = 0;

		String[] warningMessages = { "请输入正确的理智数值！", "请输入正确的位置！", "请输入正确的通关时间" };

		try {
			current_resource = Integer.parseInt(sanityTextField.getText());
			spend_resource = Integer.parseInt(onceSanityTextField.getText());

			count = current_resource / spend_resource;
			step++;
			start1_1 = Integer.parseInt(startTextField_1_1.getText());
			start1_2 = Integer.parseInt(startTextField_1_2.getText());
			start2_1 = Integer.parseInt(startTextField_2_1.getText());
			start2_2 = Integer.parseInt(startTextField_2_2.getText());
			over1 = Integer.parseInt(overTextField_1.getText());
			over2 = Integer.parseInt(overTextField_2.getText());

			step++;
			cycle_time = Integer.parseInt(timeTextField.getText());
			result = true;
		} catch (Exception e) {
			//	根据 step 标记 打印 提示信息
			logAppend(warningMessages[step]);

		} finally {
			// 记录数据
			if (step == 2 && result) {
				inputData[0] = start1_1;
				inputData[1] = start1_2;
				inputData[2] = start2_1;
				inputData[3] = start2_2;
				inputData[4] = over1;
				inputData[5] = over2;
				inputData[6] = current_resource;
				inputData[7] = spend_resource;
				inputData[8] = cycle_time;
				inputData[9] = count;
				for (int i : inputData) {
					System.out.println(i);

				}
			}

		}

		return result;
	}
	 
	/**
	 * 最后端 添加 打印信息 自带换行 保存到全局变量
	 * @param logStr 要打印的信息
	 */
	public void logAppend(String logStr) {
		logData = logData + logStr + "\n";
		logTextArea.setText(logData);
		logTextArea.setCaretPosition(logData.length());
	}
	/**
	 * 打印信息, 不保存到全局变量
	 * @param logStr
	 */
	public void logLive(String logStr) {
		logTextArea.setText(logData + logStr + "\n");
		logTextArea.setCaretPosition(logTextArea.getText().length());
	}
	/**
	 * 清楚打印信息
	 */
	public void logClear() {
		logData = "";
		logTextArea.setText(logData);
		logTextArea.setCaretPosition(0);
	}
	/**
	 * 设置 当前理智 数值
	 * @param value
	 */
	private void setSanity(String value) {
		this.sanityTextField.setText(value);

	}
}
