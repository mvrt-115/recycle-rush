package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

public class ArcadePrecisionDrive extends Command {
	
	private static final double PRECISION_SCALE_MOVE = 0.5;
	private static final double PRECISION_SCALE_ROTATE = 0.5;
	
	private Joystick joystick;
	
	public ArcadePrecisionDrive() {
		requires(Robot.drive);
		joystick = Robot.oi.getJoystick();
	}
	
	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
	  	double moveValue = joystick.getAxis(AxisType.kY) * PRECISION_SCALE_MOVE;
    	double rotateValue = joystick.getAxis(AxisType.kX) * PRECISION_SCALE_ROTATE;
    	Robot.drive.drive(moveValue, rotateValue);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {}

}
