package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Lee Mracek on 2/10/15.
 * Moves the elevator up a preset
 */
public class ElevatorUp extends Command {
	
    private final int indices;
    Elevator elev;

    public ElevatorUp() {
        this(1);
    }

    public ElevatorUp(int indices) {
        this.indices = indices;
        requires(Robot.elevator);
    }

    @Override
    protected void initialize() {
    	// enable PID
    	elev = Robot.elevator;
    	elev.enable();
		elev.setMotorControlMode(ControlMode.Position);
		elev.release();
		setPosition();
    }

    private void setPosition(){
    	int[] presets = elev.presets;
    	double height = elev.getHeight();
    	int destPreset = presets.length - 1;
    	for(int i = 0; i < presets.length; i++){
    		if(presets[i] > height + 1){ // if the preset is above current height
    			destPreset = i; // set that preset to our destination
    			break;
    		}
    	}
    	Robot.elevator.setSetpoint(presets[destPreset]);
    }
    
    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() {
    	return Robot.elevator.onTarget();
    }

    @Override
    protected void end() {
    	// disable PWM
    	Robot.elevator.disable();
    	Robot.elevator.brake();
    }

    @Override
    protected void interrupted() {

    }
    
}
