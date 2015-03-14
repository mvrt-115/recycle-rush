package org.usfirst.frc.team115.recyclerush.commands.auton;

import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.DriveDistance;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraightDistanceNoPID;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;
import org.usfirst.frc.team115.recyclerush.commands.DelayCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CanDriveDrive extends CommandGroup{

	public CanDriveDrive() {
		addSequential(new AutoIntake(true, Elevator.PRESET_BOTTOM, false));
		addSequential(new DelayCommand(0.5));
		addSequential(new DriveDistance(-8));
		//addSequential(new DriveStraightDistanceNoPID(-3));,l+opo
		addSequential(new RollerOpen());
		addParallel(new OpenClaw());
		//addSequential(new DelayCommand(0.5));
		//addSequential(new DriveDistance(-4));
		//addSequential(new DriveStraightDistanceNoPID(-3));
	}

}
