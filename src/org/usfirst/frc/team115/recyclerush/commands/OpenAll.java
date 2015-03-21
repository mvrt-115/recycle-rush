package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class OpenAll extends CommandGroup {

	public OpenAll(){
		addParallel(new ClawOpen());
		addParallel(new IntakeOpen());
		addSequential(new StabilizerOpen());
	}

}
