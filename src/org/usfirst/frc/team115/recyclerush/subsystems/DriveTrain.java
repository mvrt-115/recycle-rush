/**
 * This is the drive train for the robot for the competition.
 *
 * @author Heather Baker
 */

package org.usfirst.frc.team115.recyclerush.subsystems;

import com.kauailabs.nav6.frc.IMUAdvanced;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team115.recyclerush.RobotMap;
import org.usfirst.frc.team115.recyclerush.commands.ArcadeDriveWithJoystick;

public class DriveTrain extends Subsystem {

	private RobotDrive drive;
	private final int BACK_LEFT = 0;
	private final int BACK_RIGHT = 1;
	private final int FRONT_LEFT = 2;
	private final int FRONT_RIGHT = 3;
	private final IMUAdvanced navX;
	
	private CANTalon motors[];
	
	/**
	 * Initializes each other motors based on ports set in RobotMap
	 */
	public DriveTrain() {
	    navX = new IMUAdvanced(new SerialPort(57600, Port.kMXP));
	    motors = new CANTalon[4];
	    motors[BACK_LEFT] = new CANTalon(RobotMap.BACK_LEFT_DRIVE);
	    motors[BACK_RIGHT] = new CANTalon(RobotMap.BACK_RIGHT_DRIVE);
	    motors[FRONT_LEFT] = new CANTalon(RobotMap.FRONT_LEFT_DRIVE);
	    motors[FRONT_RIGHT] = new CANTalon(RobotMap.FRONT_RIGHT_DRIVE);
	    drive = new RobotDrive(motors[FRONT_LEFT], motors[BACK_LEFT],
	            motors[FRONT_RIGHT], motors[BACK_RIGHT]);
	}
	
	/**
	 * This thing drives the robot!
	 *
	 * @param move   the forward speed of the rotation
	 * @param rotate the rotation value of the robot
	 */
	public void drive(double move, double rotate) {
	    drive.arcadeDrive(move, rotate);
	}
	
	/**
	 * Drives the robot
	 *
	 * @param joystick The joystick to drive based on
	 */
	public void drive(Joystick joystick) {
	    drive.arcadeDrive(joystick);
	}
	
	/**
	 * Stops the drivetrain
	 */
	public void stop() {
	    drive(0, 0);
	}
	
	/**
	 * Initializes the default command of the subsystem.
	 */
	@Override
	protected void initDefaultCommand() {
	    setDefaultCommand(new ArcadeDriveWithJoystick());
	}
	
	/**
	 * This returns the current.
	 *
	 * @return the current
	 */
	public double getCurrent() {
	    double current = 0;
	    for (CANTalon motor : motors)
	        current += motor.getOutputCurrent();
	    return current;
	}
	
	/**
	 * Returns the angle of rotational displacement
	 *
	 * @return the current yaw of the gyro
	 */
	public float getYaw() {
	    return navX.getYaw();
	}
	
	/**
	 * Returns the angle of tilt along the horizontal plane
	 *
	 * @return the gyro's pitch
	 */
	public float getPitch() {
	    return navX.getPitch();
	}
	
	/**
	 * Returns the angle of tilt along the vertical plane
	 *
	 * @return the gyro's roll
	 */
	public float getRoll() {
	    return navX.getRoll();
	}
	
	/**
	 * Resets the navx and any encoders
	 */
	public void resetAll() {
	    navX.zeroYaw();
	    //encoder reset goes here
	}
	
	/**
	 * Returns the displacement along x axis
	 *
	 * @return the x displacement
	 */
	public float getX() {
	    return navX.getWorldLinearAccelX();
	}
	
	/**
	 * Returns the displacement along x axis
	 *
	 * @return the x displacement
	 */
	public float getY() {
	    return navX.getWorldLinearAccelY();
	}
	
	/**
	 * Returns the displacement along x axis
	 *
	 * @return the x displacement
	 */
	public float getZ() {
	    return navX.getWorldLinearAccelZ();
	}
	
}
