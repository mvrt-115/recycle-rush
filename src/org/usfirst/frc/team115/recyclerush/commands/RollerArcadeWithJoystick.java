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

	private boolean rolling = false, rolltrigger_past = false;

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

		if(!rolltrigger_past && Robot.oi.rollerButtonPressed()) {
			rolling = !rolling;
		}
		rolltrigger_past = Robot.oi.rollerButtonPressed();

		if(rolling && !isDriving()) {
			Robot.roller.control(-1, 0);
		} else {
			double move = Robot.oi.getXboxAxis(moveAxis, true);
			double rotate = Robot.oi.getXboxAxis(rotateAxis, true);
			Robot.roller.control(move, rotate);
		}
	}

	private boolean isDriving() {
		return Math.abs(Robot.oi.getXboxAxis(moveAxis, true)) >= 0.15
				&& Math.abs(Robot.oi.getXboxAxis(rotateAxis, true)) >= 0.15;
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
