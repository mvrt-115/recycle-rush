package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.DriveForDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCaseC extends CommandGroup{
	
	public AutonCaseC(){
		addSequential(new DriveForDistance());
	}

}
