package com.joaoaugustoperin.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	public int width, height;
	public double x, y;
	public double dx, dy;
	public double speed = 1.6;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 4;
		this.height = 4;
		
		int angle = new Random().nextInt(75) + 46;
	
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		
		if(x + (dx*speed) + width >= Game.WIDTH) {
			dx *= -1;
		} else if (x + (dx*speed) < 0)	 {
			dx *= -1;
		}
		
		if( y>= Game.HEIGHT) {
			// Enemy scored
			System.out.println("Enemy Scored :(");
			new Game();
			return;
		} else if (y < 0) {
			// Player scored
			System.out.println("Player Scored! :)");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)), (int)(y+(dy*speed)), width, height);
		Rectangle playerBounds = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.height);
		Rectangle enemyBounds = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.height);
		
		if(bounds.intersects(playerBounds)) {
			int angle = new Random().nextInt(75) + 46;
			
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if (dy > 0) {
				dy *= -1;
			}
		} else if (bounds.intersects(enemyBounds)) {
			int angle = new Random().nextInt(75) + 46;
			
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy < 0) {
				dy *= -1;
			}
		}
		
		x += dx*speed;
		y += dy*speed;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)x, (int)y, width, height);
	}

}
