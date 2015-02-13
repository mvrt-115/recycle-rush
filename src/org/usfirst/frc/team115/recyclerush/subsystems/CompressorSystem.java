package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.commands.EnableCompressor;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CompressorSystem extends Subsystem{
	private Compressor compressor;
	
	public CompressorSystem(int pcmID) {
		compressor = new Compressor(pcmID);
		compressor.setClosedLoopControl(true);
	}
	
	public void disable() {
		compressor.stop();
	}
	
	public void enable() {
		compressor.start();
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new EnableCompressor());
	}
}
