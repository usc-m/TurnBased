package inf1.oop.turnbased.combat;

public class Turn {
	private CombatEntity sourceEntity, targetEntity;
	private TurnAction action;
	
	public Turn(CombatEntity src, TurnAction act, CombatEntity tgt) {
		sourceEntity = src;
		action = act;
		targetEntity = tgt;
	}

	public CombatEntity getSourceEntity() {
		return sourceEntity;
	}

	public CombatEntity getTargetEntity() {
		return targetEntity;
	}

	public TurnAction getAction() {
		return action;
	}
}
