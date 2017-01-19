package org.usfirst.frc.team6637.robot;

// Robot Requirements
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

// Motor Drive Dependencies
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

// CORE OF ROBOT – Robot
public class Robot extends IterativeRobot {
	
	// Initialized Variables
	public RobotDrive myRobot;
	private SpeedController motor;	// the motor to directly control with a joystick
    private Joystick stick;
    private final double k_updatePeriod = 0.005; // update every 0.005 seconds/5 milliseconds (200Hz)
	int autoLoopCounter;
	
	// More Commands
	RobotDrive chassis = new RobotDrive(1, 2);
	Joystick left = new Joystick(1);
	Joystick right = new Joystick(2);
	Joystick control = new Joystick(3);
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	myRobot = new RobotDrive(0,1);
    	stick = new Joystick(0);
    }
    
    /**
     * START: Robot Drive Mechanisms
     * 
     * -----------------------------
     */
    
    public Robot() {
        motor = new Talon(0);		// initialize the motor as a Talon on channel 0
        stick = new Joystick(0);	// initialize the joystick on port 0
    }

    /**
     * Runs the motor from a joystick.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
        	// Set the motor's output.
        	// This takes a number from -1 (100% speed in reverse) to +1 (100% speed going forward)
        	motor.set(stick.getY());
        	
            Timer.delay(k_updatePeriod);	// wait 5ms to the next update
        }
        
    }
    
    /**
     * END: Robot Drive Mechanisms
     * 
     * ---------------------------
     */
    
    
    
    /**
     * Autonomous Functionality
     */
    public void autonomousInit() {
    	autoLoopCounter = 0;
    }

    
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(autoLoopCounter < 100) //Check if we've completed 100 loops (approximately 2 seconds)
		{
			myRobot.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
			myRobot.drive(0.0, 0.0); 	// stop robot
		}
    }
    
    /**
     * This function is called once each time the robot enters tele-operated mode
     */
    public void teleopInit(){
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        myRobot.arcadeDrive(stick);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
