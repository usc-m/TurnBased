package inf1.oop.turnbased.entity;

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

	
	//argument0 = item ID
	//argument1 = equip/unequip, equip=1; unequip=0;
	public EquipItem(int id, int equip)
	{
		if (equip == 1)
		{
			//equip item
			switch (id)
			{
				//MAIN WEAPON
				case 100: 
					
					break;
					
				//OFF WEAPON
				case 150: 
						
					break;	
					
				//TOP ARMOR
				case 200:
					
					break;
					
				//LEG ARMOR
				case 300:
					
					break;
				//SHOUDER ARMOR
				case 400: 
						
					break;	
					
				//VAMBRACE ARMOR
				case 500:
					
					break;
					
				//FOOT ARMOR
				case 600:
					
					break;			
					
				
			}
			
		}
		else
		{
			//unequip item
			
		}
	}
	

	

	
	
}
