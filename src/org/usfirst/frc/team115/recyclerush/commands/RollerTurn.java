package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * RollerTurn turns each of the motors in Robot's roller 
 * instance based on the speeds specified in the constructor.
 *  
 * @author MVRT
 *
 */
public class RollerTurn extends Command {
	
	private double l_speed;
	private double r_speed;
	
	/**
	 * Turns each motor in opposite directions at the maximum speed (1.0)
	 */
	public RollerTurn() {
		this(1.0);
	}
	
	/**
	 * turns the rollers in separate directions with the specified speed
	 * @param speed	The speed for the motors
	 */
	public RollerTurn(double speed) {
		this(speed, -speed);
	}
	
	/**
	 * Turns each motor with the specified speeds
	 * @param l_speed	Speed for the left motor
	 * @param r_speed	Speed for the right motor
	 */
	public RollerTurn(double l_speed, double r_speed) {
		requires(Robot.roller);
		this.l_speed = l_speed;
		this.r_speed = r_speed;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		Robot.roller.turnLeft(l_speed);
		Robot.roller.turnRight(r_speed);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.roller.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
