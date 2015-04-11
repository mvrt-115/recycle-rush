package org.usfirst.frc.team115.recyclerush.commands.led;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDStrip;

import edu.wpi.first.wpilibj.command.Command;

public class FadePulse extends Command {
	boolean finished = false;
	short color1;
	short color2;
	short period;

	/**
	 * Fades between two colors
	 *
	 * @param color1
	 *            : The color to pulse
	 * @param color2
	 *            : The second color to pulse
	 * @param period
	 *            : The period (ms) to blink at
	 */
	public FadePulse(short color1, short color2, short period) {
		requires(Robot.ledStripPrimary);
		this.color1 = color1;
		this.setRunWhenDisabled(true);
		this.color2 = color2;
		this.period = period;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.ledStripPrimary
		.sendLEDCommand(LEDStrip.STRIP_ADDRESS,
				LEDStrip.FUNCTION_PULSE_FADE, color1, color2, period,
				(short) 0);
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