package headfirst.combining.djmvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * mvc pattern
 * djview is observer for both real time beats and bpm changes 
 */

public class DJView implements ActionListener, BeatObserver, BPMObserver {
	
	BeatModelInterface model;
	//used by control interface
	ControllerInterface controller;
    //display components
	JFrame viewFrame;
    JPanel viewPanel;
	BeatBar beatBar;
	JLabel bpmOutputLabel;
    JFrame controlFrame;
    JPanel controlPanel;
    //lebel and textfield
    JLabel bpmLabel;
    JTextField bpmTextField;
    //buttons
    JButton setBPMButton;
    JButton increaseBPMButton;
    JButton decreaseBPMButton;
    //menu bar components
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem startMenuItem;
    JMenuItem stopMenuItem;

	public DJView(ControllerInterface controller, BeatModelInterface model) {
		// get a reference to model and controller
		this.controller = controller;
		this.model = model;
		//register as observers
		model.registerObserver((BeatObserver)this);
		model.registerObserver((BPMObserver)this);
	}

	public void createView() {
		// Create all Swing components here
		viewPanel = new JPanel(new GridLayout(1, 2));
		viewFrame = new JFrame("View");
		viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewFrame.setSize(100, 80);
		bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
		beatBar = new BeatBar();
		JPanel bpmPanel = new JPanel(new GridLayout(2, 1));
		bpmPanel.add(beatBar);
		bpmPanel.add(bpmOutputLabel);
		viewPanel.add(bpmPanel);
		viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
		viewFrame.pack();
		viewFrame.setVisible(true);
	}
	
	public void createControls() {
		// Create all Swing components here
		JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(100, 80));

        controlPanel = new JPanel(new GridLayout(1, 2));

        menuBar = new JMenuBar();
        menu = new JMenu("DJ Control");
        startMenuItem = new JMenuItem("Start");
        menu.add(startMenuItem);
        startMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.start();
            }
        });
        stopMenuItem = new JMenuItem("Stop");
        menu.add(stopMenuItem); 
        stopMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                controller.stop();
            }
        });
        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        menu.add(exit);
        menuBar.add(menu);
        controlFrame.setJMenuBar(menuBar);

        bpmTextField = new JTextField(2);
        bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
        setBPMButton = new JButton("Set");
        setBPMButton.setSize(new Dimension(10,40));
        increaseBPMButton = new JButton(">>");
        decreaseBPMButton = new JButton("<<");
        setBPMButton.addActionListener(this);
        increaseBPMButton.addActionListener(this);
        decreaseBPMButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

		buttonPanel.add(decreaseBPMButton);
		buttonPanel.add(increaseBPMButton);

        JPanel enterPanel = new JPanel(new GridLayout(1, 2));
        enterPanel.add(bpmLabel);
        enterPanel.add(bpmTextField);
        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        insideControlPanel.add(enterPanel);
        insideControlPanel.add(setBPMButton);
        insideControlPanel.add(buttonPanel);
        controlPanel.add(insideControlPanel);
        
        bpmLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        controlFrame.getRootPane().setDefaultButton(setBPMButton);
        controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        controlFrame.pack();
        controlFrame.setVisible(true);
	}   
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// when set button is clicked
		if(e.getSource() == setBPMButton) {
			System.out.println("setBPMButton");
			int bpm = Integer.parseInt(bpmTextField.getText());
			controller.setBPM(bpm);
		}
		//when increase is clicked
		if(e.getSource() == increaseBPMButton) {
			System.out.println("increaseBPMButton");
			controller.increaseBPM();
		}
		//when decrease is clicked
		if(e.getSource() == decreaseBPMButton) {
			System.out.println("decreaseBPMButton");
			controller.decreaseBPM();
		}
	}
	
	public void enableStopMenuItem() {
    	stopMenuItem.setEnabled(true);
	}

	public void disableStopMenuItem() {
    	stopMenuItem.setEnabled(false);
	}

	public void enableStartMenuItem() {
    	startMenuItem.setEnabled(true);
	}

	public void disableStartMenuItem() {
    	startMenuItem.setEnabled(false);
	}

	@Override
	public void updateBPM() {
		// when bpm is changed, update current bpm
		int bpm = model.getBPM();
		if (bpm == 0) {
			bpmOutputLabel.setText("offline");
		} else {
			bpmOutputLabel.setText("Current BPM: " + bpm);
		}
	}

	@Override
	public void updateBeat() {
		// when model starts new beat
		// set to 100 and let it handle
		if (beatBar != null) {
			beatBar.setValue(100);
		}
	}

}
