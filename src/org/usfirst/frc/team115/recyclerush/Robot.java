package org.usfirst.frc.team115.recyclerush;


import org.usfirst.frc.team115.recyclerush.commands.ElevatorBrakeOff;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;
import org.usfirst.frc.team115.recyclerush.commands.auton.JuggernautA;
import org.usfirst.frc.team115.recyclerush.commands.auton.JuggernautB;
import org.usfirst.frc.team115.recyclerush.commands.auton.JuggernautB0;
import org.usfirst.frc.team115.recyclerush.commands.auton.MobilityAuton;
import org.usfirst.frc.team115.recyclerush.commands.auton.SingleToteAuton;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;
import org.usfirst.frc.team115.recyclerush.subsystems.CompressorSystem;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;
import org.usfirst.frc.team115.recyclerush.subsystems.Roller;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * @author MVRT
 *         This class is equivalent to RobotMain in LabVIEW and runs when the robot is turned on.
 *         Note: If you change the class name or package, the manifest must be updated.
 */
public class Robot extends IterativeRobot {
	private Command autonCommand;
	private SendableChooser autonChooser;

    public static DriveTrain drive;
    public static Stabilizer stabilizer;
    public static Claw claw;
    public static OI oi;
    public static Roller roller;
    public static Elevator elevator;
    public static CompressorSystem compressor;

    public Robot() {
        drive = new DriveTrain();
        // stabilizer = new Stabilizer();
        claw = new Claw();
        roller = new Roller();
        compressor = new CompressorSystem();
        elevator = new Elevator();
        oi = new OI();
    }

    @Override
    public void robotInit() {
        drive.initialize();
        // stabilizer.initialize();
        claw.initialize();
        roller.initialize();
        compressor.initialize();
        elevator.initialize();
        oi.initialize();
        initAutonChooser();

        initCommands();
    }

    public void initCommands() {
        SmartDashboard.putData(new ElevatorBrakeOff());
        SmartDashboard.putData(new ElevatorHardReset());
    }

    public void initAutonChooser() {
        autonChooser = new SendableChooser();
        autonChooser.addDefault("Nothing", null);
        autonChooser.addObject("Single Tote", new SingleToteAuton());
        autonChooser.addObject("Mobility", new MobilityAuton());
        autonChooser.addObject("Juggernaut start left", new JuggernautA());
        autonChooser.addObject("Juggernaut start right", new JuggernautB());
        autonChooser.addObject("Juggernaut start right w/o PID", new JuggernautB0());
       
        SmartDashboard.putData("Auton Mode Chooser", autonChooser);
    }

    @Override
    public void disabledPeriodic() {
    	Scheduler.getInstance().run();
        log();
    }

    @Override
    public void autonomousInit() {
        autonCommand = (Command) autonChooser.getSelected();
        new ElevatorHardReset().start();
        if(autonCommand != null)autonCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    @Override
    public void teleopInit() {
    	Robot.drive.zeroEncoders();
    }

    @Override
    public void disabledInit() {
        Robot.elevator.brake();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
        log();
    }

    public void log() {
        roller.log();
        elevator.log();
        // stabilizer.log();
        compressor.log();
        drive.log();
        claw.log();
    }
}
