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
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class BlockPortalDarkWord extends BlockPortalBaseDarkWorld {

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
			
			if(player.timeUntilPortal > 0){
				player.timeUntilPortal = 10;
			}else if(player.dimension != DarkWorld.dimensionDarkWordID){
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, DarkWorld.dimensionDarkWordID, new TeleporterDarkWord(server.worldServerForDimension(DarkWorld.dimensionDarkWordID)));
			}else{
				player.timeUntilPortal = 10;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterDarkWord(server.worldServerForDimension(0)));
			}
			
			//entity.setInPortal();
		}
		
	}
	//using old way ;(
	@Override
	public boolean func_150000_e(World world, int x, int y, int z){
		
		byte b = 0;
		byte b1 = 0;
		
		if(world.getBlock(x-1, y, z) == ModBlocks.peculiarStone || world.getBlock(x+1, y, z) == ModBlocks.peculiarStone) b = 1;
		
		if(world.getBlock(x, y, z-1) == ModBlocks.peculiarStone || world.getBlock(x, y, z+1) == ModBlocks.peculiarStone) b1 = 1;
		
		if(b == b1) return false;
		else{
			if(world.isAirBlock(x-b, y, z-b1)){
				x-=b;
				z-=b1;
			}
			
			for(int i = -1; i <= 2; i++){
				for(int j = -1; j <=3; j++){
					boolean flag = (i == -1 || i == 2 || j == -1 || j == 3);
					
					if(i != -1 && i != 2 || j != -1 && j != 3){
						Block k = world.getBlock(x + (b*i), y+j, z+(b1*i));
						boolean isAirBlock = world.isAirBlock(x + b * i, y + j, z + b1 * i);
						
						if(flag){
							if(k != ModBlocks.peculiarStone) return false;
						}else if(!isAirBlock && k != ModBlocks.darkFire){
							return false;
						}
					}
				}
			}
			for(int l = 0; l < 2; l++){
				for(int l2 = 0; l2 < 3; l2++){
					world.setBlock(x + b * l, y + l2, z + b1 * l, ModBlocks.darkworldPortal, 0, 2);
				}
			}
			
			return true;
			
		}
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighborBlock){
		byte b0 = 0;
		byte b1 = 1;
		
		if(world.getBlock(x-1, y, z) == this || world.getBlock(x+1, y, z) == this){
			b0 = 1;
			b1 = 0;
		}
		
		int i1;
		
		for(i1 = y; world.getBlock(x, i1-1, z) == this; i1--){
			
		}
		
		if(world.getBlock(x, i1-1, z) != ModBlocks.peculiarStone){
			world.setBlockToAir(x, y, z);
		}else{
			int j1;
			
			for(j1 = 1; j1 < 4 && world.getBlock(x, i1+j1, z) == this; j1++){
				
			}
			
			if(j1 == 3 && world.getBlock(x, i1+j1, z) == ModBlocks.peculiarStone){
				boolean flag = world.getBlock(x-1, y, z) == this || world.getBlock(x+1, y, z) == this;
				boolean flag1 = world.getBlock(x, y, z-1) == this || world.getBlock(x, y, z+1) == this;
				
				if(flag && flag1){
					world.setBlockToAir(x, y, z);
				}else{
					if((world.getBlock(x+b0, y, z+b1) != ModBlocks.peculiarStone || world.getBlock(x-b0, y, z-b1) != this) && (world.getBlock(x-b0, y, z-b1) != ModBlocks.peculiarStone || world.getBlock(x+b0, y, z+b1) != this)){
						world.setBlockToAir(x, y, z);
					}
				}
			}else{
				world.setBlockToAir(x, y, z);
			}
		}
	}
	
}
