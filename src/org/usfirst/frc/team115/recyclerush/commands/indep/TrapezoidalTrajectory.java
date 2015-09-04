package org.usfirst.frc.team115.recyclerush.commands.indep;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TrapezoidalTrajectory extends Command {


	double duration;
	double rampUp;
	double rampDown;
	double max;
	double initial;
	double current;
	double speed;

	public TrapezoidalTrajectory(double Duration, double RampUp, double RampDown, double Max) { //Seconds, %, %, %VBUS
		this.duration = Duration;
		this.rampUp = Math.min(1,RampUp);
		this.rampDown = Math.min(1, RampDown);
		this.max = Max;
		this.initial = Timer.getFPGATimestamp();
		this.current = 0;
		this.speed = 0;
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		if(current < rampUp*current){
			speed = current/(rampUp*current)*max;
		}
		else if(current < rampDown*current){
			speed = max;
		}
		else{
			speed = max - current/(rampDown*current)*max;
		}
	}

	@Override
	protected boolean isFinished() {
		return Timer.getFPGATimestamp()-initial > duration;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
