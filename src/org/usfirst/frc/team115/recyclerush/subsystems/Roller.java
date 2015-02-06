package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.commands.RollerControl;

import edu.wpi.first.wpilibj.CANTalon;
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
	
	
	/**
	 * Creates the two motors with the specified ports
	 * @param m_port	Port for the CANTalon motor
	 */
	public Roller(int lm_port, int rm_port, int solenoid1, int solenoid2) {
		leftMotor = new CANTalon(lm_port);
		rightMotor = new CANTalon(rm_port);
		rollerSolenoid = new DoubleSolenoid(solenoid1, solenoid2);
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