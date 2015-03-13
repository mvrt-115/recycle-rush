package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.DelayCommand;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightDistanceNoPID;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorUp;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.commands.Turn;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;
/*
 * Drives down the line of totes, starting from the left, picking up 3 totes, before going to the auto zone and dropping them off
 * @author Marcus Plutowski
 */
public class JuggernautA extends CommandGroup {

	private static final double BIN_CLOSING_DISTANCE = 0.1;

	public JuggernautA(){
		for(int i = 0; i < 2; i++) {
			addSequential(new AutoIntake(true, Elevator.PRESET_TOTE_INTAKETOTE));
			addParallel(new DriveStraightDistanceNoPID(BIN_CLOSING_DISTANCE));
			addSequential(new JuggernautRotate());
			addSequential(new RollerOpen());
			addSequential(new DelayCommand(0.2));
			addSequential(new DriveStraightDistanceNoPID(6.9));
			addParallel(new OpenClaw());
		}

		addSequential(new AutoIntake(true, Elevator.PRESET_BOTTOM));
		addSequential(new Turn(90.0, Turn.CompP, Turn.CompI, Turn.CompD));
		addParallel(new DriveStraightDistanceNoPID(10.0));
		addSequential(new RollerOpen());
		addSequential(new OpenClaw());	
		addSequential(new DriveStraightDistanceNoPID(-1.0));
	}
}
