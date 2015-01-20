package org.usfirst.frc.team115.recyclerush;


import org.usfirst.frc.team115.recyclerush.commands.DriveStop;
import org.usfirst.frc.team115.recyclerush.commands.DriveStraight;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorDown;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorStop;
import org.usfirst.frc.team115.recyclerush.commands.ElevatorUp;
import org.usfirst.frc.team115.recyclerush.commands.TurnNinetyLeft;
import org.usfirst.frc.team115.recyclerush.commands.TurnNinetyRight;

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
		
		//test button for DriveStraight and turning - to be changed later.
		DriveButton = new JoystickButton(joystick, RobotMap.DRIVE_STRAIGHT);
		RightTurnButton = new JoystickButton(joystick, RobotMap.RIGHT_TURN);
		LeftTurnButton = new JoystickButton(joystick, RobotMap.LEFT_TURN);
		
		DriveButton.whenPressed(new DriveStraight(joystick));
		DriveButton.whenReleased(new DriveStop());
		RightTurnButton.whenPressed(new TurnNinetyRight());
		RightTurnButton.whenReleased(new DriveStop());
		LeftTurnButton.whenPressed(new TurnNinetyLeft());
		LeftTurnButton.whenReleased(new DriveStop());
		
		
		//Elevator buttons
		ElevateUpButton = new JoystickButton(joystick, RobotMap.ELEVATE_UP);
		ElevateDownButton = new JoystickButton(joystick, RobotMap.ELEVATE_DOWN);

		ElevateUpButton.whenPressed(new ElevatorUp());
		ElevateUpButton.whenReleased(new ElevatorStop());
		ElevateDownButton.whenPressed(new ElevatorDown());
		ElevateDownButton.whenReleased(new ElevatorStop());
		
	}
	
	
	public Joystick getJoystick() {
		return joystick;
	}

}

