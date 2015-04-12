package org.usfirst.frc.team115.recyclerush.auton;

import org.usfirst.frc.team115.recyclerush.commands.DelayCommand;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightForDistance;
import org.usfirst.frc.team115.recyclerush.commands.IntakeOpen;
import org.usfirst.frc.team115.recyclerush.commands.OpenAll;
import org.usfirst.frc.team115.recyclerush.commands.indep.AutoIntake;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class JuggernautA extends CommandGroup {

	private static final double BIN_CLOSING_DISTANCE = 3;

	public JuggernautA() {
		for(int i = 0; i < 2; i++) {
			addParallel(new AutoIntake(Elevator.PRESET_TOTE_INTAKETOTE, true, false));
			addSequential(new DriveStraightForDistance(BIN_CLOSING_DISTANCE));
			addSequential(new JuggernautRotate());
			addParallel(new DelayCommand(new IntakeOpen(), .3));
			addSequential(new DriveStraightForDistance(6.9));
		}

		addSequential(new AutoIntake(Elevator.PRESET_BOTTOM, true, false));
		addSequential(new Turn(90));
		addSequential(new DriveStraightForDistance(96.0));
		addSequential(new OpenAll());
		addSequential(new DriveStraightForDistance(-6));
	}

}
