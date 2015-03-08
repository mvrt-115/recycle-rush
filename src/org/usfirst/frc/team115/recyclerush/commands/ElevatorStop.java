package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Stops the elevator
 * @author MVRT
 */
public class ElevatorStop extends Command {

    boolean finished = false;
	
	public ElevatorStop(){
		requires(Robot.elevator);
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
        Robot.elevator.control(0);
        Robot.elevator.brake();
        finished = true;
    }

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {}

	@Override
	protected void interrupted() {
	    end();
	}

}
