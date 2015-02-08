/**
 * This class contains all interactions between physical controls and the robot,
 * including Joystick, Triggers, etc.
 * @author MVRT
 */

package org.usfirst.frc.team115.recyclerush;

import org.usfirst.frc.team115.recyclerush.commands.AutoIntake;
import org.usfirst.frc.team115.recyclerush.commands.CloseClaw;
import org.usfirst.frc.team115.recyclerush.commands.OpenClaw;
import org.usfirst.frc.team115.recyclerush.commands.RollerClose;
import org.usfirst.frc.team115.recyclerush.commands.RollerOpen;

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
	
	/**
	 * Sets up each button of the Xbox controller, and performs a command when pressed,
	 * held, etc.
	 * 
	 */
	private void initXboxController(){
		
		JoystickButton lb = new JoystickButton(xbox, RobotMap.XBOX_LB);
		lb.whenPressed(new OpenClaw());
		JoystickButton rb = new JoystickButton(xbox, RobotMap.XBOX_RB);
		rb.whenPressed(new CloseClaw());
	
		XboxTrigger lt = new XboxTrigger(xbox, RobotMap.XBOX_LT, 0.8);
		XboxTrigger rt = new XboxTrigger(xbox, RobotMap.XBOX_RT, 0.8);
		 
		//TODO: Finish CloseArm and OpenArm
		lt.whenActive(new RollerClose());
		rt.whenActive(new RollerOpen());
		
		//TODO: Finish AutoIntake.java
		JoystickButton ab =  new JoystickButton(xbox, RobotMap.XBOX_A);
		ab.whenPressed(new AutoIntake()); 
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
	
	public double getElevAxis(){
		//elev.getAxis();
		return getXboxAxis(RobotMap.XBOX_AXIS_LY);
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

class DpadTrigger extends Trigger{
	
	Joystick xbox;
	int angle;
	
	public DpadTrigger(Joystick xbox, int angle){
		this.angle = angle;
		this.xbox = xbox;
	}
	
	public boolean get(){
		return xbox.getPOV() == angle;
	}
	
}
