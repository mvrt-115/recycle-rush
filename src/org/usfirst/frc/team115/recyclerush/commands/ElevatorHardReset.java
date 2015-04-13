package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorHardReset extends Command {

	private static final double SPEED_DOWN = -1;
	private static final double SAFETY_TIMEOUT = 3;

	public ElevatorHardReset(){
		requires(Robot.elevator);
	}

	@Override
	protected void initialize() {
		System.out.println("Init Hard Reset");
		setTimeout(SAFETY_TIMEOUT);
		Robot.elevator.setBrake(false);
	}

	@Override
	protected void execute() {
		Robot.elevator.setSpeed(SPEED_DOWN);
		SmartDashboard.putBoolean("Is Finished?", isFinished());
		SmartDashboard.putNumber("Limit Switch", Robot.elevator.isLimitPressed() ? 1 : 0);
	}

	@Override
	protected boolean isFinished() {
		return Robot.elevator.isLimitPressed() || isTimedOut();
	}

	@Override
	protected void end() {
		Robot.elevator.resetEncoder();
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		Robot.elevator.stop();
	}

}
