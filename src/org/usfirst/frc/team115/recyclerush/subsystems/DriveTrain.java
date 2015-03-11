package org.usfirst.frc.team115.recyclerush.subsystems;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;

/**
 * A subsystem representing the drive train for the robot
 * @author MVRT
 */
public class DriveTrain extends Subsystem {

    private static final int BACK_LEFT = 0;
    private static final int BACK_RIGHT = 1;
    private static final int FRONT_LEFT = 2;
    private static final int FRONT_RIGHT = 3;
    private static final int RATE_VOLTAGE_RAMP = 24;
    private static final double ANALOG_SCALE_3_3V = 0.00644;

    private RobotDrive drive;    
    private CANTalon motors[];
    
    
    private AnalogInput ultrasonicFront;
    private AnalogInput ultrasonicBack;
    private AnalogInput ultrasonicLeft;
    private AnalogInput ultrasonicRight;
    private IMUAdvanced navX;

    /**
     * Initializes each other motors based on ports set in RobotMap
     */
    public DriveTrain() {
        navX = new IMUAdvanced(new SerialPort(57600, Port.kMXP));

        /*ultrasonicFront = new AnalogInput(RobotMap.ULTRASONIC_FRONT);
        ultrasonicBack = new AnalogInput(RobotMap.ULTRASONIC_BACK);
        ultrasonicLeft = new AnalogInput(RobotMap.ULTRASONIC_LEFT);
        ultrasonicRight = new AnalogInput(RobotMap.ULTRASONIC_RIGHT);*/
        
        motors = new CANTalon[4];
        motors[BACK_LEFT] = new CANTalon(RobotMap.BACK_LEFT_DRIVE);
        motors[BACK_RIGHT] = new CANTalon(RobotMap.BACK_RIGHT_DRIVE);
        motors[FRONT_LEFT] = new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
        motors[FRONT_RIGHT] = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
        drive = new RobotDrive(motors[FRONT_LEFT], motors[BACK_LEFT],
                motors[FRONT_RIGHT], motors[BACK_RIGHT]);
    }
    
    public void initialize() {
    	for(CANTalon motor : motors){
    		motor.setVoltageRampRate(RATE_VOLTAGE_RAMP);
    	}
    	zeroEncoders();
    }

    /**
     * Initializes the default command of the subsystem.
     */
    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ArcadeDriveWithJoystick());
    }
    
    /**
     * Resets the navx and any encoders
     */
    public void resetAll() {
        navX.zeroYaw();
        zeroEncoders();
    }
    
    
    /**
     * Drives the robot
     * @param joystick The joystick to drive based on
     */
    public void drive(Joystick joystick) {
        drive.arcadeDrive(joystick);
    }
    
    /**
     * This thing drives the robot!
     * @param move   the forward speed of the rotation
     * @param rotate the rotation value of the robot
     */
    public void drive(double move, double rotate) {
        drive.arcadeDrive(-1 * move, rotate);
    }

    /**
     * Stops the drivetrain
     */
    public void stop() {
        drive(0, 0);
    }
    
    /**
     * Zeroes the robot's encoders
     */
    public void zeroEncoders(){
        for(CANTalon motor: motors){
            motor.setPosition(0); //zero the encoders
        }
    }
    
    public double getDistance(){
        return (motors[FRONT_LEFT].getPosition() + motors[FRONT_RIGHT].getPosition())/2;
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
        return (navX.getYaw() + 360)%360;
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
