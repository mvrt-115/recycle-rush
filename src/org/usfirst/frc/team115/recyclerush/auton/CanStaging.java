package org.usfirst.frc.team115.recyclerush.auton;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.ClawOpen;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightForDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorToHeight;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerClose;
import org.usfirst.frc.team115.recyclerush.commands.StabilizerOpen;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CanStaging extends CommandGroup {
	public CanStaging() {
		addSequential(new ClawClose());
		addSequential(new StabilizerOpen());
		addSequential(new ElevatorToHeight(Robot.elevator.PRESET_TOP));
		addSequential(new StabilizerClose());
		addParallel(new DriveStraightForDistance(-8));
		addSequential(new ClawOpen());
		addSequential(new ElevatorToHeight(Robot.elevator.PRESET_TOTE_INTAKETOTE, true));
	}
}
