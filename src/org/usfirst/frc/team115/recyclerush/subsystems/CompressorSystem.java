package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CompressorSystem extends Subsystem{
	private Compressor compressor;
	
	public CompressorSystem() {
		compressor = new Compressor(RobotMap.COMPRESSOR);
		compressor.setClosedLoopControl(true);
		compressor.start();
	}
	
	public void disable() {
		compressor.stop();
	}
	
	public void enable() {
		compressor.start();
	}

	@Override
	protected void initDefaultCommand() {}
}
