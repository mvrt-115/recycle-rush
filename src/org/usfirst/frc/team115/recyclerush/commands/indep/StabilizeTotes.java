package org.usfirst.frc.team115.recyclerush.commands.indep;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.ClawOpen;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StabilizeTotes extends CommandGroup {
	public StabilizeTotes() {
		addSequential(new ClawOpen());
		addSequential(new ElevatorToHeight(Robot.elevator.PRESET_STABILIZE_TOTES));
		addSequential(new ClawClose());
		addSequential(new ElevatorToHeight(Robot.elevator.PRESET_STABILIZE_TOTES + 2));
	}
}
