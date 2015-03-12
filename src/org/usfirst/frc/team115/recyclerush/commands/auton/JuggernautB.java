package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

public class JuggernautB extends CommandGroup{
	private static final double BIN_TO_TOTE = 1.5;
	public JuggernautB() {

		addSequential(new OpenClaw());
		addSequential(new AutoIntake(true, Elevator.PRESET_BIN_INTAKETOTE));
		addSequential(new DriveStraightDistanceNoPID(BIN_TO_TOTE));
		addSequential(new AutoIntake(true, Elevator.PRESET_TOTE_INTAKETOTE));
		addSequential(new DriveStraightDistanceNoPID(6.9));
		addSequential(new JuggernautRotate());
		addSequential(new DriveStraightDistanceNoPID(BIN_TO_TOTE));
		addSequential(new AutoIntake(true, Elevator.PRESET_TOTE_INTAKETOTE));
		addSequential(new DriveStraightDistanceNoPID(6.9));
		addSequential(new JuggernautRotate());
		addSequential(new DriveStraightDistanceNoPID(BIN_TO_TOTE));
		addSequential(new AutoIntake(true, Elevator.PRESET_BOTTOM));
		addSequential(new Turn(-90.0));
		addParallel(new DriveStraightDistanceNoPID(11.0));
		addSequential(new RollerOpen());
		addSequential(new OpenClaw());	
		addSequential(new DriveStraightDistanceNoPID(-1.0));
	}

}
