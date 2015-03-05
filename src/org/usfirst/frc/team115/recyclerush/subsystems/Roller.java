package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.RollerControl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Contains the two motors that make up the Robot's roller mechanism
 * @author MVRT
 */
public class Roller extends Subsystem {
	
	private CANTalon leftMotor;
	private CANTalon rightMotor;
	
	private DoubleSolenoid rollerSolenoid;
	
	private DigitalInput intakeLimitSwitchRight;
	private DigitalInput intakeLimitSwitchLeft;
	
	private RobotDrive drive;
	
	/**
	 * Initializes the roller
	 */
	public Roller() {
		leftMotor = new CANTalon(RobotMap.ROLLER_MOTOR_LEFT);
		rightMotor = new CANTalon(RobotMap.ROLLER_MOTOR_RIGHT);
		rollerSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.ROLLER_PORT_A, RobotMap.ROLLER_PORT_B);
		intakeLimitSwitchRight = new DigitalInput(RobotMap.ROLLER_SWITCH_RIGHT);
		intakeLimitSwitchLeft = new DigitalInput(RobotMap.ROLLER_SWITCH_LEFT);
		leftMotor.enableLimitSwitch(true, false);
		rightMotor.enableLimitSwitch(true, false);
		drive = new RobotDrive(leftMotor, rightMotor);
		drive.setInvertedMotor(MotorType.kFrontLeft, true);
		drive.setInvertedMotor(MotorType.kFrontRight, true);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RollerControl());
	}

	/**
	 * Controls the rollers, using 2 joystick axes
	 * @param x: The joystick x-axis (controls rotation)
	 * @param y: The joystick y-axis (controls in/out)
	 */
	
	public void control(double x, double y) {
		drive.arcadeDrive(y * -1, x * -1);
	}
	
	public void control() {
		drive.arcadeDrive(Robot.oi.getXbox(), 5, Robot.oi.getXbox(), 4);
	}
	
	/**
	 * Stops the roller motors
	 */
	public void stop() {
		leftMotor.set(0.0);
		rightMotor.set(0.0);
	}
	
	public boolean getIntakeLimitSwitchLeft() {
		return leftMotor.isFwdLimitSwitchClosed();
	}
	
	public boolean getIntakeLimitSwitchRight() {
		return rightMotor.isFwdLimitSwitchClosed();
	}
	
	/**
	 * Closes the arm
	 */
	public void close() {
		rollerSolenoid.set(Value.kReverse);
	}
	
	/**
	 * Opens the arm
	 */
	public void open() {
		rollerSolenoid.set(Value.kForward);
	}

}
