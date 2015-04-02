package org.usfirst.frc.team115.recyclerush.auton;

import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonGroup extends CommandGroup {
	public AutonGroup(Command autonomous) {
		addSequential(new ElevatorHardReset());
		addSequential(autonomous);
	}
}
