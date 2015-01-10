package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.subsystems.MecanumDriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;
import org.usfirst.frc.team115.recyclerush.subsystems.Grabber;

/**
 * @author Lee Mracek
 *         This class is equivalent to RobotMain in LabVIEW and runs when the robot is turned on.
 *         Note: If you change the class name or package, the manifest must be updated.
 */
public class Robot extends IterativeRobot {

    public static MecanumDriveTrain drive;
    public static Claw claw;
    public static Grabber grabber;
    public static OI oi;

    public Robot() {
        drive = new MecanumDriveTrain();
        claw = new Claw();
        grabber = new Grabber();
        oi = new OI();
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
