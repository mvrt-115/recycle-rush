package org.usfirst.frc.team115.recyclerush.commands;

/**
 * Turns the rollers inwards (towards the Robot's body)
 * @author MVRT
 *
 */
public class RollerIn extends RollerTurn {

	public RollerIn() {
		this(1.0);
	}
	
	public RollerIn(double speed) {
		super(speed, speed);
	}

}
