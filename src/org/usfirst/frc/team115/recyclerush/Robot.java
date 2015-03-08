package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.ElevatorBrakeOff;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorStop;
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
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author MVRT
 * This class is equivalent to RobotMain in LabVIEW and runs when the robot is turned on.
 * Note: If you change the class name or package, the manifest must be updated.
 */
public class Robot extends IterativeRobot {

    public static DriveTrain drive;
    public static Stabilizer stabilizer;
    public static Claw claw;
    public static OI oi;
    public static Roller roller;
    public static Elevator elevator;
    public static CompressorSystem compressor;

    public Robot()
    {
        drive = new DriveTrain();
        stabilizer = new Stabilizer();
        claw = new Claw();
        roller  = new Roller();
        compressor = new CompressorSystem();
        elevator = new Elevator();
        oi = new OI();
    }
    int session;
    Image frame;
    public void cameraServerDisplay()
    {
            NIVision.IMAQdxGrab(session, frame, 1);
            CameraServer.getInstance().setImage(frame);
            Timer.delay(0.005);
    }
    public void robotInit()
    {
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
        NIVision.IMAQdxStartAcquisition(session);
    	drive.initialize();
    	elevator.initialize();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        cameraServerDisplay();
    }

    public void autonomousInit() {
        NIVision.IMAQdxStopAcquisition(session);
        NIVision.IMAQdxStartAcquisition(session);
    }

    public void autonomousPeriodic() {
        cameraServerDisplay();
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        NIVision.IMAQdxStopAcquisition(session);
        NIVision.IMAQdxStartAcquisition(session);
    }

    public void disabledInit() {
    	Robot.elevator.brake();
    }

    public void teleopPeriodic() {
        cameraServerDisplay();
        Scheduler.getInstance().run();
    	roller.log();
    	elevator.log();
    }

    public void testPeriodic() {
        cameraServerDisplay();
        LiveWindow.run();
       
    }
}
