package robot1236;

import robocode.*;
import robocode.util.Utils;
import java.util.Random;

import javafx.scene.control.skin.TextInputControlSkin.Direction;

import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Robot1236 - a robot by Group 2 GCL
 */
public class Robot1236 extends AdvancedRobot {

	//for stop and go strategy
	private boolean movingForward;
	private int moveCount;

	public void run() {
		//set colors
    	setColors(Color.yellow, Color.green, Color.red);

		//move forward for 10 turns, then backward for 10, and repeat
    	while (true) {
			setTurnRadarRight(Double.POSITIVE_INFINITY); //scan continuously
			if (moveCount % 10 == 0) {
				movingForward = !movingForward;
				setAhead(movingForward ? 100 : -100); //move in short bursts
			}
			execute();
			moveCount++;
		}
	}
	
	public void onHitWall(HitWallEvent e) {
		//randomly generate an angle between 45 and 135 degrees
		double turnAngle = Math.toRadians((Math.random() * 90) + 45);
		//randomly generate a distance to move forward betweem 50 and 150 degrees
		double distance = (Math.random() * 100) + 50;
		//if math random is less than 0.5 will randomly turn for right or left
		if (Math.random() < 0.5) {
			//turn right based on the random turnAngle
			turnRightRadians(turnAngle);
		} else {
			//turn left based on the random turnAngle
			turnLeftRadians(turnAngle);
		}
		//go ahead random distance
		ahead(distance);
	}
	
	public void onScannedRobot(ScannedRobotEvent event) {
		double angleToEnemy = getHeadingRadians() + event.getBearingRadians();
        double radarTurn = Utils.normalRelativeAngle(angleToEnemy - getRadarHeadingRadians());
        double gunTurn = Utils.normalRelativeAngle(angleToEnemy - getGunHeadingRadians());
        
        setTurnRadarRightRadians(radarTurn); //track enemy with radar
        setTurnGunRightRadians(gunTurn); //aim gun at enemy
        fire(1); //fire at enemy with a power of 1
	}
}