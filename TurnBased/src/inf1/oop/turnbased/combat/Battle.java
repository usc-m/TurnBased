package inf1.oop.turnbased.combat;

import java.util.ArrayList;
import java.util.Random;

public class Battle {
	private Random rng;
	private ArrayList<CombatEntity> factionA, factionB;
	
	// added this so we can keep track of turns, but not sure if that's necessary for saving or not
	private ArrayList<Turn> turns;
	
	public Battle(CombatEntity a, CombatEntity b) {
		factionA = new ArrayList<CombatEntity>();
		factionA.add(a);
		
		factionB = new ArrayList<CombatEntity>();
		factionB.add(b);
		
		turns = new ArrayList<Turn>();
		
		rng = new Random();
	}
	
	public void applyTurn(Turn turn) {
		if(isValidTurn(turn)) {
			turns.add(turn);
			executeTurn(turn);
		}
		// TODO: Throw exception
	}

	private void executeTurn(Turn turn) {
		TurnAction action = turn.getAction();
		
		switch(action) {
			case ATTACK:
			CombatEntity src = turn.getSourceEntity();
			int damage = src.generateDamage(rng);
			
			CombatEntity tgt = turn.getTargetEntity();
			tgt.applyDamage(damage);
			break;
		}
		

		
	}

	private boolean isValidTurn(Turn turn) {
		CombatEntity src = turn.getSourceEntity();
		boolean aContainsSrc = factionA.contains(src);
		boolean srcExists = aContainsSrc || factionB.contains(src); // check that one of the teams contains the "source" entity
		
		CombatEntity tgt = turn.getTargetEntity();
		boolean aContainsTgt = factionA.contains(tgt);
		boolean tgtExists = aContainsTgt || factionB.contains(tgt); // check that one of the teams contains the "target" entity
		
		boolean differentTeams = (aContainsSrc && !aContainsTgt) || (!aContainsSrc && aContainsTgt); // check that the entities aren't both on the same team
		
		return srcExists && tgtExists && differentTeams;
	}
	
}
