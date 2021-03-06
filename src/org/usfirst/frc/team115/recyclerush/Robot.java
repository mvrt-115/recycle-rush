
package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.auton.AllianceAuton;
import org.usfirst.frc.team115.recyclerush.auton.AutonGroup;
import org.usfirst.frc.team115.recyclerush.auton.JuggernautA;
import org.usfirst.frc.team115.recyclerush.auton.LeftCanAuton;
import org.usfirst.frc.team115.recyclerush.auton.Mobility;
import org.usfirst.frc.team115.recyclerush.auton.RightCanAuton;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;
import org.usfirst.frc.team115.recyclerush.commands.led.FadePulse;
import org.usfirst.frc.team115.recyclerush.commands.led.SetColor;
import org.usfirst.frc.team115.recyclerush.subsystems.Claw;
import org.usfirst.frc.team115.recyclerush.subsystems.CompressorSystem;
import org.usfirst.frc.team115.recyclerush.subsystems.Drive;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;
import org.usfirst.frc.team115.recyclerush.subsystems.Intake;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDStrip;
import org.usfirst.frc.team115.recyclerush.subsystems.Roller;
import org.usfirst.frc.team115.recyclerush.subsystems.Stabilizer;

import com.kauailabs.navx_mxp.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * @author Lee Mracek
 */
public class Robot extends IterativeRobot {

    public static OI oi;
    public static Drive drive;
    public static Roller roller;
    public static Intake intake;
    public static CompressorSystem compressor;
    public static Elevator elevator;
    public static Claw claw;
    public static Stabilizer stabilizer;
    public static LEDStrip ledStripPrimary;

    public static AHRS navx;

    private SendableChooser autonChooser;

    private Command autonomousCommand;
    private CommandGroup autonGroup;

    private static final int AUTON_ALLIANCE = 1;
    private static final int AUTON_NULL = 0;
    private static final int AUTON_MOBILITY = 2;
    private static final int AUTON_RIGHT_CAN = 3;
    private static final int AUTON_LEFT_CAN = 4;
    private static final int AUTON_JUGGER = 6;

    private boolean firstIteration;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        oi = new OI();
        drive = new Drive(oi.getDriveJoystick());
        roller = new Roller();
        intake = new Intake();
        compressor = new CompressorSystem();
        elevator = new Elevator();
        elevator.initResetTrigger();
        claw = new Claw();
        stabilizer = new Stabilizer();
        ledStripPrimary = new LEDStrip(5803, "10.1.15.16");

        navx = new AHRS(new SerialPort(57600, Port.kMXP));
        firstIteration = true;

        oi.initXbox();

        initAutonChooser();
    }

    private void initAutonChooser() {
        autonChooser = new SendableChooser();
        autonChooser.addDefault("Alliance", AUTON_ALLIANCE);
        autonChooser.addObject("Mobility", AUTON_MOBILITY);
        autonChooser.addObject("Juggernaut", AUTON_JUGGER);
        autonChooser.addObject("Right Can", AUTON_RIGHT_CAN);
        autonChooser.addObject("Left Can", AUTON_LEFT_CAN);
        autonChooser.addObject("Null", AUTON_NULL);
        SmartDashboard.putData("Autonomous Selector", autonChooser);
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();

        // Resets the navx yaw after it is done calibrating (theoretically)
        if(firstIteration && !navx.isCalibrating()) {
            Timer.delay(0.3);
            navx.zeroYaw();
            firstIteration = false;
        }

        log();
    }

    @Override
    public void autonomousInit() {
        new FadePulse(LEDStrip.PURPLE, LEDStrip.GOLD, (short) 2000).start();
        // schedule the autonomous command (example)
        int autonomousCommandIndex = (int) autonChooser.getSelected();
        switch(autonomousCommandIndex) {
        case AUTON_NULL:
            autonomousCommand = null;
            break;
        case AUTON_ALLIANCE:
            autonomousCommand = new AllianceAuton();
            break;
        case AUTON_MOBILITY:
            autonomousCommand = new Mobility();
            break;
        case AUTON_JUGGER:
            autonomousCommand = new JuggernautA();
            break;
        case AUTON_LEFT_CAN:
            autonomousCommand = new LeftCanAuton();
            break;
        case AUTON_RIGHT_CAN:
            autonomousCommand = new RightCanAuton();
            break;
        default:
            System.err.println("Autonomous Command not indexed");
        }
        if (autonomousCommand != null) {
            autonGroup = new AutonGroup(autonomousCommand);
            autonGroup.start();
        } else {
            new ElevatorHardReset().start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    @Override
    public void teleopInit() {
        if (DriverStation.getInstance().getAlliance().equals(Alliance.Blue)) {
            new SetColor(LEDStrip.BLUE).start();
        } else {
            new SetColor(LEDStrip.RED).start();
        }
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) {
            autonGroup.cancel();
        }
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){
        new FadePulse(LEDStrip.PURPLE, LEDStrip.GOLD, (short) 5000).start();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        log();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
        log();
    }

    public void log() {
        compressor.log();
        drive.log();
        elevator.log();
        intake.log();
        roller.log();

        logNavX();
    }

    public void logNavX() {
        SmartDashboard.putBoolean("NavX Connected", navx.isConnected());
        SmartDashboard.putBoolean("NavX IsCalibrating", navx.isCalibrating());
        SmartDashboard.putBoolean("NavX IsMoving", navx.isMoving());
        SmartDashboard.putBoolean("NavX IsRotating", navx.isRotating());
        SmartDashboard.putNumber("NavX Yaw", navx.getYaw());
        SmartDashboard.putNumber("NavX Pitch", navx.getPitch());
        SmartDashboard.putNumber("NavX Roll", navx.getRoll());
        SmartDashboard.putNumber("NavX CompassHeading",
                navx.getCompassHeading());

        SmartDashboard.putNumber("NavX Accel_X", navx.getWorldLinearAccelX());
        SmartDashboard.putNumber("NavX Accel_Y", navx.getWorldLinearAccelY());
        SmartDashboard.putBoolean("NavX IsMoving", navx.isMoving());
        SmartDashboard.putNumber("NavX Temp_C", navx.getTempC());

        SmartDashboard.putNumber("Velocity_X", navx.getVelocityX());
        SmartDashboard.putNumber("Velocity_Y", navx.getVelocityY());
        SmartDashboard.putNumber("Displacement_X", navx.getDisplacementX());
        SmartDashboard.putNumber("Displacement_Y", navx.getDisplacementY());

    }
}
