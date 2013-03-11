package inf1.oop.turnbased.combat;

import java.util.Random;

public class CombatEntity {
	private int maxHp, currentHp;
	
	private boolean isDead;
	
	// stats
	private int attack, defence;
	
	// The % damage blocked per point of defense
	private static final double DEFENCE_EFFECTIVENESS=0.05;
	
	public CombatEntity(int maxHp, int initialHp) {
		this.maxHp = maxHp;
		this.currentHp = initialHp;
	}
	
	public CombatEntity(int maxHp) {
		this(maxHp, maxHp);
	}
	
	public void applyDamage(int dmg) {
		if(isDead) return; // beating a dead horse
		
		double reductionPct = (DEFENCE_EFFECTIVENESS*defence); // total percentage of damage blocked
		currentHp -= Math.round(dmg * reductionPct);
		
		if(currentHp < 1) {
			this.die();
		}
	}
	
	public void applyHealing(int heal) {
		if(isDead) return; // necromancy not allowed
		
		int total = currentHp + heal;
		
		if(total > maxHp) { 
			currentHp = maxHp;
		} else {
			currentHp = total;
		}
	}
	
	public int generateDamage(Random rng) {
		// TODO: Come up with a more fun damage generator, maybe add more stats or something
		return rng.nextInt(attack);
	}
	
	// TODO: Add death event handler
	
	private void die() {
		isDead = true;
		// TODO: Fire death event
	}
}
