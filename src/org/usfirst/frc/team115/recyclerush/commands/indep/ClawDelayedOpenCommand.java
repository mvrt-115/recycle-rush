package org.usfirst.frc.team115.recyclerush.commands.indep;

import org.usfirst.frc.team115.recyclerush.commands.ClawOpen;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClawDelayedOpenCommand extends CommandGroup {

	public ClawDelayedOpenCommand(double height) {
		addSequential(new WaitForHeightCommand(height));
		addSequential(new ClawOpen());
	}

}
