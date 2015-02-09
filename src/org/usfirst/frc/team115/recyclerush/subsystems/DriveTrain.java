/**
 * This is the drive train for the robot for the competition.
 *
 * @author MVRT
 */

package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * A subsystem representing the drive train for the robot
 * @author MVRT
 */
public class DriveTrain extends Subsystem {
	
	public enum DriveMode{
		UserControl,
		CommandControl
	}

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

	private CANTalon motors[];

	private double scaleFactor = 0.25;
	
	private DriveMode dm;
	
	private double limited_speed = 0.0;
	private double speed_change_limit = 0.0001;
	private double limited_angle = 0.0;
	private double angle_change_limit = 0.05;
	
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
        dm = DriveMode.UserControl;
    }
    
    public void initialize() {
    	for (CANTalon motor : motors) {
        	motor.enableControl();
   //     	motor.setVoltageRampRate(24);
        } 
    }
    
    
    /**
     * Limit the change in speed
     * @param output The desired speed to limit
     */
    public void ramping(double output) {  
    	//speed_change_limit = SmartDashboard.getNumber("Speed Change Limit");
    	double change = output - limited_speed;
    	if (change > speed_change_limit)
    		change = speed_change_limit;
    	else if (change < -speed_change_limit)
    		change = -speed_change_limit;
    	limited_speed += change;
	}
    
    /** 
     * Limits the rotation speed
     * @param output The desired rotation angle to limit
     */
    public void limitRotation(double output) {
    	double change = output - limited_angle;
    	if (change > angle_change_limit)
    		change = angle_change_limit;
    	else if (change < -angle_change_limit)
    		change = -angle_change_limit;
    	limited_angle += change;
    }

    public DriveMode getControlMode(){
    	return dm;
    }
    
    public void setControlMode(DriveMode mode){
    	dm = mode;
    }
    /**
     * This thing drives the robot!
     * @param move   the forward speed of the rotation
     * @param rotate the rotation value of the robot
     */
    public void drive(double move, double rotate) {
    	ramping(move);
    	limitRotation(rotate);
        drive.arcadeDrive(limited_speed, rotate);
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
	 * This returns the current.
	 * @return 		the current
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
	
	public void enableControl() {
		for (CANTalon motor : motors)
			motor.enableControl();
	}
	
	public void setMode(CANTalon.ControlMode mode) {
		for (CANTalon motor : motors)
			motor.changeControlMode(mode);		
	}
	
	public CANTalon.ControlMode getMode() {
		return motors[0].getControlMode();
	}
	
	public void setPosition(double position) {
		for (CANTalon motor : motors)
			motor.set(position);
	}
	
	public double getDistance() {
		return motors[0].getPosition()*scaleFactor;
	}
	
	public double getSpeed() {
		double total = 0.0;
		for (CANTalon motor : motors)
			total += motor.get();
		return total / motors.length;
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
