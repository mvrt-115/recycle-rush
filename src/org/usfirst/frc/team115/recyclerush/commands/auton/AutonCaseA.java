package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.DriveForDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorDown;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorUp;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonCaseA extends CommandGroup {

	public AutonCaseA(int numOfTotes, int start){

		if(numOfTotes == 1 ){
			addSequential(new DriveForDistance(2.0));
			addSequential(new AutoIntake());
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
			addSequential(new Turn(-90.0));
			addSequential(new DriveForDistance(10.0));
			addSequential(new ElevatorDown(0.0, 0.0, 0.0));
			addParallel(new OpenClaw()); // ask Ishan about this and next line
			addSequential(new RollerOpen());
			addSequential(new DriveForDistance(-1.0));
		}

		else if(numOfTotes == 2 && start == 1 || numOfTotes == 2 && start == 2){
			addSequential(new DriveForDistance(2.0));
			addSequential(new AutoIntake());
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
			addSequential(new Turn(-90.0));
			addSequential(new DriveForDistance(1.0));
			addSequential(new Turn(90.0));
			addSequential(new DriveForDistance(2.0)); // ask about this as well
			addSequential(new Turn(90.0));
			addSequential(new DriveForDistance(1.0));
			addSequential(new Turn(-90.0));
			addSequential(new DriveForDistance(2.75));

			addSequential(new AutoIntake());
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
			addSequential(new Turn(90.0));
			addSequential(new DriveForDistance(10.0));
			addSequential(new ElevatorDown(0.0, 0.0, 0.0));
			addParallel(new OpenClaw());
			addSequential(new RollerOpen());
			addSequential(new DriveForDistance(-1.0));
		}

		else if(numOfTotes == 2 && start == 3){
			addSequential(new DriveForDistance(2.0));
			addSequential(new AutoIntake());
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
			addSequential(new Turn(180));
			addSequential(new DriveForDistance(2.75));
			addSequential(new Turn(90.0));
			addSequential(new DriveForDistance(1.0));
			addSequential(new Turn(-90.0));
			addSequential(new DriveForDistance(4.0));
			addSequential(new Turn(-90.0));
			addSequential(new DriveForDistance(1.0));
			addSequential(new Turn(-90.0));
			addSequential(new DriveForDistance(1.0));
			addSequential(new Turn(90.0));
			addSequential(new AutoIntake());
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
			addSequential(new Turn(90.0));
			addSequential(new DriveForDistance(10.0));
			addSequential(new ElevatorDown(0.0, 0.0, 0.0));
			addParallel(new OpenClaw());
			addSequential(new RollerOpen());
			addSequential(new DriveForDistance(-1.0));
		}
		
	}

}
