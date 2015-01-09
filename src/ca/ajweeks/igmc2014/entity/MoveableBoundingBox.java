package ca.ajweeks.igmc2014.entity;

import ca.ajweeks.igmc2014.level.Tile;

public class MoveableBoundingBox extends BoundingBox {
	
	private final float TIMESLICE_EPSILON = 0.00001f;
	private final float MAX_TIMESLICE_SUBDIVISIONS = 32;
	
	private final boolean X_AXIS = true;
	private final boolean Y_AXIS = false;
	
	private float px, py; // Our previous position
	private float xv, yv;
	
	public MoveableBoundingBox(float x, float y, float width, float height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	/** Attempts to move this object according to it's current velocity, based on the delta time supplied
	 *  Returns whether or not this object was able to be moved (False if there was a collision) */
	public boolean attemptToMove(double delta, Tile[] tiles) {
		// Store our already (hopefully) collision-free position
		px = x;
		py = y;
		
		float timeslicesRemaining = 1.0f;
		int numberOfTimesSubdivided = 0;
		boolean collided;
		Collision collision = new Collision();
		Collision bestCollision = new Collision();
		
		while (timeslicesRemaining > TIMESLICE_EPSILON) {
			bestCollision.time = 1.0f + TIMESLICE_EPSILON;
			collided = false;
			
			//LATER only check the four chunks near us
			
			for (int i = 0; i < tiles.length; i++) {
				if (intersectionTest(tiles[i], collision)) {
					System.out.println(t++);
					if (collision.time < bestCollision.time || collision.time < bestCollision.time + TIMESLICE_EPSILON
							&& collision.surfaceArea > bestCollision.surfaceArea) {
						collided = true;
						bestCollision = collision.copy();
					}
				}
			}
			
			if (!collided) return false;
			
			resolveCollision(delta, bestCollision, timeslicesRemaining);
			
			timeslicesRemaining = (1.0f - bestCollision.time) * timeslicesRemaining;
			numberOfTimesSubdivided++;
			
			if (numberOfTimesSubdivided > MAX_TIMESLICE_SUBDIVISIONS) {
				System.err.println("Warning! Maximum timeslice subdivisions reached! Collision resolution may be incomplete");
				break;
			}
		}
		
		return true;
	}
	
	int t = 0;
	
	private boolean intersectionTest(Tile tile, Collision collision) {
		if (!tile.isSolid()) return false;
		
		float timeX = 0.0f;
		float timeY = 0.0f;
		boolean collisionX = false;
		boolean collisionY = false;
		float newX = 0.0f;
		float newY = 0.0f;
		
		// The static object's left, right, bottom, and top coords
		float sL = tile.x;
		float sR = tile.x + tile.width;
		float sT = tile.y;
		float sB = tile.y + tile.height;
		
		// Our left, right, bottom, and top coords
		float mL = this.x;
		float mR = this.x + this.width;
		float mT = this.y;
		float mB = this.y + this.height;
		
		// Our last position's left, right, bottom, and top coords
		float pL = px;
		float pR = px + this.width;
		float pT = py;
		float pB = py + this.height;
		
		//FIXME flip fucking top and bottom vars cause ludobloom is some kind of huge dumbass :/
		if (Math.min(mB, pB) < sT - TIMESLICE_EPSILON && Math.max(mT, pT) > sB + TIMESLICE_EPSILON) {
			if (pR <= sL + TIMESLICE_EPSILON && mR > sL + TIMESLICE_EPSILON) {
				collisionX = true;
				timeX = (sL - pR) / (mL - pL);
			} else if (pL >= sR - TIMESLICE_EPSILON && mL > sR - TIMESLICE_EPSILON) {
				collisionX = true;
				timeX = (sR - pL) / (mL - pL);
			}
			
			if (collisionX) {
				newY = pB + (mB - pB) * timeX;
				if (newY >= sT - TIMESLICE_EPSILON || newY + tile.height <= sB + TIMESLICE_EPSILON) {
					collisionX = false;
				}
			}
		}
		
		if (Math.min(mL, pL) < sR - TIMESLICE_EPSILON && Math.max(mR, pR) > sL + TIMESLICE_EPSILON) {
			if (pT <= sB + TIMESLICE_EPSILON && mT > sB + TIMESLICE_EPSILON) {
				collisionY = true;
				timeY = (sB - pT) / (mB - pB);
			} else if (pB >= sT - TIMESLICE_EPSILON && mB < sT - TIMESLICE_EPSILON) {
				collisionY = true;
				timeY = (sT - pB) / (mB - pB);
			}
			
			if (collisionY) {
				newX = pL + (mL - pL) * timeY;
				if (newX >= sR - TIMESLICE_EPSILON || newY + tile.width <= sL + TIMESLICE_EPSILON) {
					collisionY = false;
				}
			}
		}
		
		if (collisionX || collisionY) {
			if (!collisionX || (collisionY && timeY < timeX)) {
				collision.time = timeY;
				collision.axis = Y_AXIS;
				collision.surfaceArea = Math.min(sR, newX + tile.width) - Math.max(sL, newX);
				return true;
			}
			collision.time = timeX;
			collision.axis = X_AXIS;
			collision.surfaceArea = Math.min(sT, newY + tile.height) - Math.max(sB, newY);
			return true;
		}
		
		return false;
	}
	
	private void resolveCollision(double delta, Collision bestCollision, float timeSlicesRemaining) {
		x = px + (x - px) * bestCollision.time;
		y = py + (y - py) * bestCollision.time;
		
		if (bestCollision.axis == X_AXIS) {
			xv = 0.0f;
		} else {
			yv = 0.0f;
		}
		
		px = x;
		py = y;
		x += xv * (1.0f - bestCollision.time) * timeSlicesRemaining;
		y += yv * (1.0f - bestCollision.time) * timeSlicesRemaining;
	}
	
	class Collision {
		float time = 0;
		float surfaceArea = 0;
		boolean axis = X_AXIS;
		
		public Collision copy() {
			Collision c = new Collision();
			c.time = this.time;
			c.surfaceArea = this.surfaceArea;
			c.axis = this.axis;
			return c;
		}
	}
	
	public void setXv(float xv) {
		this.xv = xv;
	}
	
	public void setYv(float yv) {
		this.yv = yv;
	}
	
}
