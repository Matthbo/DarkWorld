package matthbo.mods.darkworld.init;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import matthbo.mods.darkworld.block.fluid.FluidDarkLava;
import matthbo.mods.darkworld.block.fluid.FluidDarkWater;
import matthbo.mods.darkworld.block.fluid.FluidDarkWorld;
import matthbo.mods.darkworld.handler.BucketHandler;
import matthbo.mods.darkworld.handler.EventHandler;
import matthbo.mods.darkworld.item.ItemBucketDarkWorld;
import matthbo.mods.darkworld.item.ItemDarkLavaBucket;
import matthbo.mods.darkworld.item.ItemDarkWaterBucket;
import matthbo.mods.darkworld.reference.Refs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids {
	
	private static FluidRegistry FR;
	private static FluidContainerRegistry FCR;
	private static GameRegistry GR;
	
	public static final Fluid darkWater = new Fluid("darkWater").setUnlocalizedName("darkwater").setLuminosity(15).setViscosity(6000);
	public static final Fluid darkLava = new Fluid("darkLava").setUnlocalizedName("darklava");

	public static FluidDarkWorld darkWaterBlock;
	public static FluidDarkWorld darkLavaBlock;
	
	public static ItemBucketDarkWorld darkWaterBucket = new ItemDarkWaterBucket(darkWaterBlock);
	public static ItemBucketDarkWorld darkLavaBucket = new ItemDarkLavaBucket(darkLavaBlock);
	
	public static void init(){
		FR.registerFluid(darkWater);
		FR.registerFluid(darkLava);
		
		darkWaterBlock = new FluidDarkWater(darkWater);
		darkLavaBlock = new FluidDarkLava(darkLava);
		
		GR.registerBlock(darkWaterBlock, "darkWaterBlock");
		GR.registerBlock(darkLavaBlock, "darkLavaBlock");
		
		GR.registerItem(darkWaterBucket, "darkWaterBucket");
		GR.registerItem(darkLavaBucket, "darkLavaBucket");
		
		FCR.registerFluidContainer(FluidRegistry.getFluidStack("darkwater", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(darkWaterBucket), new ItemStack(Items.bucket));
		FCR.registerFluidContainer(FluidRegistry.getFluidStack("darklava", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(darkLavaBucket), new ItemStack(Items.bucket));
		
		BucketHandler.INSTANCE.buckets.put(darkWaterBlock, darkWaterBucket);
		BucketHandler.INSTANCE.buckets.put(darkLavaBlock, darkLavaBucket);
	}
	
	

}
