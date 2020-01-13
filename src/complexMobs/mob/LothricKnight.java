package complexMobs.mob;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import complexMobs.main.ComplexMobs;
import complexMobs.mob.lothicKnight.AttackSword;
import complexMobs.mob.lothicKnight.Build;
import complexMobs.mob.lothicKnight.Move;
import complexMobs.mob.lothicKnight.Run;
import complexMobs.object.Part;
import complexMobs.object.Weapon;
import complexMobs.template.SoulsKnight;

public class LothricKnight extends SoulsKnight {

	/**
	* Creates a LothricKnight object
	*/
	public LothricKnight(Vector post) {
		super("LothricKnight", "Lothric Knight", 100, 30, 30, post, new Weapon(), new Weapon());
	}
	
	public void attackFrameSword(double damage, Vector knockBack, boolean particles) {
		new AttackSword().run(this, damage, knockBack, particles);
	}

	public void attackFrameShield() {
		
	}

	public void run() {
		Run run= new Run();
		run.run(this, ComplexMobs.getProvidingPlugin(ComplexMobs.class));
	}

	public void build(Location spawnLocation) {
		Build build = new Build();
		build.run(this, spawnLocation);
	}
	
	public void remove() {
		for (Part part : this.getParts().values()) {
			part.getArmorStand().remove();
		}
		this.getMain().remove();
		this.setRemoved(true);
		this.setDead(true);
	}
	
	@Override
	public void move(double vectorScalar, double angleTurn, double angleOffset) {
		new Move().run(this, vectorScalar, angleTurn, angleOffset);
	}
	
}
