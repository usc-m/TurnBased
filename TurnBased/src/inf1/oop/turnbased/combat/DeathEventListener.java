package inf1.oop.turnbased.combat;

// represents an object that wants to know when an entity dies
public interface DeathEventListener {
	void onEntityKilled(CombatEntity entity);
}
