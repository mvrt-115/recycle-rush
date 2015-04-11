package org.usfirst.frc.team115.recyclerush.auton;

import org.usfirst.frc.team115.recyclerush.commands.DriveStraightForDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Mobility extends CommandGroup {
	public Mobility() {
		addSequential(new DriveStraightForDistance(90));
	}
}
