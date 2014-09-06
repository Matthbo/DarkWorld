package matthbo.mods.darkworld.init;

import cpw.mods.fml.common.registry.GameRegistry;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements {
	
	public static Achievement achPickupPCobbleStone = new Achievement(Refs.ACH_PICKUPPCOBBLESTONE, "pickuppcobblestone", 0, 0,new ItemStack(ModBlocks.peculiarStone), (Achievement)null);
	public static Achievement achSmeltPCobbleStone = new Achievement(Refs.ACH_SMELTPCOBBLESTONE, "smeltpcobblestone", 2, 0, new ItemStack(ModItems.peculiarDust), achPickupPCobbleStone);
	public static Achievement achCraftPTools = new Achievement(Refs.ACH_CRAFTPTOOLS, "craftptools", 2, 2, new ItemStack(ModItems.peculiarSword), achSmeltPCobbleStone); //TODO change pickaxe to sword
	public static Achievement achCraftPArmor = new Achievement(""/*Refs.ACH_CRAFTPARMOR*/, "craftparmor", 2, -2, new ItemStack((Item)null), achSmeltPCobbleStone);
	//TODO Make ^^^legit
	
	public static AchievementPage achPageDarkWorld = new AchievementPage("Dark World", new Achievement[]{achPickupPCobbleStone, achSmeltPCobbleStone, achCraftPTools});
	
	public static void init(){
		
		achPickupPCobbleStone.initIndependentStat().registerStat();
		achSmeltPCobbleStone.registerStat();
		achCraftPTools.registerStat();
		
		AchievementPage.registerAchievementPage(achPageDarkWorld);
		
	}

}
