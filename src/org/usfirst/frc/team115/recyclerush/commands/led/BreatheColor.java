package org.usfirst.frc.team115.recyclerush.commands.led;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDStrip;

import edu.wpi.first.wpilibj.command.Command;

public class BreatheColor extends Command {
	boolean finished = false;
	short color1;
	short color2;

	/**
	 * "Breathes" between two colors
	 *
	 * @param color1
	 *            : The first color
	 * @param color2
	 *            : The second color
	 */
	public BreatheColor(short color1, short color2) {
		requires(Robot.ledStripPrimary);
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.ledStripPrimary
		.sendLEDCommand(LEDStrip.STRIP_ADDRESS,
				LEDStrip.FUNCTION_BREATHE, (short) 0, (short) 0,
				color1, color2);
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