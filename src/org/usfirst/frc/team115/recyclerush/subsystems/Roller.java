package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import org.usfirst.frc.team115.recyclerush.commands.RollerControl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
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
	
	/**
	 * Initializes the roller
	 */
	public Roller() {
		leftMotor = new CANTalon(RobotMap.ROLLER_MOTOR_LEFT);
		rightMotor = new CANTalon(RobotMap.ROLLER_MOTOR_RIGHT);
		rollerSolenoid = new DoubleSolenoid(RobotMap.ROLLER_SOLENOID_1, RobotMap.ROLLER_SOLENOID_2);
		intakeLimitSwitchRight = new DigitalInput(RobotMap.INTAKE_ROLLER_SWITCH_RIGHT);
		intakeLimitSwitchLeft = new DigitalInput(RobotMap.INTAKE_ROLLER_SWITCH_LEFT);
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
		
		// if abs(y) is greater than abs(x), control in/out instead of rotation
		if(Math.abs(y) > Math.abs(x)){
			leftMotor.set(y);
			rightMotor.set(y);
		} else{
			double fwdSpeed = Math.abs(x);
			double revSpeed = -0.4;
			
			// if x is positive, left is abs(x) and right is -0.4
			// else if x is negative, right is abs(x) and left is -0.4
			leftMotor.set((x > 0) ? fwdSpeed : revSpeed);
			rightMotor.set((x < 0) ? fwdSpeed : revSpeed);
		}
	}
	
	/**
	 * Stops the roller motors
	 */
	public void stop() {
		leftMotor.set(0.0);
		rightMotor.set(0.0);
	}
	
	public boolean getIntakeLimitSwitchLeft() {
		return intakeLimitSwitchLeft.get();
	}
	
	public boolean getIntakeLimitSwitchRight() {
		return intakeLimitSwitchRight.get();
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
