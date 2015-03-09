package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.auton.HulkSmashAutonA;
import org.usfirst.frc.team115.recyclerush.commands.auton.HulkSmashAutonB;
import org.usfirst.frc.team115.recyclerush.commands.auton.JuggernautA;
import org.usfirst.frc.team115.recyclerush.commands.auton.JuggernautB;
import org.usfirst.frc.team115.recyclerush.commands.auton.MobilityAuton;
import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.IntakeTote;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;
import org.usfirst.frc.team115.recyclerush.subsystems.CompressorSystem;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;
import org.usfirst.frc.team115.recyclerush.subsystems.Roller;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author MVRT
 * This class is equivalent to RobotMain in LabVIEW and runs when the robot is turned on.
 * Note: If you change the class name or package, the manifest must be updated.
 */
public class Robot extends IterativeRobot {
	Command autonCommand;
	SendableChooser autonChooser;
	
    public static DriveTrain drive;
    public static Stabilizer stabilizer;
    public static Claw claw;
    public static OI oi;
    public static Roller roller;
    public static Elevator elevator;
    public static CompressorSystem compressor;

    public Robot() {
        drive = new DriveTrain();
        stabilizer = new Stabilizer();
        claw = new Claw();
        roller  = new Roller();
        compressor = new CompressorSystem();
        elevator = new Elevator();
        oi = new OI();
    }
    
    public void robotInit() {
        SmartDashboard.putData("autoIntake", new AutoIntake());
        SmartDashboard.putData("ToteIntake", new IntakeTote());
    	drive.initialize();
    	elevator.initialize();
    	autonChooser = new SendableChooser();
    	autonChooser.addDefault("Mobility", new MobilityAuton());
    	autonChooser.addObject("Hulk Smash start left", new HulkSmashAutonA());
    	autonChooser.addObject("Hulk Smash start right", new HulkSmashAutonB());
    	autonChooser.addObject("Juggernaut start left", new JuggernautA());
    	autonChooser.addObject("Juggernaut start right", new JuggernautB());
    	SmartDashboard.putData("Auton Mode Chooser", autonChooser);

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	autonCommand = (Command) autonChooser.getSelected();
    	autonCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    }

    public void disabledInit() {
    	Robot.elevator.brake();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    	roller.log();
    	elevator.log();
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
}
