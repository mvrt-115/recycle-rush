
package org.usfirst.frc.team115.recyclerush;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;
import org.usfirst.frc.team115.recyclerush.subsystems.RobotElevator;

/**
 * @author Lee Mracek
 * This class is equivalent to RobotMain in LabVIEW and runs when the robot is turned on.
 * Note: If you change the class name or package, the manifest must be updated.
 */
public class Robot extends IterativeRobot {
	public static OI oi;
	public static RobotElevator Elevator;
	public static DriveTrain drive;
	
	public DriveTrain getDrive() {
		return drive;
	}

    public void robotInit() {
    
    	oi = new OI();
    	Elevator = new RobotElevator();
    	drive = new DriveTrain();
    	
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
