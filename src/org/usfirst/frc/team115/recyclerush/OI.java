package org.usfirst.frc.team115.recyclerush;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * @author Lee Mracek
 * This class should contain all interactions between physical controls and the robot,
 * including Cypress, Joystick, Triggers, etc.
 */
public class OI {
	private Joystick joystick;
	private JoystickButton ElevateUpButton, ElevateDownButton, DriveButton, RightTurnButton, LeftTurnButton;
	
	
	public OI() {
		
		joystick = new Joystick(RobotMap.JOYSTICK);
		
		//todo: map buttons for commands (@akhil99)
	}
	
	
	public Joystick getJoystick() {
		return joystick;
	}

}

