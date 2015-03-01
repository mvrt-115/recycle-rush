package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.Elevator;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Moves the elevator up a preset
 * @author Akhil Palla (creds to Lee for being Lee)
 */
public class ElevatorToPreset extends Command {
	
    Elevator elev;
    int destPreset;

    public ElevatorToPreset(int preset) {
        requires(Robot.elevator);
        destPreset = preset;
    }

    @Override
    protected void initialize() {
    	// enable PID
    	elev = Robot.elevator;
    	elev.enable();
		elev.release();
		setPosition();
    }

    private void setPosition(){
    	int[] presets = elev.presets;
    	elev.setSetpoint(presets[destPreset]);
    }
    
    @Override
    protected void execute() {}

    @Override
    protected boolean isFinished() {
    	return elev.onTarget();
    }

    @Override
    protected void end() {
    	// disable PWM
    	elev.disable();
    	elev.brake();
    	Robot.oi.rumbleXbox(RumbleType.kLeftRumble, 0.2, 300);
    	Robot.oi.rumbleXbox(RumbleType.kRightRumble, 0.2, 300);
    }

    @Override
    protected void interrupted() {
        end();
    }
    
}
