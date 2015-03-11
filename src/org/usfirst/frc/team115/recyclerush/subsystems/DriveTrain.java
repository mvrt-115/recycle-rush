package org.usfirst.frc.team115.recyclerush.subsystems;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SerialPort.Port;
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

    private static final double ANALOG_SCALE_3_3V = 0.00644;

    private final int BACK_LEFT = 0;
    private final int BACK_RIGHT = 1;
    private final int FRONT_LEFT = 2;
    private final int FRONT_RIGHT = 3;

    private CANTalon motors[];

    private IMUAdvanced navX;
    private RobotDrive drive;

    /**
     * Initializes each other motors based on ports set in RobotMap
     */
    public void initialize() {
        navX = new IMUAdvanced(new SerialPort(57600, Port.kMXP));

        motors = new CANTalon[4];
        motors[BACK_LEFT] = new CANTalon(RobotMap.BACK_LEFT_DRIVE);
        motors[BACK_RIGHT] = new CANTalon(RobotMap.BACK_RIGHT_DRIVE);
        motors[FRONT_LEFT] = new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
        motors[FRONT_RIGHT] = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
        drive = new RobotDrive(motors[FRONT_LEFT], motors[BACK_LEFT],
                motors[FRONT_RIGHT], motors[BACK_RIGHT]);

        for (CANTalon motor : motors)
            motor.setVoltageRampRate(24);
    }

    /**
     * This thing drives the robot!
     *
     * @param move   the forward speed of the rotation
     * @param rotate the rotation value of the robot
     */
    public void drive(double move, double rotate) {
        drive.arcadeDrive(-1 * move, rotate); // invert drive direction
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
     * Stops the drivetrain
     */
    public void stop() {
        System.out.println("Stopping Drive");
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
    public void log() {
        for (int it = 0; it < motors.length; it++) {
            CANTalon motor = motors[it];
            SmartDashboard.putNumber("Drivetrain Motor " + it + " Current", motor.getOutputCurrent());
        }

        SmartDashboard.putNumber("Yaw", navX.getYaw());
        SmartDashboard.putNumber("Pitch", navX.getPitch());
        SmartDashboard.putNumber("Roll", navX.getRoll());
        SmartDashboard.putNumber("Position X", navX.getWorldLinearAccelX());
        SmartDashboard.putNumber("Position Y", navX.getWorldLinearAccelY());
        SmartDashboard.putNumber("Position Z", navX.getWorldLinearAccelZ());
    }

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
        resetEncoders();
    }

    public void resetEncoders() {
        for(CANTalon motor : motors)
            motor.setPosition(0);
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
