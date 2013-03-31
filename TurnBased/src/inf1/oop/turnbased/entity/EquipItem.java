/*package inf1.oop.turnbased.entity;

//In order to prevent id collisions when adding new items later on, indexed them as follows:
//M.Weapon ID: 	100-149 (main hand)
//O.Weapon ID:	150-199 (off hand)
//top-armor ID:	200-299
//leg-armor ID:	300-399
//shoulders ID:	400-499
//vambraces ID: 500-599
//Feet		ID: 600-699

//note "weapons" are also armor.
public class EquipItem {
	private Entity player;
	
	
	//argument0 = item ID
	//argument1 = equip/unequip, equip=1; unequip=0;
	public EquipItem(int id, int equip)
	{
		ItemDatabase item = new ItemDatabase(id);
		if (equip == 1)
		{
			player.increaseMaxHp(item.getBonusHp());
			player.setDamage(item.getBonusDmg());
			player.setArmor(item.getBonusDef());
			
			//confirm player is actually wearing the item
			if (id>=100 && id<=149)
			{
				player.setMWeapon(id);
			}
			else if (id>=150 && id <= 199)
			{
				player.setOWeapon(id);
			}
			else if (id>=200 && id <= 299)
			{
				player.setTop(id);
			}
			else if (id >= 300 && id <= 399)
			{
				player.setLeg(id);
			}
			else if (id >= 400 && id <= 499)
			{
				player.setShoulder(id);
			}
			else if (id >= 500 && id <= 599)
			{
				player.setVambs(id);
			}
			else if (id >= 600 && id <= 699)
			{
				player.setFoot(id);
			}
			else
			{
				System.out.println("*Manic scream! - Error: Un/Equipping an item that DOES NOT EXIST!");
			}
		}
		else
		{
			//unequip item
			player.reduceMaxHp(item.getBonusHp());
			player.setDamage(-item.getBonusDmg());
			player.setArmor(-item.getBonusDef());
			
			//confirm player is actually UNEQUIPPING them
			if (id>=100 && id<=149)
			{
				player.setMWeapon(0);
			}
			else if (id>=150 && id <= 199)
			{
				player.setOWeapon(0);
			}
			else if (id>=200 && id <= 299)
			{
				player.setTop(id);
			}
			else if (id >= 300 && id <= 399)
			{
				player.setLeg(0);
			}
			else if (id >= 400 && id <= 499)
			{
				player.setShoulder(0);
			}
			else if (id >= 500 && id <= 599)
			{
				player.setVambs(0);
			}
			else if (id >= 600 && id <= 699)
			{
				player.setFoot(0);
			}
			else
			{
				System.out.println("*Manic scream! - Error: Un/Equipping an item that DOES NOT EXIST!");
			}
			
		}
	}
	

	

	
	
}*/
