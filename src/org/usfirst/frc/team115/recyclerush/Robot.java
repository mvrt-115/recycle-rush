package org.usfirst.frc.team115.recyclerush;

import java.awt.Color;

import org.usfirst.frc.team115.recyclerush.commands.LEDStaticColor;
import org.usfirst.frc.team115.recyclerush.commands.StrobeLEDs;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;
import org.usfirst.frc.team115.recyclerush.subsystems.Grabber;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDstrip;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Lee Mracek
 *         This class is equivalent to RobotMain in LabVIEW and runs when the robot is turned on.
 *         Note: If you change the class name or package, the manifest must be updated.
 */
public class Robot extends IterativeRobot {

    public static DriveTrain drive;
    public static Claw claw;
    public static Grabber grabber;
    public static OI oi;
    public static LEDstrip ledStrip;
    
    public Robot() {
        drive = new DriveTrain();
        claw = new Claw();
        grabber = new Grabber();
        oi = new OI();
        ledStrip = new LEDstrip(RobotMap.PWM_LED1_RED, RobotMap.PWM_LED1_GREEN, RobotMap.PWM_LED1_BLUE);
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
		SmartDashboard.putData(new StrobeLEDs(ledStrip, new Color(255, 0, 0), new Color(0, 0, 255)));
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
