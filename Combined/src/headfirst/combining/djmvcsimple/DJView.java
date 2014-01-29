package headfirst.combining.djmvcsimple;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * mvc pattern
 * view class 
 * observer for both real time beats and bpm changes (must implement bpm observer)
 */

public class DJView implements ActionListener, BPMObserver {
	
	//references to model and controller
	//controller is used by control interface
	BeatModelInterface model;
	ControllerInterface controller;
	
	//display components
	//windows and layout managers
	JFrame viewFrame;
    JPanel viewPanel;
	JFrame controlFrame;
    JPanel controlPanel;
    //labels and textfields
    JLabel bpmOutputLabel;
    JLabel bpmLabel;
    JTextField bpmTextField;
    //buttons
    JButton setBPMButton;
    JButton increaseBPMButton;
    JButton decreaseBPMButton;
    //menu bar components
    JMenuBar menuBar;
    JMenu controlMenu;
    JMenuItem startMenuItem;
    JMenuItem stopMenuItem;
    
	public DJView(ControllerInterface controller, BeatModelInterface model) {
		// constructor gets a reference to model and controller
		System.out.println("in view constructor");
		this.controller = controller;
		this.model = model;
		//register as observers
		model.registerObserver((BPMObserver)this);
	}


	public void createView() {
		// Create all Swing components here
		System.out.println("in createview...");
		//create window
		JFrame.setDefaultLookAndFeelDecorated(true);
		viewFrame = new JFrame("View");
		viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewFrame.setSize(200, 100);
		//layout manager 1
		viewPanel = new JPanel(new GridLayout(2, 1));
		//txt label
		bpmOutputLabel = new JLabel("offline", SwingConstants.CENTER);
		
		//layout manager 2
		JPanel bpmPanel = new JPanel(new GridLayout(1, 1));
		//add component to layout manager 2
		bpmPanel.add(bpmOutputLabel);
		//add layout manager 2 to layout manager 1
		viewPanel.add(bpmPanel);
		//set region
		viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
		//set preffered size
		//viewFrame.pack();
		viewFrame.setVisible(true);
	}

	
	public void createControls() {
		// Create all Swing components here
		System.out.println("in createcontrols...");
		//create window
		JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(200, 100));
        //main layout manager
        controlPanel = new JPanel(new GridLayout(1, 2));
        //menu bar
        menuBar = new JMenuBar();
        controlMenu = new JMenu("DJ Control");
        //menu items
		startMenuItem = new JMenuItem("Start");
		stopMenuItem = new JMenuItem("Stop");
		JMenuItem exitMenuItem = new JMenuItem("Quit");
        //add menuitems to menu
		controlMenu.add(startMenuItem);
		controlMenu.add(stopMenuItem);
		controlMenu.add(exitMenuItem);
		
		//register buttons with action event
		startMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
			    //start controller
				controller.start();
			}
		});

        stopMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	//stop controller
            	controller.stop();
            }
        });
        
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
            	//exit application
            	System.exit(0);
            }
        });

		//add menubar to menu
        menuBar.add(controlMenu);
        //set menubar in the frame
        controlFrame.setJMenuBar(menuBar);

        //add labels, fields, buttons
        bpmTextField = new JTextField(2);
        bpmLabel = new JLabel("Enter BPM:", SwingConstants.RIGHT);
        setBPMButton = new JButton("Set");
        setBPMButton.setSize(new Dimension(10,40));
        increaseBPMButton = new JButton(">>");
        decreaseBPMButton = new JButton("<<");
        setBPMButton.addActionListener(this);
        increaseBPMButton.addActionListener(this);
        decreaseBPMButton.addActionListener(this);
        //button layout manager
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        //add buttons to button layout manager
		buttonPanel.add(decreaseBPMButton);
		buttonPanel.add(increaseBPMButton);
		//input layout manager
        JPanel enterPanel = new JPanel(new GridLayout(1, 2));
        //add to input layout manager
        enterPanel.add(bpmLabel);
        enterPanel.add(bpmTextField);
        //inside layout manager
        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        //add input layout manager to inside layout manager
        insideControlPanel.add(enterPanel);
        //add button layout manager to inside layout manager
        insideControlPanel.add(setBPMButton);
        //add set button to inside layout manager
        insideControlPanel.add(buttonPanel);
        //add inside layout manager to main layout manager
        controlPanel.add(insideControlPanel);
        
        bpmLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        bpmOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        controlFrame.getRootPane().setDefaultButton(setBPMButton);
        //add main layout manager to frame
        controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        controlFrame.pack();
        controlFrame.setVisible(true);
	}   
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// when set button is clicked
		if(e.getSource() == setBPMButton) {
			//tell controller to set bpm
			System.out.println("setBPMButton");
			if (!isNumeric(bpmTextField.getText())) {
				System.out.println("njente");
				createAlertFrame();
			} else {
				int bpm = Integer.parseInt(bpmTextField.getText());
				controller.setBPM(bpm);
			}
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
		System.out.println("DJView.enableStopMenuItem()");
    	stopMenuItem.setEnabled(true);
	}

	public void disableStopMenuItem() {
		System.out.println("DJView.disableStopMenuItem()");
    	stopMenuItem.setEnabled(false);
	}

	public void enableStartMenuItem() {
		System.out.println("DJView.enableStartMenuItem()");
    	startMenuItem.setEnabled(true);
	}

	public void disableStartMenuItem() {
		System.out.println("DJView.disableStartMenuItem()");
    	startMenuItem.setEnabled(false);
	}
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static void createAlertFrame() {
		//create alert frame
		JFrame alertFrame = new JFrame("Alert");
		alertFrame.setSize(200, 100);
		JPanel alertPanel = new JPanel(new GridLayout(1, 1));
		JLabel alertLabel = new JLabel("Unesi vrijednost", SwingConstants.CENTER);
		alertPanel.add(alertLabel);
		alertFrame.getContentPane().add(alertPanel, BorderLayout.CENTER);
		alertFrame.setVisible(true);
	}

	@Override
	public void updateBPM() {
		System.out.println("DJView.updateBPM()");
		//update bpm is called when model state is changed
		//then we get current bpm and update bpm display 
		int bpm = model.getBPM();
		if (bpm == 0) {
			bpmOutputLabel.setText("OFFLINE");
		} else {
			bpmOutputLabel.setText(bpm + " bpm");
		}
	}
}
