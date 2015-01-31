package org.usfirst.frc.team115.recyclerush.commands;

import java.awt.Color;

import org.usfirst.frc.team115.recyclerush.subsystems.LEDstrip;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StrobeLEDs extends Command{

	LEDstrip strip;
	Timer timer;
	
	double brightness = 0;
	double fadeAmount = 0.05;

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
		if(brightness <= 0){
			brightness = 0;
			fadeAmount = -fadeAmount;
			//swap colors:
			if(curr.equals(one)){
				curr = two;
			} else {
				curr = one;
			}
		}else if(brightness >= 1){
			brightness = 1;
			fadeAmount = -fadeAmount;
		}
		strip.setColor(curr.getRed(), curr.getGreen(), curr.getBlue(), brightness);
		brightness += fadeAmount;
		SmartDashboard.putNumber("brightness", brightness);
		Timer.delay(0.1);
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
