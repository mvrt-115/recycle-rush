package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem to control the compressor
 * @author Lee Mracek
 */
public class CompressorSystem extends Subsystem {
	private Compressor compressor;

	public void initialize() {
		compressor = new Compressor(RobotMap.PCM);
		compressor.setClosedLoopControl(true);
		compressor.start();
	}

	@Override
	protected void initDefaultCommand() {
	}
}
