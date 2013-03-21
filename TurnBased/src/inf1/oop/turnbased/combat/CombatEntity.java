package inf1.oop.turnbased.combat;

import java.util.ArrayList;
import java.util.Random;

public class CombatEntity {
	private ArrayList<DeathEventListener> listeners;
	
	private int maxHp, currentHp;
	
	private boolean isDead;
	private boolean isDefending;
	
	// stats
	private int attack, defence, healFactor;
	
	// The % damage blocked per point of defense
	private static final double DEFENCE_EFFECTIVENESS=0.05;
	private static final double DEFENDING_MODIFIER=2; // How much defence is multiplied by when defending
	
	private static final double HEALING_EFFECTIVENESS=0.25; // how much each point of heal factor can contribute to the heal mutliplier
	private static final int BASE_HEALING=5; // the minimum amount healed
	
	public CombatEntity(int maxHp, int initialHp) {
		this.listeners = new ArrayList<DeathEventListener>();
		
		this.maxHp = maxHp;
		this.currentHp = initialHp;
	}
	
	public CombatEntity(int maxHp) {
		this(maxHp, maxHp);
	}
	
	public void defend() {
		isDefending = true;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void applyDamage(int dmg) {
		if(isDead) return; // beating a dead horse
		
		double reductionPct = (DEFENCE_EFFECTIVENESS*defence); // total percentage of damage blocked
		
		if(isDefending) {
			reductionPct *= DEFENDING_MODIFIER;
		}
		
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
	
	public void addDeathEventListener(DeathEventListener listener) {
		listeners.add(listener);
	}
	
	public int generateDamage(Random rng) {
		// TODO: Come up with a more fun damage generator, maybe add more stats or something
		return rng.nextInt(attack);
	}
	
	public int generateHealing(Random rng) {
		int maxHealMultiplier = (int)Math.round(HEALING_EFFECTIVENESS*healFactor);
		
		return BASE_HEALING * (1 + rng.nextInt(maxHealMultiplier)); // add 1 so the healing multiplier is never 0, so healing is always performed
	}
	
	private void die() {
		isDead = true;
		
		for(DeathEventListener listener : listeners) {
			listener.onEntityKilled(this);
		}
	}
}
