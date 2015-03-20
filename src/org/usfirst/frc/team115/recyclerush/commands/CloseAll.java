package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CloseAll extends CommandGroup {

	public CloseAll(){
		addParallel(new ClawClose());
		addParallel(new IntakeClose());
		addSequential(new StabilizerClose());
	}

}
