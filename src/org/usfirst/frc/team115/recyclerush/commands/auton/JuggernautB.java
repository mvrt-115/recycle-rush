package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;
/*
 * Drives down line of totes starting from the right, picking up one can and three totes, before going to the auto zone and dropping them off
 * @author Marcus Plutowski, Ishan Mitra
 */
public class JuggernautB extends CommandGroup{
	private static final double BIN_TO_TOTE = 1.5;
	private static final double TOTE_TO_BIN= 6.9;
	public JuggernautB() {

		//addSequential(new CloseClaw()); Automatically done in autointake
		addSequential(new AutoIntake(true, Elevator.PRESET_BIN_INTAKETOTE));
		addSequential(new RollerOpen());
		addSequential(new DelayCommand(0.2));
		addSequential(new DriveStraightDistanceNoPID(BIN_TO_TOTE));
		addSequential(new AutoIntake(true, Elevator.PRESET_TOTE_INTAKETOTE));

		addSequential(new DriveStraightDistanceNoPID(TOTE_TO_BIN));

		addSequential(new JuggernautRotate());
		addSequential(new RollerOpen());
		addSequential(new DelayCommand(0.2));
		addSequential(new DriveStraightDistanceNoPID(BIN_TO_TOTE));
		addSequential(new AutoIntake(true, Elevator.PRESET_TOTE_INTAKETOTE));

		addSequential(new DriveStraightDistanceNoPID(TOTE_TO_BIN));

		addSequential(new JuggernautRotate());
		addSequential(new RollerOpen());
		addSequential(new DelayCommand(0.2));
		addSequential(new DriveStraightDistanceNoPID(BIN_TO_TOTE));
		addSequential(new AutoIntake(true, Elevator.PRESET_BOTTOM));

		addSequential(new Turn(-90.0));
		addParallel(new DriveStraightDistanceNoPID(11.0));

		addSequential(new RollerOpen());
		addSequential(new OpenClaw());	
		addSequential(new DriveStraightDistanceNoPID(-1.0));
	}
}
