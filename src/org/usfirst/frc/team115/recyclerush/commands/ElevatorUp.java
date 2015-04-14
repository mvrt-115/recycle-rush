package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Moves the elevator up a preset
 * @author Akhil Palla
 */
public class ElevatorUp extends CommandGroup {

	private ElevatorToHeight elevToHeight;

	public ElevatorUp() {
		elevToHeight = new ElevatorToHeight(0);
		addSequential(elevToHeight);
	}

	@Override
	public void initialize(){
		double destHeight = getGoal();
		elevToHeight.setDest(destHeight);
	}

	private double getGoal() {
		double[] presets = Elevator.presets;
		double height = Robot.elevator.getHeight();
		int destPreset = presets.length - 1;
		double destHeight = 0;
		for(int i = 0; i < presets.length; i++) {
			if(presets[i] > height + 2) { // if the preset is above current height
				destPreset = i; // set that preset to our destination
				break;
			}
		}
		destHeight = presets[destPreset];
		return destHeight;
	}

}
