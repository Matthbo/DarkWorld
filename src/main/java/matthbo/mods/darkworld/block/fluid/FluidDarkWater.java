package matthbo.mods.darkworld.block.fluid;

import matthbo.mods.darkworld.init.ModFluids;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class FluidDarkWater extends FluidDarkWorld {

	public FluidDarkWater(Fluid fluidName) {
		super(fluidName, Material.water);
		this.setBlockName("darkwater");
	}
	
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
		
		if(entity instanceof EntityLivingBase){
			EntityLivingBase living = (EntityLivingBase)entity;
			
			living.curePotionEffects(living.getHeldItem());
			
			living.addPotionEffect(new PotionEffect(Potion.weakness.id, 200));
			living.addPotionEffect(new PotionEffect(Potion.blindness.id, 80));
			living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 3));
		}
		entity.extinguish();
	}

}
