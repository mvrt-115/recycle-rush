package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.DriveForDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorUp;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JuggernautB extends CommandGroup{
	
	public JuggernautB() {
		for(int i=0; i<2; i++){
			addSequential(new JuggernautRotate());
			addSequential(new DriveForDistance(4.1));
			addSequential(new AutoIntake());
			addParallel(new DriveForDistance(2.75));
			addSequential(new ElevatorUp(0.0, 0.0, 0.0));
		}
		addSequential(new JuggernautRotate());
		addParallel(new DriveForDistance(4.1));
		addSequential(new AutoIntake());
		addSequential(new Turn(90.0));
		addParallel(new DriveForDistance(10.0));
		addSequential(new RollerOpen());
		addSequential(new OpenClaw());
		addSequential(new DriveForDistance(-1.0));
	}
	
}
