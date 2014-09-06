package matthbo.mods.darkworld.block;

import cpw.mods.fml.common.FMLCommonHandler;
import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockPortalDarkWord extends BlockPortalMod {

	public BlockPortalDarkWord(){
		super();
		this.setBlockName("darkworldportal");
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
		
		if(entity.ridingEntity == null && entity.riddenByEntity ==  null && entity instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) entity;
			FMLCommonHandler.instance().getMinecraftServerInstance();
			MinecraftServer server = MinecraftServer.getServer();
			
			if(player.timeUntilPortal > 0) player.timeUntilPortal = 0;
			else if(player.dimension != DarkWorld.dimensionDarkWordID){
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, DarkWorld.dimensionDarkWordID, new TeleporterDarkWord(server.worldServerForDimension(DarkWorld.dimensionDarkWordID)));
			}else{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterDarkWord(server.worldServerForDimension(0)));
			}
		}
		
	}
	//using old way ;(
	@Override
	public boolean func_150000_e(World world, int x, int y, int z){
		
		byte b = 0;
		byte b1 = 0;
		
		if(world.getBlock(x-1, y, z) == ModBlocks.peculiarDustBlock || world.getBlock(x+1, y, z) == ModBlocks.peculiarDustBlock) b = 1;
		
		if(world.getBlock(x, y, z-1) == ModBlocks.peculiarDustBlock || world.getBlock(x, y, z+1) == ModBlocks.peculiarDustBlock) b1 = 1;
		
		if(b == b1) return false;
		else{
			if(world.isAirBlock(x-b, y, z-b1)){
				x-=b;
				z-=z;
			}
			
			for(int i = -1; i <= 2; i++){
				for(int j = -1; j <=3; j++){
					boolean flag = (i == -1 || i == 2 || j == -1 || j == 3);
					
					if(i != -1 && i != 2 || j != -1 && j != 3){
						Block k = world.getBlock(x + (b*i), y+j, z+(b1*i));
						
						if(k != ModBlocks.peculiarDustBlock) return false;
					}
				}
			}
		}
		
		return false;
	}
	
}
