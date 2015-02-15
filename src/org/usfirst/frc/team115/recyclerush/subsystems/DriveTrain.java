/**
 * This is the drive train for the robot for the competition.
 *
 * @author MVRT
 */

package org.usfirst.frc.team115.recyclerush.subsystems;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightWithJoystick;

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

    private final double p = 0.045;
    private final double i = 0.007;
    private final double d = 0.07;

    public static final double DEFAULT_VBUS = 0.7;

    private static final double ANALOG_SCALE_3_3V = 0.00644;

    public static final double DISTANCE_SCALE = 8.0 * Math.PI / 1024.0;

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
    }
    
    public void initialize() {
    	for (CANTalon motor : motors) {
            motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
            motor.changeControlMode(CANTalon.ControlMode.PercentVbus);
            
        motors[1].reverseSensor(true);
        motors[3].reverseSensor(true);
        } 
    }
    
    public void setControlMode(CANTalon.ControlMode mode){
    	 for(CANTalon motor : motors)
             motor.changeControlMode(mode);
    }

    public void setFeedbackDevice(CANTalon.FeedbackDevice device) {
        for(CANTalon motor : motors)
            motor.setFeedbackDevice(device);
    }
    /**
     * This thing drives the robot!
     * @param move   the forward value of the rotation
     * @param rotate the rotation value of the robot
     */
    public void drive(double move, double rotate) {
        drive.arcadeDrive(-1*move, rotate);
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
        for(CANTalon motor : motors) {
        	motor.setPosition(0);
        }
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

    public void disableControl() {
        for (CANTalon motor : motors)
            motor.disableControl();
    }
	
	public void setPosition(double position) {
		for (CANTalon motor : motors)
			motor.set(position);
	}
	
	public double getDistance() {
		return ((motors[0].getPosition() + motors[1].getPosition()
                + motors[2].getPosition() + motors[3].getPosition()) / 4 ) * DISTANCE_SCALE;
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

    public double getD() {
        return d;
    }

    public double getI() {
        return i;
    }

    public double getP() {
        return p;
    }
}
