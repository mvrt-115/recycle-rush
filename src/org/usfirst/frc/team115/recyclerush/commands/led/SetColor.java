package org.usfirst.frc.team115.recyclerush.commands.led;

import org.usfirst.frc.team115.recyclerush.Robot;
import org.usfirst.frc.team115.recyclerush.subsystems.LEDStrip;

import edu.wpi.first.wpilibj.command.Command;

public class SetColor extends Command {
	boolean finished = false;
	short color;

	/**
	 * Sets the LED's color
	 *
	 * @param ledColor
	 *            : The ID of the color to set
	 */
	public SetColor(short ledColor) {
		requires(Robot.ledStripPrimary);
		this.setRunWhenDisabled(true);
		color = ledColor;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.ledStripPrimary.sendLEDCommand(LEDStrip.STRIP_ADDRESS,
				LEDStrip.FUNCTION_SET_COLOR, color, (short) 0, (short) 0,
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