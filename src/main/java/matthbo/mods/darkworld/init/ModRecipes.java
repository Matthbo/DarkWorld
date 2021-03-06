package matthbo.mods.darkworld.init;

import java.util.Random;

import matthbo.mods.darkworld.reference.MetaNames;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	
	private static GameRegistry GR;
	private static Random rand;
	
	public static void initCrafting(){
		
		GR.addRecipe(new ItemStack(ModBlocks.peculiarDustBlock), "ddd", "ddd", "ddd", 'd', new ItemStack(ModItems.peculiarDust));
		GR.addRecipe(new ItemStack(ModItems.peculiarDustBar), "dd", 'd', new ItemStack(ModItems.peculiarDust));
		GR.addRecipe(new ItemStack(ModItems.peculiarDustNSteel), "dn", 'd', new ItemStack(ModItems.hardPeculiarDust), 'n', new ItemStack(Items.flint));
		GR.addRecipe(new ItemStack(Items.bread), "www", 'w', new ItemStack(ModItems.darkWheat));
		
		GR.addRecipe(new ItemStack(ModItems.peculiarPickaxe), "ddd", " s ", " s ", 'd', new ItemStack(ModItems.hardPeculiarDust), 's', new ItemStack(Items.stick));
		GR.addRecipe(new ItemStack(ModItems.peculiarSword), " d ", " d ", " s ", 'd', new ItemStack(ModItems.hardPeculiarDust), 's', new ItemStack(Items.stick));
		GR.addRecipe(new ItemStack(ModItems.peculiarAxe), "dd ", "ds ", " s ", 'd', new ItemStack(ModItems.hardPeculiarDust), 's', new ItemStack(Items.stick));
		GR.addRecipe(new ItemStack(ModItems.peculiarShovel), " d ", " s ", " s ", 'd', new ItemStack(ModItems.hardPeculiarDust), 's', new ItemStack(Items.stick));
		GR.addRecipe(new ItemStack(ModItems.peculiarHoe), "dd ", " s ", " s ", 'd', new ItemStack(ModItems.hardPeculiarDust), 's', new ItemStack(Items.stick));
		
		GR.addRecipe(new ItemStack(ModItems.peculiarHelmet), "ddd", "d d", 'd', new ItemStack(ModItems.hardPeculiarDust));
		GR.addRecipe(new ItemStack(ModItems.peculiarChestplate), "d d", "ddd", "ddd", 'd', new ItemStack(ModItems.hardPeculiarDust));
		GR.addRecipe(new ItemStack(ModItems.peculiarLeggings), "ddd", "d d", "d d", 'd', new ItemStack(ModItems.hardPeculiarDust));
		GR.addRecipe(new ItemStack(ModItems.peculiarBoots), "d d", "d d", 'd', new ItemStack(ModItems.hardPeculiarDust));
		
	}
	
	public static void initSmelting(){
		for(int meta = 0; meta < MetaNames.blockDarkSand.length; meta++){GR.addSmelting(new ItemStack(ModBlocks.darkSand, 1, meta), new ItemStack(Blocks.glass), 0.1F);}
		
		GR.addSmelting(new ItemStack(ModBlocks.peculiarCobbleStone), new ItemStack(ModItems.peculiarDust, 2), 0.1F);
		GR.addSmelting(new ItemStack(ModItems.peculiarDustBar), new ItemStack(ModItems.hardPeculiarDust), 0.2F);

		GR.addSmelting(new ItemStack(ModBlocks.darkIronOre), new ItemStack(Items.iron_ingot), 0.2F);
		GR.addSmelting(new ItemStack(ModBlocks.darkGoldOre), new ItemStack(Items.gold_ingot), 0.2F);
		GR.addSmelting(new ItemStack(ModBlocks.darkDiamondOre), new ItemStack(Items.diamond), 0.2F);
		GR.addSmelting(new ItemStack(ModBlocks.darkEmeraldOre), new ItemStack(Items.emerald), 0.2F);

		GR.addSmelting(new ItemStack(ModBlocks.darkCactus), new ItemStack(Items.dye, 1, 2), 0F);
		
	}

}
