package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HulkSmashAutonA extends CommandGroup{
	
	public HulkSmashAutonA(){
		for(int i=0; i<2; i++){
			addSequential(new AutoIntake());
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
			addSequential(new DriveforDistance(2));
			addSequential(new Turn(-90.0));
			addSequential(new ElevatorDown(0.0, 0.0, 0.0));
			addSequential(new ElevatorDown(0.0, 0.0, 0.0));
			addSequential(new ElevatorDown(0.0, 0.0, 0.0));
			addSequential(new Turn(90.0));
			addSequential(new DriveForDistance(4.7));
		}
		addSequential(new AutoIntake());
		addSequential(new ElevatorUp(0.0, 0.0, 0.0));
		addSequential(new Turn(90.0));
		addSequential(new DriveForDistance(10.0));
		addParallel(new RollerOpen());
		addSequential(new ElevatorDown(0.0, 0.0, 0.0));
		addParallel(new OpenClaw());	
		addSequential(new DriveForDistance(-1.0));
	}

}
