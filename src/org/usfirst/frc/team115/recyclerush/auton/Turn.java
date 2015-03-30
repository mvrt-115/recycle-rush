package org.usfirst.frc.team115.recyclerush.auton;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class Turn extends PIDCommand {

	private static final double kP = 0.0;
	private static final double kI = 0.0;
	private static final double kD = 0.0;

	private final double dAngle;
	private boolean shitty = false;

	public Turn(double dAngle) {
		super(kP, kI, kD);
		this.dAngle = dAngle;
	}

	public Turn(double dAngle, boolean shitty) {
		this(dAngle);
		this.shitty = shitty;
	}

}
