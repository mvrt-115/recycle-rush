package org.usfirst.frc.team115.recyclerush.commands;

import org.usfirst.frc.team115.recyclerush.subsystems.LEDstrip;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDstrip.Color;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LEDStaticColor extends Command {

	private LEDstrip strip;
	int r, g, b;
	
	public LEDStaticColor(Color c, LEDstrip strip) {
		requires(strip);
		this.strip = strip;
		this.r = c.getRed();
		this.g = c.getGreen();
		this.b = c.getBlue();
	}
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void execute() {
		strip.setColor(r, g, b, 1);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		strip.allOff();
		SmartDashboard.putString("ledStatus", "turned off");
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		strip.allOff();
		SmartDashboard.putString("ledStatus", "turned off");

	}

}
