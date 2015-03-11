package org.usfirst.frc.team115.recyclerush.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

/**
 * Drives the robot straight for a given distance,
 * utilizing two seperate PID control loops
 * 
 *  boolean works = this.works();
 *  @author (works)?"Akhil Palla":"MVRT" 
 */
public class DriveStraightForDistance extends DriveStraight implements PIDSource, PIDOutput{

    //TODO: Tune these values
    private static final double P_DIST = 0.1;
    private static final double I_DIST = 0.1;
    private static final double D_DIST = 0.1;

    private static final double MAX_SPEED_DEFAULT = 0.5;
    
    /**  scaling constant for feet into encoder ticks. */
    private final double SCALE = 12 * (1 / (Math.PI * 8)) * 3 * 1024;
    
    /** PIDController for driving the desired distance */
    PIDController distancePID;
    
    /** The distance to travel, in encoder ticks */
    private double distance;
    
    /** This flag allows us to check to make sure
     *  distancePID.onTarget() returned true for two consecutive
     *  iterations, to make sure we're on target
     */
    private boolean last_finished_state = false;
    
    /**
     * Initializes a DriveForDistance command, to drive a certain number of feet
     * @param dist: Distance, in feet
     */
    public DriveStraightForDistance(double dist){
        this(dist, MAX_SPEED_DEFAULT);
    }
    
    /**
     * Initializes a DriveForDistance command, to drive a certain number of feet
     * @param dist: Distance, in feet
     * @param maxSpeed: The maximum speed to drive at
     */
    public DriveStraightForDistance(double dist, double maxSpeed){
        super(0, false);
        distance = dist * SCALE;
        distancePID = new PIDController(P_DIST, I_DIST, D_DIST, this, this);
        distancePID.setOutputRange(-1 * maxSpeed, maxSpeed);
        distancePID.setAbsoluteTolerance(1000); //set absolute tolerance to 1000 ticks, or about 10 inches
    }

    @Override
    protected void initialize() {
        super.initialize();
        drive.zeroEncoders();
        distancePID.setSetpoint(distance);
        distancePID.enable();
    }
    
    /**
     * Returns PID info for distance driving
     * (returns drive distance)
     */
    @Override
    public double pidGet() {
        return drive.getDistance();
    }
    
    /**
     * Uses the DistanceDrive PID output to set drive speed
     */
    @Override
    public void pidWrite(double out) {
        setSpeed(out);
    }
    
    @Override
    protected boolean isFinished(){
        if(distancePID.onTarget() && last_finished_state)return true;
        //else, update last_finished_state and return false
        last_finished_state = distancePID.onTarget();
        return false;
    
    }

    @Override
    protected void end(){
        super.end();
        
        //disable and free our PID controller
        distancePID.disable();
        distancePID.free();
    }
    
}
