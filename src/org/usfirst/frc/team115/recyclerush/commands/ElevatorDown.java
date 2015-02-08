package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorDown extends PIDCommand {
	private double preset1, preset2, preset3;
	private double currentHeight;

	public ElevatorDown(double p, double i, double d) {
		super(p, i, d);
		requires(Robot.elevator);
		SmartDashboard.putString("ElevatorDirection", "Down");
		currentHeight = Robot.elevator.getHeight();
		
		preset1 = 0;
		preset2 = 1;
		preset3 = 2;
	}

	@Override
	protected double returnPIDInput() {
		return 0;
	}

	@Override
	protected void usePIDOutput(double output) {}

	@Override
	protected void initialize() {
		Robot.elevator.release();
	}

	@Override
	protected void execute() {

		currentHeight = Robot.elevator.getHeight(); // Fix this later need to scale

		if (currentHeight > preset3){
			Robot.elevator.goToHeight(preset3);
		}
		else if (currentHeight > preset2){
			Robot.elevator.goToHeight(preset2);
		}
		else if (currentHeight > preset1) {
			Robot.elevator.goToHeight(preset1);
		} 
		
		end();
	}
	
	
	

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.elevator.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

}