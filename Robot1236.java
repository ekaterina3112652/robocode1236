package robot1236;

import robocode.*;
import robocode.util.Utils;
import java.util.Random;
import robocode.util.Utils;

import javafx.scene.control.skin.TextInputControlSkin.Direction;

import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * Robot1236 - a robot by (your name here)
 */
public class Robot1236 extends AdvancedRobot {

	private Random random = new Random();
	private double moveDistance = 100;

	public void run() {
		// Set colors
		setColors(Color.yellow, Color.green, Color.red);

		while (true) {
			moveRandomly();
		}
	}

	private void moveRandomly() {
		ahead(moveDistance * (random.nextDouble() * 2 - 1));
		turnRight(90 * (random.nextDouble() * 2 - 1));
		execute();
	}

	// ### Luanna ###
	public void onHitWall(HitWallEvent e) {
		// Will random generate an angle between 45 and 135 degrees
		double turnAngle = Math.toRadians((Math.random() * 90) + 45);
		// Wiçç random generate a distance to move foward betweem 50 and 150 degrees
		double distance = (Math.random() * 100) + 50;
		// If math random is less than 0.5 will randomly turn for right or left
		if (Math.random() < 0.5) {
			// Turn right based on the random turnAngle
			turnRightRadians(turnAngle);
		} else {
			// Turn çeft based on the random turnAngle
			turnLeftRadians(turnAngle);
		}
		// Go ahead a random distance
		ahead(distance);
	}
	// ### Luanna ###

	public void onScannedRobot(ScannedRobotEvent e) {
		// //Turn towards scanned robot
		// turnRight(e.getBearing());
		// //Fire at scanned robot
		// fire(1);
		
		//Calculate the enemy's velocity, distance, and heading
		double enemyVelocity = e.getVelocity();
		double enemyDistance = e.getDistance();
		double enemyHeading = e.getHeading();
		
		//Calculate the angle to the enemy
		double absBearing = getHeadingRadians() + e.getBearingRadians();
		
		//Calculate the predicted enemy heading based on its current heading and velocity
		double enemyHeadingPredicted = enemyHeading + enemyVelocity / enemyDistance * Math.sin(e.getHeadingRadians() - absBearing);
		
		//Calculate the predicted enemy x and y coordinates
		double enemyX = getX() + enemyDistance * Math.sin(absBearing);
		double enemyY = getY() + enemyDistance * Math.cos(absBearing);
		double enemyXPredicted = enemyX + enemyVelocity / enemyDistance * Math.sin(enemyHeadingPredicted);
		double enemyYPredicted = enemyY + enemyVelocity / enemyDistance * Math.cos(enemyHeadingPredicted);

		//Calculate the angle to the predicted enemy location
		double targetHeading = Math.atan2(enemyXPredicted - getX(), enemyYPredicted - getY());
		
		// Turn the gun to the predicted enemy location
		turnGunRightRadians(Utils.normalRelativeAngle(targetHeading - getGunHeadingRadians()));

		// Fire at the enemy
		fire(1);
	}
}