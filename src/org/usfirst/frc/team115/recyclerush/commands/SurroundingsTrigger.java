package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

public class SurroundingsTrigger extends Trigger {

	public static final double DIST_LIM_INCH = 18.0;

	@Override
	public boolean get() {
		boolean isClose = false;
		if (Robot.drive.getFrontUltrasonicInches() <= DIST_LIM_INCH)
			isClose = true;
		else if (Robot.drive.getBackUltrasonicInches() <= DIST_LIM_INCH)
			isClose = true;
		else if (Robot.drive.getLeftUltrasonicInches() <= DIST_LIM_INCH)
			isClose = true;
		else if (Robot.drive.getRightUltrasonicInches() <= DIST_LIM_INCH)
			isClose = true;
		else
			isClose = false;
		return isClose;
	}

}
