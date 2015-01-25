package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.commands.RollerStop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Contains the two motors that make up the Robot's roller mechanism
 * @author MVRT
 *
 */
public class Roller extends Subsystem {
	
	private CANTalon rollerMotorLeft;
	private CANTalon rollerMotorRight;
	
	public static final double CLOCKWISE = 1.0;
	public static final double COUNTER_CLOCKWISE = -1.0;
	
	/**
	 * Creates the two motors with the specified ports
	 * @param l_port	The port for the left motor
	 * @param r_port	The port for the right motor
	 */
	public Roller(int l_port, int r_port) {
		rollerMotorLeft = new CANTalon(l_port);
		rollerMotorRight = new CANTalon(r_port);
		rollerMotorRight.reverseOutput(true);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RollerStop());
	}
	
	public CANTalon getRollerMotorLeft() {
		return rollerMotorLeft;
	}
	
	public CANTalon getRollerMotorRight() {
		return rollerMotorRight;
	}
	
	/**
	 * Turns the left motor
	 * @param val	The speed to turn the left motor at (corrected to be between -1.0 and 1.0)
	 */
	public void turnLeft(double val) {
		if (Math.abs(val) <= 1.0)
			rollerMotorLeft.set(val);
		else if (val > 1.0)
			rollerMotorLeft.set(1.0);
		else
			rollerMotorLeft.set(-1.0);
	}
	
	/**
	 * Turns the right motor
	 * @param val	The speed to turn the right motor at (corrected to be between -1.0 and 1.0)
	 */
	public void turnRight(double val) {
		if (Math.abs(val) <= 1.0)
			rollerMotorRight.set(val);
		else if (val > 1.0)
			rollerMotorRight.set(1.0);
		else
			rollerMotorRight.set(-1.0);
	}
	
	/**
	 * Stops both of the roller's motor
	 */
	public void stop() {
		turnLeft(0.0);
		turnRight(0.0);
	}

}
