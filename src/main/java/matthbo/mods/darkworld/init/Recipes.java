package matthbo.mods.darkworld.init;

import java.util.Random;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	
	private static GameRegistry GR;
	private static Random rand;
	
	public static void initCrafting(){
		
		GR.addRecipe(new ItemStack(ModBlocks.peculiarDustBlock), "ddd", "ddd", "ddd", 'd', new ItemStack(ModItems.peculiarDust));
		
	}
	
	public static void initSmelting(){
		
		GR.addSmelting(new ItemStack(ModBlocks.peculiarCobbleStone), new ItemStack(ModItems.peculiarDust, 2), 0.1F);
		
	}

}
