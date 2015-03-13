package org.usfirst.frc.team115.recyclerush.commands;


/**
 * Drives the robot straight for a given distance
 * 
 *  boolean works = this.works();
 *  @author (works)?"Akhil Palla":"MVRT" 
 */
public class DriveStraightDistanceNoPID extends DriveStraight{

    private static final double SPEED_DEFAULT = 0.5;
    private static final double THRESHOLD = 0.0;
    
    /**  scaling constant for feet into encoder ticks. */
    private final double SCALE = 12 * (1 / (Math.PI * 8)) * 2 * 1024;
    
    /** The distance to travel, in encoder ticks */
    private double distance;
    
    /**
     * Initializes a DriveForDistance command, to drive a certain number of feet
     * @param dist: Distance, in feet
     */
    public DriveStraightDistanceNoPID(double dist){
        this(dist, SPEED_DEFAULT);
    }
    
    /**
     * Initializes a DriveForDistance command, to drive a certain number of feet
     * @param dist: Distance, in feet
     * @param speed: The maximum speed to drive at
     */
    public DriveStraightDistanceNoPID(double dist, double speed){
        super(0, false);
        distance = dist * SCALE;
        setSpeed(distance > 0 ? speed : -1 * speed);
    }
    public DriveStraightDistanceNoPID(double dist, double speed, double P, double I, double D){
        super(0, false, P, I, D);
        distance = dist * SCALE;
        setSpeed(distance > 0 ? speed : -1 * speed);
    }
    public DriveStraightDistanceNoPID(double dist, double P, double I, double D){
        this(dist, SPEED_DEFAULT, P, I, D);
    }

    @Override
    protected void initialize() {
        super.initialize();
        drive.zeroEncoders();
    }
    
    @Override
    protected boolean isFinished(){
        if(distance > 0)
        	return drive.getDistance() >= (distance - THRESHOLD);
    	return drive.getDistance() <= distance + THRESHOLD;
    }

    @Override
    protected void end(){
        super.end();
    }
    
}
