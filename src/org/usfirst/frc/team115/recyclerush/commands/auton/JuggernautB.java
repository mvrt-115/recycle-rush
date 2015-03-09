package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.CloseClaw;
import org.usfirst.frc.team115.recyclerush.commands.DriveForDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorDown;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorUp;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.ToggleStabilizer;
import org.usfirst.frc.team115.recyclerush.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JuggernautB extends CommandGroup{

	public JuggernautB() {

		//addParallel(new RollerOpen()); 
		//addSequential(new OpenClaw());
		addParallel(new DriveForDistance(0.2));
		addSequential(new CloseClaw());
		//addSequential(new ToggleStabilizer());
		addSequential(new ElevatorUp());
		addSequential(new ElevatorUp());
		addSequential(new DriveForDistance(2.1));
		addSequential(new ElevatorDown());
		addSequential(new ElevatorDown());
		addParallel(new OpenClaw());
		addSequential(new ToggleStabilizer());
		addSequential(new AutoIntake());
		addParallel(new DriveForDistance(2.75));
		addSequential(new ElevatorUp());

		addSequential(new JuggernautRotate());
		addSequential(new DriveForDistance(4.1));
		addSequential(new AutoIntake());
		addParallel(new DriveForDistance(2.75));
		addSequential(new ElevatorUp());

		addSequential(new JuggernautRotate());
		addParallel(new DriveForDistance(4.1));
		addSequential(new AutoIntake());
		addSequential(new Turn(90.0));
		//addSequential(new DriveForDistance(10.0));
		addParallel(new ElevatorDown());
		addSequential(new RollerOpen());
		addSequential(new OpenClaw());
		addParallel(new ToggleStabilizer());
		addSequential(new DriveForDistance(-1.0));

	}

}
