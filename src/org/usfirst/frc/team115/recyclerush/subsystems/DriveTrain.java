/**
 * This is the drive train for the robot for the competition.
 * 
 * @author Heather Baker
 */

package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private RobotDrive drive;
	private final int BACK_LEFT = 0;
	private final int BACK_RIGHT = 1;
	private final int FRONT_LEFT = 2;
	private final int FRONT_RIGHT = 3;

	private CANTalon motors[];

	private double scaleFactor = 0.25;
	
	/**
	 * Initializes each other motors based on ports set in RobotMap
	 */
	public DriveTrain() {
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
	 * @param move	the forward speed of the rotation
	 * @param rotate	the rotation value of the robot
	 */
	public void drive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}

	/**
	 * Drives the robot
	 * @param joystick The joystick to drive based on
	 */
	public void drive(Joystick joystick) {
		drive.arcadeDrive(joystick);
	}

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
