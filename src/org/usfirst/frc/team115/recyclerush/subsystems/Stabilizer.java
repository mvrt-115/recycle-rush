package org.usfirst.frc.team115.recyclerush.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team115.recyclerush.RobotMap;

/**
 * A subsystem representing the robot's claw, which stabilizes the bins
 * @author MVRT
 */
public class Stabilizer extends Subsystem {

    DoubleSolenoid stabSolenoid;

    public Stabilizer()
    {
        super();
        stabSolenoid = new DoubleSolenoid(RobotMap.PCM, RobotMap.STABILIZER_PORT_A, RobotMap.STABILIZER_PORT_B);
    }
    public void log()
    {
    	SmartDashboard.putBoolean("Stabilizer Open?", stabSolenoid.get().equals(Value.kForward));
    }
    /**
     * Closes the claw
     */
    public void close()
    {
        stabSolenoid.set(Value.kReverse);
        System.out.println("Stabilizer Closing");
    }

    /**
     * Opens the claw
     */
    public void open()
    {
        stabSolenoid.set(Value.kForward);
        System.out.println("Stabilizer Opening");
    }

    public boolean isOpen()
    {
    	return stabSolenoid.get().equals(Value.kForward);
    }
    
    public boolean isClosed(){
    	return stabSolenoid.get().equals(Value.kReverse);
    }
    
    @Override
    protected void initDefaultCommand() {}

}
