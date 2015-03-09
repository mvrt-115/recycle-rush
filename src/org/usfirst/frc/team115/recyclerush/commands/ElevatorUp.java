package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves the elevator up a preset
 * @author Akhil Palla
 */
public class ElevatorUp extends CommandGroup {
	

    public ElevatorUp() {
        double destHeight = getGoal();
        addSequential(new ElevatorToHeight(destHeight));
    }

    private double getGoal() {
    	double[] presets = Robot.elevator.presets;
    	double height = Robot.elevator.getHeight();
    	int destPreset = presets.length - 1;
    	double destHeight = 0;
    	for(int i = 0; i < presets.length; i++) {
    		if(presets[i] > height + 1) { // if the preset is above current height
    			destPreset = i; // set that preset to our destination
    			break;		
    		}
    	}
    	destHeight = presets[destPreset];
    	SmartDashboard.putNumber("Elev dest height", destHeight);
    	return destHeight;
    }
    
}
