package matthbo.mods.darkworld.block.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class FluidDarkWater extends FluidDarkWorld {

	public FluidDarkWater(Fluid fluidName) {
		super(fluidName, Material.water);
		this.setUnlocalizedName("darkwater");
	}
	
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity){
		
		if(entity instanceof EntityLivingBase){
			EntityLivingBase living = (EntityLivingBase)entity;
			
			living.curePotionEffects(new ItemStack(Items.milk_bucket));
			
			living.addPotionEffect(new PotionEffect(Potion.weakness.id, 200));
			living.addPotionEffect(new PotionEffect(Potion.blindness.id, 80));
			living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 3));
		}
		entity.extinguish();
	}

}
