package org.usfirst.frc.team115.recyclerush.subsystems;

import javafx.scene.paint.Color;

import org.usfirst.frc.team115.recyclerush.RobotMap;

import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LEDstrip extends Subsystem {

	private final double MAX_SCALER = 0.5; //max brightness = 1/2
	
	PWM red;
	PWM blue;
	PWM green;
	
	public LEDstrip(int redChannel, int greenChannel, int blueChannel) {
		red = new PWM(redChannel);
		blue = new PWM(blueChannel);
		green = new PWM(greenChannel);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

	public void setColor(double r, double g, double b, double brightness){
		if(brightness < 0 || brightness > 1)return; //ignore bad values
		red.setRaw((int) (r * brightness * MAX_SCALER));
		green.setRaw((int) (g * brightness * MAX_SCALER));
		blue.setRaw((int) (b * brightness * MAX_SCALER));
	}
	
	public void setColor(Color c, double brightness){
		setColor(c.getRed(), c.getGreen(), c.getBlue(), brightness);
	}
	
	public void allOff(){
		red.setRaw(0);
		blue.setRaw(0);
		green.setRaw(0);
	}

}
