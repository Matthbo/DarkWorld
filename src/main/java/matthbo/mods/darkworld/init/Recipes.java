package matthbo.mods.darkworld.init;

import java.util.Random;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {
	
	private static GameRegistry GR;
	private static Random rand;
	
	public static void initCrafting(){
		
		GR.addRecipe(new ItemStack(ModBlocks.peculiarDustBlock), "ddd", "ddd", "ddd", 'd', new ItemStack(ModItems.peculiarDust));
		GR.addRecipe(new ItemStack(ModItems.peculiarDustBar), "dd", 'd', new ItemStack(ModItems.peculiarDust));
		GR.addRecipe(new ItemStack(ModItems.peculiarDustNSteel), "dn", 'd', new ItemStack(ModItems.hardPeculiarDust), 'n', new ItemStack(Items.flint));
		
	}
	
	public static void initSmelting(){
		
		GR.addSmelting(new ItemStack(ModBlocks.peculiarCobbleStone), new ItemStack(ModItems.peculiarDust, 2), 0.1F);
		GR.addSmelting(new ItemStack(ModItems.peculiarDustBar), new ItemStack(ModItems.hardPeculiarDust), 0.1F);
		
	}

}
