package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class RollerArcadeWithJoystick extends Command {

	private Joystick joystick;

	private int moveAxis, rotateAxis;

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
		Robot.roller.control(joystick, moveAxis, rotateAxis);
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
