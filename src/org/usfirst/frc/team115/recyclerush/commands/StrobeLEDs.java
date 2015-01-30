package org.usfirst.frc.team115.recyclerush.commands;


import javafx.scene.paint.Color;

import org.usfirst.frc.team115.recyclerush.subsystems.LEDstrip;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class StrobeLEDs extends Command{

	LEDstrip strip;
	Timer timer;
	
	double brightness = 0;
	double fadeAmount = 0.05;
	Color currentColor = Color.PURPLE;
	
	public StrobeLEDs(LEDstrip strip) {
		requires(strip);
		this.strip = strip;
		timer = new Timer();
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
			if(currentColor.equals(Color.PURPLE)){
				currentColor = Color.GOLD;
			} else {
				currentColor = Color.PURPLE;
			}
		}else if(brightness >= 1){
			brightness = 1;
			fadeAmount = -fadeAmount;
		}
		strip.setColor(currentColor, brightness);
		brightness += fadeAmount;
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
