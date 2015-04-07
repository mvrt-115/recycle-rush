package org.usfirst.frc.team115.recyclerush.commands.led;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDStrip;

import edu.wpi.first.wpilibj.command.Command;

public class FlashColor extends Command {
	boolean finished = false;
	short color;
	short period_on;
	short period_off;

	/**
	 * Pulses a color on the LED's
	 *
	 * @param ledColor
	 *            : The color to pulse
	 * @param period_on
	 *            : The period (ms) to stay on
	 * @param period_off
	 *            : The period (ms) to stay off
	 */
	public FlashColor(short ledColor, short period_on, short period_off) {
		requires(Robot.ledStripPrimary);
		color = ledColor;
		this.setRunWhenDisabled(true);
		this.period_off = period_off;
		this.period_on = period_on;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.ledStripPrimary.sendLEDCommand(LEDStrip.STRIP_ADDRESS,
				LEDStrip.FUNCTION_FLASH, color, (short) 0, period_on,
				period_off);
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
