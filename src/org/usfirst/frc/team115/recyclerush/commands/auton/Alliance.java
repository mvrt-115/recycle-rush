package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.DelayCommand;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightDistanceNoPID;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorToHeight;
import org.usfirst.frc.team115.recyclerush.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Alliance extends CommandGroup {

	public Alliance() {
		addSequential(new Turn(90));
		addSequential(new DelayCommand(0.5));
		addSequential(new DriveStraightDistanceNoPID(8, 0.7));
	}

}
