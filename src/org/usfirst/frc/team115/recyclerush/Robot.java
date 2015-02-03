
package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;
import org.usfirst.frc.team115.recyclerush.subsystems.Roller;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * @author Lee Mracek
 * This class is equivalent to RobotMain in LabVIEW and runs when the robot is turned on.
 * Note: If you change the class name or package, the manifest must be updated.
 */
public class Robot extends IterativeRobot {
	
	public static DriveTrain drive;
    public static OI oi;
    public static Roller leftRoller, rightRoller;

	public Robot() {
		drive = new DriveTrain();
        oi = new OI();
        leftRoller  = new Roller(RobotMap.ROLLER_MOTOR_LEFT,  RobotMap.ROLLER_SOLENOID_1,  RobotMap.ROLLER_SOLENOID_2);
        rightRoller = new Roller(RobotMap.ROLLER_MOTOR_RIGHT, RobotMap.ROLLER_SOLENOID_1, RobotMap.ROLLER_SOLENOID_2);
	}
    
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {

    }

    public void disabledInit(){

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
