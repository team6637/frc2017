package org.usfirst.frc.team6637.robot;

// Robot Requirements
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;




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
	// Motor Configuration
	RobotDrive chasisDrive;
	// Joystick Configuration
	Joystick leftStick;
	Joystick gearStick;
	// Button Configuration
	Button btn1;
	
	int autoLoopCounter;
	// Individual Motor Configuration
	Victor VLeft, VRight;
	Victor hopper = new Victor(5);
    Jaguar hopper2 = new Jaguar(6);
	Compressor gearCompressor = new Compressor();
	DoubleSolenoid doubleSolenoid1 = new DoubleSolenoid(1, 2);
	float hopperSpeed = 0.5f;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	// Assign Values to variables
    	VLeft = new Victor(2);
    	VRight = new Victor(1);
    	
    	chasisDrive = new RobotDrive(VLeft,VRight);
    	
    	// Controller Configuration
    	leftStick = new Joystick(0);	
    	gearStick = new Joystick(1);  
    	//btn1 = new JoystickButton(leftStick, 1);
    	
    	// Air Compressor Setup
    	gearCompressor = new Compressor(1);      
    	gearCompressor.start();
    	gearCompressor.setClosedLoopControl(true);
    	gearCompressor.setClosedLoopControl(false);

//    	doubleSolenoid1.set(DoubleSolenoid.Value.kOff);
//    	doubleSolenoid1.set(DoubleSolenoid.Value.kForward);
//    	doubleSolenoid1.set(DoubleSolenoid.Value.kReverse);
    
    	CameraServer.getInstance().startAutomaticCapture();
    }
    
    
 

    /**
     * Runs the motor from a joystick.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
       
        	 if(leftStick.getRawButton(1) == true)
             {
        		 
//                   doubleSolenoid1.set(true);
//                   doubleSolenoid1.set(false);
              }
              if(leftStick.getRawButton(2) == true)
              {
//            	  doubleSolenoid1.set(false);
//            	  doubleSolenoid1.set(true);
               }
            //Timer.delay(1);	// wait 5ms to the next update
        }
        
    }
    
    
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
    		chasisDrive.drive(-0.5, 0.0); 	// drive forwards half speed
			autoLoopCounter++;
			} else {
				chasisDrive.drive(0.0, 0.0); 	// stop robot
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
    	// Chasis Drive With Sensitivity 
    	
    	chasisDrive.arcadeDrive(leftStick, true);
//    	if(gearStick.getRawButton(1) == true)
//        {
//              s1.set(true);
//              s2.set(false);
//         }
//         if(gearStick.getRawButton(2) == true)
//         {
//              s1.set(false);
//              s2.set(true);
//          }
    	
    	
        //myRobot.mecanumDrive_Cartesian(left., right, null, null);
    	
    	if (gearStick.getRawButton(1)) {
            hopper.set(hopperSpeed);
        } else if (gearStick.getRawButton(2)) {
            hopper.set(-hopperSpeed);
        } else {
            hopper.set(0.0);
        }
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
    
}
