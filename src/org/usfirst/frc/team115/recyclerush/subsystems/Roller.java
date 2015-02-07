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
 *
 */
public class Roller extends Subsystem {
	
	private CANTalon leftMotor;
	private CANTalon rightMotor;
	
	private DoubleSolenoid rollerSolenoid;
	
	private DigitalInput limitSwitchRight;
	private DigitalInput limitSwitchLeft;
	
	
	/**
	 * Creates the two motors with the specified ports
	 * 
	 */
	public Roller() {
		leftMotor = new CANTalon(RobotMap.ROLLER_MOTOR_LEFT);
		rightMotor = new CANTalon(RobotMap.ROLLER_MOTOR_RIGHT);
		rollerSolenoid = new DoubleSolenoid(RobotMap.ROLLER_SOLENOID_1, RobotMap.ROLLER_SOLENOID_2);
		limitSwitchRight = new DigitalInput(RobotMap.ROLLER_LIMIT_R);
		limitSwitchLeft = new DigitalInput(RobotMap.ROLLER_LIMIT_L);
	}

	public void initialize() {}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RollerControl());
	}

	public void control(double x, double y) {
		if (Math.abs(y) >= Math.abs(x)) {
			if (y >= 0.6) {
				leftMotor.set(Math.min(1.0, y));
				rightMotor.set(Math.min(1.0, y));
			}
			else if (y <= -0.6) {
				leftMotor.set(Math.max(-1.0, y));
				rightMotor.set(Math.max(-1.0, y));
			}
		}
		else {
			if (x >= 0.6) {
				leftMotor.set(Math.min(1.0, x));
				rightMotor.set(0.5);
			}
			else if (x <= -0.6) {
				rightMotor.set(Math.min(1.0, -x));
				leftMotor.set(0.5);
			}
		}
	}
	
	/**
	 * Stops the roller's motor
	 */
	public void stop() {
		leftMotor.set(0.0);
		rightMotor.set(0.0);
	}
	
	public boolean getLeftLimitSwitch(){
		return limitSwitchLeft.get();
	}
	
	public boolean getRightLimitSwitch(){
		return limitSwitchRight.get();
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
