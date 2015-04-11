package org.usfirst.frc.team115.recyclerush.commands.indep;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.commands.ClawClose;
import org.usfirst.frc.team115.recyclerush.commands.ClawOpen;
import org.usfirst.frc.team115.recyclerush.commands.Delay;
import org.usfirst.frc.team115.recyclerush.commands.DelayCommand;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorHardReset;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoIntake extends CommandGroup {

	public AutoIntake(double heightAtEnd, boolean intake) {
		if(intake) {
			addSequential(new IntakeTote());
		}
		addParallel(new DelayCommand(new ClawOpen(), 0.05));
		addSequential(new ElevatorToHeight(Robot.elevator.PRESET_CLAW_RELEASE, false, true));
		addSequential(new ClawOpen());
		addSequential(new ElevatorHardReset());
		addSequential(new ClawClose());
		addSequential(new Delay(0.2));
		addSequential(new ElevatorToHeight(heightAtEnd));
	}
}
