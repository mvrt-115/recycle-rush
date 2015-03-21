package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.OI;
import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives the roller using a joystick and specified axis
 *
 * @author Lee Mracek
 */
public class RollerArcadeWithJoystick extends Command {

	private Joystick joystick;

	private int moveAxis, rotateAxis;

	public RollerArcadeWithJoystick(){
		this(Robot.oi.getXboxJoystick(), OI.ROLLER_MOVE_AXIS, OI.ROLLER_ROTATE_AXIS);
	}

	public RollerArcadeWithJoystick(Joystick joystick, int moveAxis, int rotateAxis) {
		requires(Robot.roller);

		this.joystick = joystick;
		this.moveAxis = moveAxis;
		this.rotateAxis = rotateAxis;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		double move = Robot.oi.getXboxAxis(moveAxis, true);
		double rotate = Robot.oi.getXboxAxis(rotateAxis, false);
		Robot.roller.control(move, rotate);
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
