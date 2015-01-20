package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team115.recyclerush.Robot;

public class TurnNinetyRight extends Command {
	private Gyro gyro;
	private double desiredAngle;
	
	public TurnNinetyRight(){
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		gyro.reset();
		desiredAngle= gyro.getAngle()+90;
	}

	@Override
	protected void execute() {
		while(gyro.getAngle() != desiredAngle){
			if(gyro.getAngle() < desiredAngle)
				Robot.drive.drive(0.5, -0.5);
			else{
				Robot.drive.drive(-0.25, 0.25);
			}
		}
	}

	@Override
	protected boolean isFinished() {
		if (gyro.getAngle() == desiredAngle) {
			return true;
		} else {
			return false;
		}
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
