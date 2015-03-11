package org.usfirst.frc.team115.recyclerush;


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
import org.usfirst.frc.team115.recyclerush.commands.auton.*;
import org.usfirst.frc.team115.recyclerush.subsystems.*;


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

    private Image frame;
    private int session;

    public Robot() {
        drive = new DriveTrain();
        // stabilizer = new Stabilizer();
        claw = new Claw();
        roller = new Roller();
        compressor = new CompressorSystem();
        elevator = new Elevator();
        oi = new OI();
    }

    public void cameraServerDisplay() {
        NIVision.IMAQdxGrab(session, frame, 1);
        CameraServer.getInstance().setImage(frame);
        Timer.delay(0.005);
    }

    @Override
    public void robotInit() {
        initCamera();

        drive.initialize();
        // stabilizer.initialize();
        claw.initialize();
        roller.initialize();
        compressor.initialize();
        elevator.initialize();
        oi.initialize();

        initAutonChooser();
    }

    public void initCamera() {
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        NIVision.IMAQdxStartAcquisition(session);
    }

    public void initAutonChooser() {
        autonChooser = new SendableChooser();
        autonChooser.addDefault("Mobility", new MobilityAuton());
        autonChooser.addObject("Juggernaut start left", new JuggernautA());
        autonChooser.addObject("Juggernaut start right", new JuggernautB());
        SmartDashboard.putData("Auton Mode Chooser", autonChooser);
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        cameraServerDisplay();
        log();
    }

    @Override
    public void autonomousInit() {
        NIVision.IMAQdxStopAcquisition(session);
        NIVision.IMAQdxStartAcquisition(session);

        autonCommand = (Command) autonChooser.getSelected();
        autonCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        cameraServerDisplay();
        log();
    }

    @Override
    public void teleopInit() {
        NIVision.IMAQdxStopAcquisition(session);
        NIVision.IMAQdxStartAcquisition(session);
    }

    @Override
    public void disabledInit() {
        Robot.elevator.brake();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        cameraServerDisplay();
        log();
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
        cameraServerDisplay();
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
