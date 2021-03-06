package complexMobs.mob.lothicKnight;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import complexMobs.mob.LothricKnight;

public class Move {

	public void run(LothricKnight lothricKnight, double vectorScalar, double angleTurn, double angleOffset) {
		Vector targetDirection = lothricKnight.getBrain().getLocation().getDirection();
		double targetYaw = Math.atan2(targetDirection.getX(), targetDirection.getZ()) * 57.29; //Yaw that the mob needs to turn to
		double mobYaw = lothricKnight.getMain().getLocation().getYaw(); //Current yaw of the mob
		double targetCompareYaw = targetYaw;
		if (targetCompareYaw > 0) targetCompareYaw -= 360;
		targetCompareYaw *= -1;
		double deltaYaw = targetCompareYaw - mobYaw;
		double newYaw = mobYaw;
		
		if (deltaYaw > 0 && deltaYaw < 180 || deltaYaw < -180) {
			newYaw += angleTurn;
			if (newYaw > targetCompareYaw) newYaw = targetCompareYaw;
		}
		else if (deltaYaw < 0 || deltaYaw >= 180) {
			newYaw -= angleTurn;
			if (newYaw < targetCompareYaw) newYaw = targetCompareYaw;
		}
		
		Vector moveVector = lothricKnight.getMain().getLocation().getDirection().rotateAroundY(angleOffset).multiply(vectorScalar);
		Location newLocation = lothricKnight.getMain().getLocation();
			
		if (newLocation.clone().add(0,-1,0).getBlock().isPassable()) {
			newLocation = newLocation.clone().add(0,-1,0).getBlock().getLocation();
		}
		else {
			newLocation.setY(newLocation.clone().add(0,-1,0).getBlockY() + 1);
			newLocation.add(moveVector);
			
			if (!isPassableExtra(newLocation.clone().getBlock()) && 
					isPassableExtra(newLocation.clone().add(0,1,0).getBlock()) && 
					isPassableExtra(newLocation.clone().add(0,2,0).getBlock())) {
				newLocation = newLocation.clone().add(0,1,0);
			}
			else if (!isPassableExtra(newLocation.clone().getBlock())
					||!isPassableExtra(newLocation.clone().add(0,1,0).getBlock()) 
					||!isPassableExtra(newLocation.clone().add(0,2,0).getBlock())) {
				newLocation.subtract(moveVector);
				newLocation.add(moveVector.clone().setX(0));
				
				if (!isPassableExtra(newLocation.clone().getBlock()) 
						||!isPassableExtra(newLocation.clone().add(0,1,0).getBlock()) 
						||!isPassableExtra(newLocation.clone().add(0,2,0).getBlock())) {
					newLocation.subtract(moveVector.clone().setX(0));
				}
				newLocation.add(moveVector.clone().setZ(0));
				
					if (!isPassableExtra(newLocation.clone().getBlock()) 
							||!isPassableExtra(newLocation.clone().add(0,1,0).getBlock())  
						||!isPassableExtra(newLocation.clone().add(0,2,0).getBlock())) {
					newLocation.subtract(moveVector.clone().setZ(0));
				}
			}
			
			if (vectorScalar >= 2) {
				
				for (int vectorScales = 0; vectorScales < vectorScales; vectorScales++) {
					Vector moveVectorScaled = lothricKnight.getMain().getLocation().getDirection().rotateAroundY(angleOffset).multiply(vectorScales);
					Location newLocationScaled = lothricKnight.getMain().getLocation();
					
					if (newLocationScaled.clone().add(0,-1,0).getBlock().isPassable()) {
						newLocationScaled = newLocationScaled.clone().add(0,-1,0).getBlock().getLocation();
					}
					else {
						
						newLocationScaled.setY(newLocationScaled.clone().add(0,-1,0).getBlockY() + 1);
						
						newLocationScaled.add(moveVectorScaled);
						if (!isPassableExtra(newLocationScaled.clone().getBlock())
								&&isPassableExtra(newLocationScaled.clone().add(0,1,0).getBlock()) 
								&&isPassableExtra(newLocationScaled.clone().add(0,2,0).getBlock())) {
							newLocationScaled = newLocationScaled.clone().add(0,1,0);
						}
						else if (!isPassableExtra(newLocationScaled.clone().getBlock()) 
								||!isPassableExtra(newLocationScaled.clone().add(0,1,0).getBlock()) 
								||!isPassableExtra(newLocationScaled.clone().add(0,2,0).getBlock())) {
							newLocationScaled.subtract(moveVectorScaled);
							newLocationScaled.add(moveVectorScaled.clone().setX(0));
							if (!isPassableExtra(newLocationScaled.clone().getBlock())
									||!isPassableExtra(newLocationScaled.clone().add(0,1,0).getBlock()) 
									||!isPassableExtra(newLocationScaled.clone().add(0,2,0).getBlock())) {
								newLocationScaled.subtract(moveVectorScaled.clone().setX(0));
							}
							newLocationScaled.add(moveVectorScaled.clone().setZ(0));
							if (
									!isPassableExtra(newLocationScaled.clone().getBlock())
									||!isPassableExtra(newLocationScaled.clone().add(0,1,0).getBlock())
									||!isPassableExtra(newLocationScaled.clone().add(0,2,0).getBlock())) {
								newLocationScaled.subtract(moveVectorScaled.clone().setZ(0));
							}
							if (moveVectorScaled.getX() == 0 && moveVectorScaled.getZ() == 0) break;
							else newLocation = newLocationScaled;
						}
					}
				}
			}
			
			//Deep drop protection
			boolean isDeepDrop = true;
			
			for (int dropDepth = 1; dropDepth < 5; dropDepth++) {
				if (!newLocation.clone().subtract(0,dropDepth,0).getBlock().isPassable()) {
					isDeepDrop = false;
					break;
				}
			}
			
			if (isDeepDrop) newLocation = lothricKnight.getMain().getLocation();
		}
		
		if (newYaw > 0) newYaw -= 360;
		newYaw *= -1;
		newLocation.setDirection(new Vector(0,0,1).rotateAroundY(newYaw / 57.29));
		lothricKnight.getMain().teleport(newLocation.clone());
	}
	
	private boolean isPassableExtra(Block block) {
		List<Material> passables = new ArrayList<>();
		passables.add(Material.BLACK_CARPET);
		passables.add(Material.BLUE_CARPET);
		passables.add(Material.BROWN_CARPET);
		passables.add(Material.CYAN_CARPET);
		passables.add(Material.GRAY_CARPET);
		passables.add(Material.GREEN_CARPET);
		passables.add(Material.LIGHT_BLUE_CARPET);
		passables.add(Material.LIGHT_GRAY_CARPET);
		passables.add(Material.LIME_CARPET);
		passables.add(Material.MAGENTA_CARPET);
		passables.add(Material.ORANGE_CARPET);
		passables.add(Material.PINK_CARPET);
		passables.add(Material.PURPLE_CARPET);
		passables.add(Material.RED_CARPET);
		passables.add(Material.WHITE_CARPET);
		passables.add(Material.YELLOW_CARPET);
		passables.add(Material.SNOW);
		
		if (block.isPassable() || passables.contains(block.getType())) {
			return true;
		}
		return false;
	}
}
