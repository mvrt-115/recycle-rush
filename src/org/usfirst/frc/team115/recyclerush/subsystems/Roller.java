package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Contains the two motors that make up the Robot's roller mechanism
 * @author Alex Erf
 *
 */
public class Roller extends Subsystem {
	
	private CANTalon rollerMotor;
	private DoubleSolenoid rollerSolenoid;
	
	
	/**
	 * Creates the two motors with the specified ports
	 * @param m_port	Port for the CANTalon motor
	 */
	public Roller(int m_port, int solenoid_port1, int solenoid_port2) {
		rollerMotor = new CANTalon(m_port);		
		rollerSolenoid = new DoubleSolenoid(solenoid_port1, solenoid_port2);
	}

	public void initialize() {}
	
	@Override
	protected void initDefaultCommand() {}
	
	public CANTalon getMotor() {
		return rollerMotor;
	}
	
	/**
	 * Turns the roller's motor
	 * @param val	The speed to turn the motor at (corrected to be between -1.0 and 1.0)
	 */
	public void set(double val) {
		if (Math.abs(val) <= 1.0)
			rollerMotor.set(val);
		else if (val > 1.0)
			rollerMotor.set(1.0);
		else
			rollerMotor.set(-1.0);
	}
	
	/**
	 * Stops the roller's motor
	 */
	public void stop() {
		set(0.0);
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
