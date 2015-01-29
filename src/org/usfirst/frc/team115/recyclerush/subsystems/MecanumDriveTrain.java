package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.MecanumDrive;
import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.MecanumDriveWithJoystick;

import com.kauailabs.nav6.frc.IMUAdvanced;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MecanumDriveTrain extends Subsystem{
	
	private MecanumDrive drive;
	private final IMUAdvanced navX;
	
	private CANTalon frontLeft = new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
	private CANTalon rearLeft = new CANTalon(RobotMap.BACK_LEFT_DRIVE);
	private CANTalon frontRight = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
	private CANTalon rearRight = new CANTalon(RobotMap.BACK_RIGHT_DRIVE);
	
	public MecanumDriveTrain() {
		super();
		drive = new MecanumDrive(frontLeft, rearLeft, frontRight, rearRight);
		navX = new IMUAdvanced(new SerialPort(57600, Port.kMXP));
		resetAll();
	}
	
	@Override
	protected void initDefaultCommand() { 
		setDefaultCommand(new MecanumDriveWithJoystick());
	}
	
	/**
	 * Stops the robot
	 */
	public void stop(){
		drive.setLeftRightMotorOutputs(0, 0);
	}
	
	/**
	 * Drives the robot
	 * @param joystick The joystick to drive based on
	 */
	public void drive(Joystick joystick) {
		double gyroTheta = getYaw();
		drive.mecanumCartesian(Robot.oi.getJoystick(), gyroTheta);
	}
	
	/**
     * Returns the angle of rotational displacement
     *
     * @return the current yaw of the gyro
     */
    public float getYaw() {
        return navX.getYaw();
    }

    /**
     * Returns the angle of tilt along the horizontal plane
     *
     * @return the gyro's pitch
     */
    public float getPitch() {
        return navX.getPitch();
    }

    /**
     * Returns the angle of tilt along the vertical plane
     *
     * @return the gyro's roll
     */
    public float getRoll() {
        return navX.getRoll();
    }

    /**
     * Resets the navx and any encoders
     */
    public void resetAll() {
        navX.zeroYaw();
        //encoder reset goes here
    }
}
