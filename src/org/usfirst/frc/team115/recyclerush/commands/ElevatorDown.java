package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorDown extends PIDCommand {
	
	private static final double preset1 = 0;
	private static final double preset2 = 1;
	private static final double preset3 = 2;
	
	private double dest = 0;

	public ElevatorDown(double p, double i, double d) {
		super(p, i, d);
		requires(Robot.elevator);
		SmartDashboard.putString("ElevatorDirection", "Down");
	}

	@Override
	protected double returnPIDInput() {
		return 0; //@arushirai1 is this correct?
	}

	@Override
	protected void usePIDOutput(double output) {}

	@Override
	protected void initialize() {
		Robot.elevator.releaseBrake();
	}

	@Override
	protected void execute() {
		double currentHeight = Robot.elevator.getHeight(); // Fix this later need to scale

		if (currentHeight > preset3){
			Robot.elevator.goToHeight(preset3);
			dest = preset3;
		}
		else if (currentHeight > preset2){
			Robot.elevator.goToHeight(preset2);
			dest = preset2;
		}
		else if (currentHeight > preset1) {
			Robot.elevator.goToHeight(preset1);
			dest = preset1;
		} 
		
	}

	@Override
	protected boolean isFinished() {
		return Robot.elevator.getHeight() <= dest;
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
