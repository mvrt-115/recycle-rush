package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.DriveDistance;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightDistanceNoPID;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorBrakeOff;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;
import org.usfirst.frc.team115.recyclerush.commands.ResetElevatorEncoder;
import org.usfirst.frc.team115.recyclerush.commands.Turn;
import org.usfirst.frc.team115.recyclerush.commands.auton.Alliance;
import org.usfirst.frc.team115.recyclerush.commands.auton.AutonGroup;
import org.usfirst.frc.team115.recyclerush.commands.auton.CanDriveDrive;
import org.usfirst.frc.team115.recyclerush.commands.auton.CanPickup;
import org.usfirst.frc.team115.recyclerush.commands.auton.DeadCanMove;
import org.usfirst.frc.team115.recyclerush.commands.auton.DriveDrive;
import org.usfirst.frc.team115.recyclerush.commands.auton.JuggernautA;
import org.usfirst.frc.team115.recyclerush.commands.auton.JuggernautB;
import org.usfirst.frc.team115.recyclerush.commands.auton.JuggernautB0;
import org.usfirst.frc.team115.recyclerush.commands.auton.MobilityAuton;
import org.usfirst.frc.team115.recyclerush.commands.auton.SingleToteAuton;
import org.usfirst.frc.team115.recyclerush.subsystems.CameraSystem;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;
import org.usfirst.frc.team115.recyclerush.subsystems.CompressorSystem;
import org.usfirst.frc.team115.recyclerush.subsystems.DriveTrain;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;
import org.usfirst.frc.team115.recyclerush.subsystems.Roller;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
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
	
	public static Preferences prefs;
    public static DriveTrain drive;
    public static Stabilizer stabilizer;
    public static Claw claw;
    public static OI oi;
    public static Roller roller;
    public static Elevator elevator;
    public static CompressorSystem compressor;
    public static CameraSystem camera;

    private static double TurnP = 0.1;
    private static double TurnI = 0;
    private static double TurnD = 0;
    public Robot() {
        drive = new DriveTrain();
        stabilizer = new Stabilizer();
        claw = new Claw();
        roller = new Roller();
        compressor = new CompressorSystem();
        elevator = new Elevator();
        //camera = new CameraSystem();//
        oi = new OI();
    }

    @Override
    public void robotInit() {
        drive.initialize();
        stabilizer.initialize();
        claw.initialize();
        roller.initialize();
        compressor.initialize();
        //elevator.initialize();
        //camera.initialize();
        oi.initialize();
        initAutonChooser();
        initCommands();
    }

    public void initCommands() {
    	SmartDashboard.putData(new Turn(90, TurnP, TurnI, TurnD));
        //SmartDashboard.putData(new ElevatorBrakeOff());
        //SmartDashboard.putData(new ElevatorHardReset());
        SmartDashboard.putData(new DriveStraightDistanceNoPID(3));
        SmartDashboard.putData(new CanDriveDrive());
        SmartDashboard.putData(new DriveDrive());
    }

    public void initAutonChooser() {
        autonChooser = new SendableChooser();
        autonChooser.addDefault("Nothing", null);
        autonChooser.addObject("Can Drive Drop Drive", new CanDriveDrive());
        autonChooser.addObject("Drive.Drive", new DriveDrive());
        autonChooser.addObject("Single Tote", new SingleToteAuton());
        autonChooser.addObject("Drive Distance", new DriveDistance(7));
        
        autonChooser.addObject("Mobility", new MobilityAuton());
        autonChooser.addObject("DRIVESTEP", new DriveStraightDistanceNoPID(8));
        autonChooser.addObject("Can Hold", new CanPickup());
        autonChooser.addObject("Juggernaut start left", new JuggernautA());
        autonChooser.addObject("Juggernaut start right", new JuggernautB());
        autonChooser.addObject("Juggernaut start right w/o PID", new JuggernautB0());
        autonChooser.addObject("Can Dead Move", new DeadCanMove());
        autonChooser.addObject("Alliance", new Alliance());
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
        if(autonCommand != null)new AutonGroup(autonCommand).start();
        else new ElevatorHardReset().start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    @Override
    public void teleopInit() {
    	Robot.drive.zeroEncoders();
    	TurnP = prefs.getDouble("Turn P", 0.1);
    	TurnI = prefs.getDouble("Turn I", 0);
    	TurnD = prefs.getDouble("Turn D", 0);
    	SmartDashboard.putNumber("Drive Distance", Robot.drive.getDistance());
    }

    @Override
    public void disabledInit() {
        //Robot.elevator.brake();
    }

    @Override
    public void teleopPeriodic() {
    	TurnP = prefs.getDouble("Turn P", 0.1);
    	TurnI = prefs.getDouble("Turn I", 0);
    	TurnD = prefs.getDouble("Turn D", 0);
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
        compressor.log();
        drive.log();
        claw.log();
        //camera.log();
    }
}
