package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.DriveForDistance;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCaseC extends CommandGroup{
	
	public AutonCaseC(){
		addParallel(new RollerOpen());
		addSequential(new OpenClaw());
		
		
		
	}

}
