package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DelayCommand extends CommandGroup {

	public DelayCommand(Command c, double time) {
		addSequential(new Delay(time));
		addSequential(c);
	}

}
