package org.usfirst.frc.team115.recyclerush.commands.led;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDStrip;

import edu.wpi.first.wpilibj.command.Command;

public class Rainbow extends Command {
	boolean finished = false;
	short period;

	/**
	 * Runs the LED's on "rainbow mode"
	 *
	 * @param period
	 *            : The period (ms) to blink at
	 */
	public Rainbow(short period) {
		requires(Robot.ledStripPrimary);
		this.setRunWhenDisabled(true);
		this.period = period;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.ledStripPrimary.sendLEDCommand(LEDStrip.STRIP_ADDRESS,
				LEDStrip.FUNCTION_RAINBOW, (short) 0, (short) 0, period,
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