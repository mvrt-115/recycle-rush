package org.usfirst.frc.team115.recyclerush.commands.led;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDStrip;

import edu.wpi.first.wpilibj.command.Command;

public class PulseColor extends Command {

	private short color;
	private short period;
	private boolean finished = false;

	public PulseColor(short period, short color) {
		requires(Robot.ledStripPrimary);
		this.period = period;
		this.color = color;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.ledStripPrimary.sendLEDCommand(LEDStrip.STRIP_ADDRESS,
				LEDStrip.FUNCTION_PULSE, color, (short) 0, period, (short) 0);
		finished = true;
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
