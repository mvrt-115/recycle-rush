package org.usfirst.frc.team115.recyclerush;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

/**
 *	This class is equivalent to RobotMain in LabVIEW and runs when the robot is turned on.
 *  Note: If you change the class name or package, the manifest must be updated.
 *  @author MVRT
 */
public class Robot extends IterativeRobot {

    public static DriveTrain drive;
    public static OI oi;
    public static Elevator elevator;

    public Robot() {
        drive = new DriveTrain();
        oi = new OI();
        elevator = new Elevator(0, 0, 0);
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

    public void disabledInit() {

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
}