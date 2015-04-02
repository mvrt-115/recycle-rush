package org.usfirst.frc.team115.recyclerush.auton;

import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.ClawOpen;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightForDistance;
import org.usfirst.frc.team115.recyclerush.commands.IntakeClose;
import org.usfirst.frc.team115.recyclerush.commands.IntakeOpen;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CanMobility extends CommandGroup {
	public CanMobility() {
		addSequential(new IntakeClose());
		addParallel(new ClawClose());
		addSequential(new DriveStraightForDistance(-8));
		addSequential(new IntakeOpen());
		addParallel(new ClawOpen());
	}
}
