package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorStop extends Command {

    boolean finished = false;
	
	public ElevatorStop(){
		requires(Robot.elevator);
	}
	
	@Override
	protected void initialize() {
		// disable PID
		Robot.elevator.disable();
		Robot.elevator.setMotorControlMode(ControlMode.PercentVbus);
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
	protected void interrupted() {}

}
