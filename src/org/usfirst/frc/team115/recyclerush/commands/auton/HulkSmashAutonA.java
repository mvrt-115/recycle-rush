package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.DriveForDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorDown;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorUp;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HulkSmashAutonA extends CommandGroup{
	
	public HulkSmashAutonA(){
		for(int i=0; i<2; i++){
			addSequential(new AutoIntake());
			addSequential(new ElevatorUp());
			addSequential(new ElevatorUp());
			addSequential(new ElevatorUp());
			addSequential(new ElevatorUp());
			addSequential(new DriveForDistance(2));
			addSequential(new Turn(-90.0));
			addSequential(new ElevatorDown());
			addSequential(new ElevatorDown());
			addSequential(new ElevatorDown());
			addSequential(new Turn(90.0));
			addSequential(new DriveForDistance(4.7));
		}
		addSequential(new AutoIntake());
		addSequential(new ElevatorUp());
		addSequential(new Turn(90.0));
		addParallel(new DriveForDistance(10.0));
		addSequential(new RollerOpen());
		addParallel(new ElevatorDown());
		addSequential(new OpenClaw());	
		addSequential(new DriveForDistance(-1.0));
	}

}
