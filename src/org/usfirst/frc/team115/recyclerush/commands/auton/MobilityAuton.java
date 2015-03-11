package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.DriveForDistance;
import org.usfirst.frc.team115.recyclerush.commands.DriveStop;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team115.recyclerush.commands.Turn;

public class MobilityAuton extends CommandGroup{
	
	private final double DISTANCE_TO_LANDMARK = 9.0;
	
	public MobilityAuton(){
		addSequential(new Turn(90));
		addSequential(new DriveForDistance(DISTANCE_TO_LANDMARK));
		addSequential(new DriveStop());
	}

}
