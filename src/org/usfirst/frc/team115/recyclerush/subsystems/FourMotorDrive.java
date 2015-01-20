package org.usfirst.frc.team115.recyclerush.subsystems;

/**
 * This is the drive train for the robot for the competition.
 * 
 * @author Heather Baker
 */

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class FourMotorDrive extends VBusMotorSystem {

	private RobotDrive drive;
	private final int BACK_LEFT = 0;
	private final int BACK_RIGHT = 1;
	private final int FRONT_LEFT = 2;
	private final int FRONT_RIGHT = 3;
	private final double MAX_SPEED = 1;

	private CANTalon motors[];

	/**
	 * Initializes each other motors based on ports set in RobotMap
	 */
	public FourMotorDrive() {
		motors = new CANTalon[4];
		motors[BACK_LEFT] = new CANTalon(RobotMap.BACK_LEFT_DRIVE);
		motors[BACK_RIGHT] = new CANTalon(RobotMap.BACK_RIGHT_DRIVE);
		motors[FRONT_LEFT] = new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
		motors[FRONT_RIGHT] = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
		drive = new RobotDrive(motors[FRONT_LEFT], motors[BACK_LEFT],
				motors[FRONT_RIGHT], motors[BACK_RIGHT]);
	}

	/**
	 * This thing drives the robot!
	 * @param left    The left speed
	 * @param right	  The right speed
	 */
	public void drive(double left, double right) {
		left = Math.min(left, MAX_SPEED);
		right = Math.min(right, MAX_SPEED);
		drive.setLeftRightMotorOutputs(left, right);
	}

	/**
	 * Drives the robot
	 * @param joystick The joystick to drive based on
	 */
	public void drive(Joystick joystick) {
		drive.arcadeDrive(joystick);
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

}
