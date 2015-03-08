package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Moves the elevator up a preset
 * @author Akhil Palla (creds to Lee for being Lee)
 */
public class ElevatorUp extends Command {
	
	private double destHeight;

    public ElevatorUp() {
        requires(Robot.elevator);
    }

    @Override
    protected void initialize() {
    	// enable PID
		Robot.elevator.release();
		
		setGoal();
    }

    private void setGoal() {
    	int[] presets = Robot.elevator.presets;
    	double height = Robot.elevator.getHeight();
    	int destPreset = presets.length - 1;
    	for(int i = 0; i < presets.length; i++) {
    		if(presets[i] > height + 1) { // if the preset is above current height
    			destPreset = i; // set that preset to our destination
    			break;		
    		}
    	}
    	destHeight = presets[destPreset];
    	SmartDashboard.putNumber("Elev dest height", destHeight);
    }
    
    @Override
    protected void execute() {
    	Robot.elevator.control(-1 * Elevator.PRESET_SPEED);
    }

    @Override
    protected boolean isFinished() {
    	return Math.abs(Robot.elevator.getHeight() - destHeight) <= Elevator.THRESHOLD;
    }

    @Override
    protected void end() {
    	Robot.elevator.brake();
    	Robot.oi.rumbleXbox(RumbleType.kLeftRumble, 0.2, 300);
    	Robot.oi.rumbleXbox(RumbleType.kRightRumble, 0.2, 300);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
