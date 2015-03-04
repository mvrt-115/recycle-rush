package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.subsystems.BinGrabber;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;
import org.usfirst.frc.team115.recyclerush.subsystems.CompressorSystem;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;
import org.usfirst.frc.team115.recyclerush.subsystems.Roller;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * @author MVRT
 * This class is equivalent to RobotMain in LabVIEW and runs when the robot is turned on.
 * Note: If you change the class name or package, the manifest must be updated.
 */
public class Robot extends IterativeRobot {

    public static DriveTrain drive;
    public static Stabilizer stabilizer;
    public static Claw claw;
    public static Roller roller;
    public static Elevator elevator;
    public static CompressorSystem compressor;
    public static BinGrabber binGrabber;
    public static OI oi;

    public Robot() {
        drive = new DriveTrain();
        stabilizer = new Stabilizer();
        claw = new Claw();
        roller  = new Roller();
        compressor = new CompressorSystem();
        elevator = new Elevator();
        binGrabber = new BinGrabber();
        oi = new OI();
    }
    
    public void robotInit() {
    	drive.initialize();
    	elevator.initialize();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {}

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {}

    public void disabledInit() {
    	Robot.elevator.brake();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
}
