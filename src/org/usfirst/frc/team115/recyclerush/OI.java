package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.CloseClaw;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;


 /* @author MVRT
 * This class should contain all interactions between physical controls and the robot,
 * including Cypress, Joystick, Triggers, etc.
 * Added XboxTrigger class, to use the triggers/other axes as triggers, to run a command when the trigger passes a certain threshold
 */

public class OI {
	
	private Joystick joystick;
	private Joystick xbox;
	
	public OI() {
		joystick = new Joystick(RobotMap.JOYSTICK);
		xbox = new Joystick(RobotMap.XBOX);
		initXboxController();
	}
	
	private void initXboxController(){
		JoystickButton lb = new JoystickButton(xbox, RobotMap.XBOX_LB);
		lb.whenPressed(new OpenClaw());
		JoystickButton rb = new JoystickButton(xbox, RobotMap.XBOX_RB);
		rb.whenPressed(new CloseClaw());
	}
	
	public Joystick getJoystick() {
		return joystick;
	}
	
	public Joystick getXbox() {
		return xbox;
	}
	
	public boolean getXboxButton(int btn){
		return xbox.getRawButton(btn);
	}
	
	public double getXboxAxis(int axis){
		return xbox.getRawAxis(axis);
	}
	
	public int getXboxPOV(){
		return xbox.getPOV();
	}
	
}

class XboxTrigger extends Trigger{

	Joystick xbox;
	int channel;
	double threshold;
	
	public XboxTrigger(Joystick xbox, int channel, double threshold) {
		this.xbox = xbox;
		this.channel = channel;
	}
	
	public boolean get() {
		return xbox.getRawAxis(channel) >= threshold;
	}
}

