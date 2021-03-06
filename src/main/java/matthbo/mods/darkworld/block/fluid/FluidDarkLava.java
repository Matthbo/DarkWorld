package matthbo.mods.darkworld.block.fluid;

import matthbo.mods.darkworld.init.ModFluids;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

public class FluidDarkLava extends FluidDarkWorld {

	public FluidDarkLava(Fluid fluidName) {
		super(fluidName, Material.lava);
		this.setUnlocalizedName("darklava");
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, Entity entity) {
		if(entity instanceof EntityLivingBase){
			EntityLivingBase living = (EntityLivingBase)entity;
			
			living.addPotionEffect(new PotionEffect(Potion.weakness.id, 200));
			living.addPotionEffect(new PotionEffect(Potion.blindness.id, 80));
			living.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 3));
			living.addPotionEffect(new PotionEffect(Potion.poison.id, 600));
			living.addPotionEffect(new PotionEffect(Potion.wither.id, 20, 3));
		}
		entity.setFire(10);
		entity.attackEntityFrom(DamageSource.lava, 4.0F);
	}

}
