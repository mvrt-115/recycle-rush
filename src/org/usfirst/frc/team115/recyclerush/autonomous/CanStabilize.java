package org.usfirst.frc.team115.recyclerush.autonomous;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.ClawOpen;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * Picks up a can and brings it up to Stabilier
 * @author Marcus Plutowski
 */
public class CanStabilize extends CommandGroup{
	public CanStabilize()
	{
		addSequential(new ClawClose());
		addSequential(new ToggleStabilizer());
		addSequential(new ElevatorToHeight(Robot.elevator.BOTTOM_HEIGHT, true));
		addSequential(new ToggleStabilizer());
		addSequential(new ClawOpen());
		addSequential(new ElevatorToHeight(Robot.elevator.PRESET_TOTE_INTAKETOTE, true));
	}
}
