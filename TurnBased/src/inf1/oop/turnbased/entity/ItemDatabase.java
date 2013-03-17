package inf1.oop.turnbased.entity;

public class ItemDatabase {

	private int bonushp, bonusdmg, bonusdef;
	private String name, type, detail, info; //detail is created out of these variables when asked for. (see getDetail();)
	//In order to prevent id collisions when adding new items later on, indexed them as follows:
	//M.Weapon ID: 	100-149 (main hand)
	//O.Weapon ID:	150-199 (off hand)
	//top-armor ID:	200-299
	//leg-armor ID:	300-399
	//shoulders ID:	400-499
	//vambraces ID: 500-599
	//Feet		ID: 600-699
	public ItemDatabase(int id)
	{
		//equip item
		switch (id)
		{
			//MAIN WEAPON
			case 100: 
				bonushp=0;
				bonusdmg=3;
				bonusdef=0;
				name="Wooden Pillow";
				type="Main-hand Weapon";
				info="Beware of splinters!";
				break;
				
			case 101: 
				bonushp=0;
				bonusdmg=8;
				bonusdef=0;
				name="Rusty Dagger";
				type="Main-hand Weapon";
				info="Not very sharp.";
				break;
				
			//OFF WEAPON
			case 150: 
				bonushp=5;
				bonusdmg=1;
				bonusdef=1;
				name="Rubber Duck";
				type="Off-hand Weapon";
				info="Quack!";	
				break;	
			
			case 151:
				bonushp=11;
				bonusdmg=4;
				bonusdef=3;
				name="Deceiving Book";
				type="Off-hand Weapon";
				info="I don't really like reading.";	
				break;	
				
			//TOP ARMOR
			case 200:
				bonushp=10;
				bonusdmg=0;
				bonusdef=3;
				name="Invisible T-shirt";
				type="Body Armor";
				info="Is it even there?";	
				break;
				
			case 201:
				bonushp=22;
				bonusdmg=0;
				bonusdef=9;
				name="Snakeskin Body";
				type="Body Armor";
				info="This stuff feels funny :)";	
				break;
				
			//LEG ARMOR
			case 300:
				bonushp=4;
				bonusdmg=0;
				bonusdef=2;
				name="Leather Kilt";
				type="Leg Armor";
				info="Time to shave those legs!";	
				break;
				
			case 301:
				bonushp=11;
				bonusdmg=0;
				bonusdef=5;
				name="Knee Protectors";
				type="Leg Armor";
				info="These should keep me safe for a while.";	
				break;
				
			//SHOUDER ARMOR
			case 400: 
				bonushp=4;
				bonusdmg=0;
				bonusdef=1;
				name="Orange Feathers";
				type="Shoulder Protector";
				info="Pretty and soft feathers.";
				break;	
				
			case 401: 
				bonushp=9;
				bonusdmg=0;
				bonusdef=4;
				name="Leather Shoulderplates";
				type="Shoulder Protector";
				info="An animal used to wear these.";
				break;
				
			//VAMBRACE ARMOR
			case 500:
				bonushp=3;
				bonusdmg=0;
				bonusdef=1;
				name="Plastic Vambrace";
				type="Arm Gear";
				info="Made of the plastic from an old bottle.";
				break;
			case 501:
				bonushp=8;
				bonusdmg=0;
				bonusdef=3;
				name="Glossy Armprotectors";
				type="Arm Gear";
				info="Pretty and shiny in the sun.";
				break;
				
			//FOOT ARMOR
			case 600:
				bonushp=2;
				bonusdmg=0;
				bonusdef=0;
				name="Cardboard Slippers";
				type="Foot Gear";
				info="Don't make them wet!";
				break;			
			
			case 601:
				bonushp=5;
				bonusdmg=0;
				bonusdef=1;
				name="Decent Shoes";
				type="Foot Gear";
				info="Fine boots for a hike.";
				break;				
		}
	}
	
	//Return Methods
	public int getBonusHp()
	{
		return bonushp;
	}
	public int getBonusDmg()
	{
		return bonusdmg;
	}
	public int getBonusDef()
	{
		return bonusdef;
	}
	public String getName()
	{
		return name;
	}
	public String getType()
	{
		return type;
	}
	public String getDetail()
	{
		detail="";
		if (bonushp != 0) {detail += "+"+bonushp+"hp";}
		if (bonusdmg != 0) {detail += ", +"+bonusdmg+"dmg";}
		if (bonusdef != 0) {detail += ", +"+bonusdef+"def";}
		return detail;
	}
	public String getInfo()
	{
		return info;
	}
	
	
}
