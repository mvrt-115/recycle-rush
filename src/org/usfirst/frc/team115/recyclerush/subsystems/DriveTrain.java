package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;
import org.usfirst.frc.team115.recyclerush.commands.TankDriveWithJoysticks;
import org.usfirst.frc.team115.recyclerush.commands.UpdateDriveType;
import org.usfirst.frc.team115.recyclerush.triggers.DriveTrigger;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A subsystem representing the drive train for the robot
 * @author MVRT
 */
public class DriveTrain extends Subsystem {

	private RobotDrive drive;
    private final int BACK_LEFT = 0;
    private final int BACK_RIGHT = 1;
    private final int FRONT_LEFT = 2;
    private final int FRONT_RIGHT = 3;
    private IMUAdvanced navX;

    private AnalogInput ultrasonicFront;
    private AnalogInput ultrasonicBack;
    private AnalogInput ultrasonicLeft;
    private AnalogInput ultrasonicRight;
    private static final double ANALOG_SCALE_3_3V = 0.00644;

    private SendableChooser chooser;


    private CANTalon motors[];

    /**
     * Initializes each other motors based on ports set in RobotMap
     */
    public DriveTrain() {
        navX = new IMUAdvanced(new SerialPort(57600, Port.kMXP));

        ultrasonicFront = new AnalogInput(RobotMap.ULTRASONIC_FRONT);
        ultrasonicBack = new AnalogInput(RobotMap.ULTRASONIC_BACK);
        ultrasonicLeft = new AnalogInput(RobotMap.ULTRASONIC_LEFT);
        ultrasonicRight = new AnalogInput(RobotMap.ULTRASONIC_RIGHT);

        motors = new CANTalon[4];
        motors[BACK_LEFT] = new CANTalon(RobotMap.BACK_LEFT_DRIVE);
        motors[BACK_RIGHT] = new CANTalon(RobotMap.BACK_RIGHT_DRIVE);
        motors[FRONT_LEFT] = new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
        motors[FRONT_RIGHT] = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
        drive = new RobotDrive(motors[FRONT_LEFT], motors[BACK_LEFT],
                motors[FRONT_RIGHT], motors[BACK_RIGHT]);
    	chooser = new SendableChooser();
    }
    
    public void initialize() {
        chooser.addDefault("Arcade Drive", new ArcadeDriveWithJoystick());
        chooser.addObject("Tank Drive", new TankDriveWithJoysticks());
        
    	SmartDashboard.putData("Drive Selector", chooser);
        
        new DriveTrigger().whenActive(new UpdateDriveType());
    }
    
    public void log() {
    	SmartDashboard.putData("Drive Selector", chooser);
    }

    public void resetDriveType() {
        this.getCurrentCommand().cancel();
        this.setDefaultCommand((Command) chooser.getSelected());
        this.getDefaultCommand().start();
    }

    public Command getSelector() {
        return (Command) chooser.getSelected();
    }

    /**
     * This thing drives the robot!
     * @param move   the forward speed of the rotation
     * @param rotate the rotation value of the robot
     */
    public void drive(double move, double rotate) {
        drive.arcadeDrive(move, rotate);
    }

    /**
     * This function drives the robot tank style
     *
     * @param joystickLeft the left joystick
     * @param joystickRight the right joystick
     */
    public void tankDrive(Joystick joystickLeft, Joystick joystickRight) {
        drive.tankDrive(joystickLeft, joystickRight);
    }

    /**
     * Drives the robot
     * @param joystick The joystick to drive based on
     */
    public void drive(Joystick joystick) {
        drive.arcadeDrive(joystick);
    }

    /**
     * Stops the drivetrain
     */
    public void stop() {
        drive(0, 0);
    }

    /**
     * Initializes the default command of the subsystem.
     */
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDriveWithJoystick());
    }

    /**
     * @return the total current being sent to motors
     */
    public double getCurrent() {
        double current = 0;
        for (CANTalon motor : motors)
            current += motor.getOutputCurrent();
        return current;
    }

    /**
     * @return the angle of rotational displacement
     */
    public float getYaw() {
        return navX.getYaw();
    }

    /**
     * @return the angle of tilt along the horizontal plane
     */
    public float getPitch() {
        return navX.getPitch();
    }

    /**
     * @return the angle of tilt along the vertical plane
     */
    public float getRoll() {
        return navX.getRoll();
    }

    /**
     * Resets the navx and any encoders
     */
    public void resetAll() {
        navX.zeroYaw();
        //TODO: encoder reset goes here
    }

    /**
     * @return the displacement along x axis
     */
    public float getX() {
        return navX.getWorldLinearAccelX();
    }

    /**
     * @return the displacement along x axis
     */
    public float getY() {
        return navX.getWorldLinearAccelY();
    }

    /**
     * @return the displacement along x axis
     */
    public float getZ() {
        return navX.getWorldLinearAccelZ();
    }

    /**
     * @return the distance from front
     */
    public double getFrontUltrasonicInches(){
        return ultrasonicFront.getVoltage()/ANALOG_SCALE_3_3V;
    }

    /**
     * @return the distance from back 
     */
    public double getBackUltrasonicInches(){
        return ultrasonicBack.getVoltage()/ANALOG_SCALE_3_3V;
    }

    /**
     * @return the distance from left
     */
    public double getLeftUltrasonicInches(){
        return ultrasonicLeft.getVoltage()/ANALOG_SCALE_3_3V;
    }

    /**
     * @return the distance from right
     */
    public double getRightUltrasonicInches(){
        return ultrasonicRight.getVoltage()/ANALOG_SCALE_3_3V;
    }



}
