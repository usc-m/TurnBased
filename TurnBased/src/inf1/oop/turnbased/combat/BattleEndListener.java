package inf1.oop.turnbased.combat;

// represents an object that wants to know when a battle has ended
public interface BattleEndListener {
	void onBattleEnd(BattleEndCondition cond);
}
