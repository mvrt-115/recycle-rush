         package org.usfirst.frc.team115.recyclerush.subsystems;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CompressorSystem extends Subsystem
{
	private Compressor compressor;
	
	public CompressorSystem()
	{
		compressor = new Compressor(RobotMap.COMPRESSOR);
		compressor.setClosedLoopControl(true);
		compressor.start();
	}
	
	public void disable()
	{
		compressor.stop();
		System.out.println("Compressor Disabled");
	}
	
	public void enable()
	{
		compressor.start();
		System.out.println("Stabilizer Enabled");
	}
	public void log()
	{
		compressor.enabled();
		SmartDashboard.putBoolean("Compressor System Enabled?", compressor.enabled());
	}
	@Override
	protected void initDefaultCommand() {}
}
