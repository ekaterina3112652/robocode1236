package robot1236;

import robocode.*;
import robocode.util.Utils;
import java.util.Random;

import java.awt.Color;
import java.awt.geom.Point2D;

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
	
	public void onHitWall(HitWallEvent e) {
		//Reverse direction upon hitting a wall
		moveDistance *= -1;
	}
	
	public void onScannedRobot(ScannedRobotEvent e) {
		//Turn towards scanned robot
		turnRight(e.getBearing());
		//Fire at scanned robot
		fire(1);
	}
}	