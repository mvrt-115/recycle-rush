package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LEDstrip extends Subsystem {
	
	PWM red;
	PWM blue;
	PWM green;
	
	public LEDstrip(int redChannel, int greenChannel, int blueChannel) {
		red = new PWM(redChannel);
		red.enableDeadbandElimination(true);
		blue = new PWM(blueChannel);
		blue.enableDeadbandElimination(true);
		green = new PWM(greenChannel);
		green.enableDeadbandElimination(true);
	}
	
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public void setColor(double r, double g, double b, double brightness){
		if(brightness < 0 || brightness > 1)return; //ignore bad values
		red.setRaw((int) (r * brightness));
		green.setRaw((int) (g * brightness));
		blue.setRaw((int) (b * brightness));
	}
	
	public void allOff(){
		red.setRaw(0);
		blue.setRaw(0);
		green.setRaw(0);
	}
	
	public static class Color{
		
		int red;
		int green;
		int blue;
		
		public Color(int red, int green, int blue){
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
		
		public int getRed(){ return red; }
		
		public int getBlue(){ return blue; }
		
		public int getGreen(){ return green; }
		
	}

}
