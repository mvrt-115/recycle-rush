package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveArcadeWithJoystick extends Command {

	private Joystick joystick;

	public DriveArcadeWithJoystick(Joystick joystick) {
		requires(Robot.drive);

		this.joystick = joystick;
	}

	@Override
	protected void initialize() {
		Robot.drive.stop();
	}

	@Override
	protected void execute() {
		Robot.drive.control(joystick);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
