package org.usfirst.frc.team115.recyclerush.subsystems;

import com.kauailabs.nav6.frc.IMUAdvanced;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;

/**
 * A subsystem representing the drive train for the robot
 *
 * @author MVRT
 */
public class DriveTrain extends Subsystem {


    private static final int BACK_LEFT = 0;
    private static final int BACK_RIGHT = 1;
    private static final int FRONT_LEFT = 2;
    private static final int FRONT_RIGHT = 3;
    private static final int RATE_VOLTAGE_RAMP = 24;
    private static final double ANALOG_SCALE_3_3V = 0.00644;

    private CANTalon motors[];

    private IMUAdvanced navX;
    private RobotDrive drive;

    /**
     * Initializes each other motors based on ports set in RobotMap
     */
    public void initialize() {
        navX = new IMUAdvanced(new SerialPort(57600, SerialPort.Port.kMXP));

        motors = new CANTalon[4];
        motors[BACK_LEFT] = new CANTalon(RobotMap.BACK_LEFT_DRIVE);
        motors[BACK_RIGHT] = new CANTalon(RobotMap.BACK_RIGHT_DRIVE);
        motors[FRONT_LEFT] = new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
        motors[FRONT_RIGHT] = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
        drive = new RobotDrive(motors[FRONT_LEFT], motors[BACK_LEFT],
                motors[FRONT_RIGHT], motors[BACK_RIGHT]);

        for (CANTalon motor : motors)
            motor.setVoltageRampRate(24);

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
     *
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
        System.out.println("Stopping Drive");
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
        return (motors[FRONT_LEFT].getPosition() + -1 * motors[FRONT_RIGHT].getPosition())/2;
    }
    
    /**
     * @return the total current being sent to motors
     */
    public void log() {
        for (int it = 0; it < motors.length; it++) {
            CANTalon motor = motors[it];
            SmartDashboard.putNumber("Drivetrain Motor " + it + " Current", motor.getOutputCurrent());
        }

        SmartDashboard.putNumber("Yaw", getYaw());
        SmartDashboard.putNumber("Pitch", getPitch());
        SmartDashboard.putNumber("Roll", getRoll());
        SmartDashboard.putNumber("Position X", navX.getWorldLinearAccelX());
        SmartDashboard.putNumber("Position Y", navX.getWorldLinearAccelY());
        SmartDashboard.putNumber("Position Z", navX.getWorldLinearAccelZ());

        SmartDashboard.putNumber("Drive Distance", getDistance());
    }

    public double getCurrent() {
        double current = 0;
        for (CANTalon motor : motors)
            current += motor.getOutputCurrent();
        return current;
    }
    
    public void setControlMode(CANTalon.ControlMode mode){
   	 for(CANTalon motor : motors)
            motor.changeControlMode(mode);
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
}
