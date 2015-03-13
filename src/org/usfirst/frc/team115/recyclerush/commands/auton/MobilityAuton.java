package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.DriveStop;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightDistanceNoPID;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team115.recyclerush.commands.Turn;

public class MobilityAuton extends CommandGroup{
	
	public final static double DISTANCE_TO_LANDMARK = 9.0;
	
	public MobilityAuton(){
		addSequential(new Turn(90));
		addSequential(new DriveStraightDistanceNoPID(DISTANCE_TO_LANDMARK));
		addSequential(new DriveStop());
	}

}
