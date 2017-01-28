package org.usfirst.frc.team6637.robot;

// Robot Requirements
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.CameraServer;




/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

// CORE OF ROBOT - Robot
public class Robot extends IterativeRobot {
	
	// Initialized Variables
	RobotDrive myRobot;
	Joystick stick;
	int autoLoopCounter;
	Victor VLeft, VRight;
	
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	VLeft = new Victor(2);
    	VRight = new Victor(1);
    	CameraServer.getInstance().startAutomaticCapture();
    	
    	myRobot = new RobotDrive(VLeft,VRight);
    	stick = new Joystick(0);	
    }
 

    /**
     * Runs the motor from a joystick.
     */
//    public void operatorControl() {
//        while (isOperatorControl() && isEnabled()) {
//        	// Set the motor's output.
//        	// This takes a number from -1 (100% speed in reverse) to +1 (100% speed going forward)
//        	motor.set(driveStick.getY());
//        	
//            Timer.delay(k_updatePeriod);	// wait 5ms to the next update
//        }
//        
//    }
//    
    
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
        //myRobot.mecanumDrive_Cartesian(left., right, null, null);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
