package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.subsystems.LEDstrip;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDstrip.Color;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class StrobeLEDs extends Command{

	LEDstrip strip;
	Timer timer;

	Color one, two;
	Color curr;
	
	public StrobeLEDs(LEDstrip strip, Color colorOne, Color colorTwo) {
		requires(strip);
		this.strip = strip;
		timer = new Timer();
		one = colorOne;
		two = colorTwo;
		curr = one;
	}

	@Override
	protected void initialize() {
		timer.reset();
	}
	
	@Override
	protected void execute() {
		strip.setColor(curr.getRed(), curr.getGreen(), curr.getBlue(), 1);
		Timer.delay(1);
		if(curr.equals(one))curr = two;
		else curr = one;
	}

	@Override
	protected void end() {
		strip.allOff();
	}
	
	@Override
	protected void interrupted() {
		strip.allOff();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
