package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.DriveForDistance;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorUp;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

public class JuggernautA extends CommandGroup {

	/**
	 * For Marcus:
	 */

	private static final double BIN_CLOSING_DISTANCE = 0.1;

	public JuggernautA(){
		for(int i = 0; i < 2; i++) {
			addSequential(new AutoIntake(true, Elevator.PRESET_TOTE_INTAKETOTE));
			addParallel(new DriveForDistance(BIN_CLOSING_DISTANCE));
			addSequential(new JuggernautRotate());
			addSequential(new DriveForDistance(6.9));
		}

		addSequential(new AutoIntake(true, Elevator.PRESET_BOTTOM));
		addSequential(new Turn(90.0));
		addParallel(new DriveForDistance(10.0));
		addSequential(new RollerOpen());
		addSequential(new OpenClaw());	
		addSequential(new DriveForDistance(-1.0));
	}
}
