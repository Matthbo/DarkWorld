package matthbo.mods.darkworld.block;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.client.particles.EntityDWPortalFX;
import matthbo.mods.darkworld.init.ModAchievements;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class BlockPortalDarkWord extends BlockPortalBaseDarkWorld {

	public BlockPortalDarkWord(){
		super();
		this.setUnlocalizedName("darkworldportal");
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity){
		
		if(entity.ridingEntity == null && entity.riddenByEntity ==  null && entity instanceof EntityPlayerMP){
			EntityPlayerMP player = (EntityPlayerMP) entity;
			FMLCommonHandler.instance().getMinecraftServerInstance();
			MinecraftServer server = MinecraftServer.getServer();
			
			if(player.timeUntilPortal > 0){
				player.timeUntilPortal = 300;
			}else if(player.dimension != ModDimensions.dimensionIDDarkWorld){
				player.timeUntilPortal = 300;
				player.addStat(ModAchievements.achEnterDarkWorld, 1);
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, ModDimensions.dimensionIDDarkWorld, new TeleporterDarkWord(server.worldServerForDimension(ModDimensions.dimensionIDDarkWorld)));
			}else{
				player.timeUntilPortal = 300;
				player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterDarkWord(server.worldServerForDimension(0)));
			}
			
			//entity.setInPortal();
		}
		
	}
	//using old way ;(
	@Override
	public boolean func_176548_d(World world, BlockPos pos){
		
		byte b = 0;
		byte b1 = 0;
        int x = pos.getX();
        int z = pos.getZ();
		
		if(world.getBlockState(pos.west()).getBlock() == ModBlocks.compressedPeculiarDust || world.getBlockState(pos.east()).getBlock() == ModBlocks.compressedPeculiarDust) b = 1;
		
		if(world.getBlockState(pos.north()).getBlock() == ModBlocks.compressedPeculiarDust || world.getBlockState(pos.south()).getBlock() == ModBlocks.compressedPeculiarDust) b1 = 1;
		
		if(b == b1) return false;
		else{
            BlockPos air = new BlockPos(pos.getX()-b, pos.getY(), pos.getZ()-b1);
			if(world.isAirBlock(air)){
				x-=b;
				z-=b1;
			}
			
			for(int i = -1; i <= 2; i++){
				for(int j = -1; j <=3; j++){
					boolean flag = (i == -1 || i == 2 || j == -1 || j == 3);
					
					if(i != -1 && i != 2 || j != -1 && j != 3){
                        BlockPos newPos = new BlockPos(x + (b*i), pos.getY()+j, z+(b1*i));
						Block k = world.getBlockState(newPos).getBlock();
                        air = new BlockPos(x + b * i, pos.getY() + j, z + b1 * i);
						boolean isAirBlock = world.isAirBlock(air);
						
						if(flag){
							if(k != ModBlocks.compressedPeculiarDust) return false;
						}else if(!isAirBlock && k != ModBlocks.darkFire){
							return false;
						}
					}
				}
			}
			for(int l = 0; l < 2; l++){
				for(int l2 = 0; l2 < 3; l2++){
                    BlockPos portal = new BlockPos(x + b * l, pos.getY() + l2, z + b1 * l);
					world.setBlockState(portal, ModBlocks.darkworldPortal.getDefaultState(), 2);
				}
			}
			
			return true;
			
		}
	}
	
	public void onNeighborBlockChange(World world, BlockPos pos, IBlockState state, Block neighborBlock){
		byte b0 = 0;
		byte b1 = 1;

		if(world.getBlockState(pos.down()).getBlock() == this || world.getBlockState(pos.up()).getBlock() == this){
			b0 = 1;
			b1 = 0;
		}
		
		int i1;
		
		for(i1 = pos.getY(); world.getBlockState(new BlockPos(pos.getX(), i1-1, pos.getZ())).getBlock() == this; i1--){

		}
		
		if(world.getBlockState(new BlockPos(pos.getX(), i1-1, pos.getZ())).getBlock() != ModBlocks.compressedPeculiarDust){
			world.setBlockToAir(pos);
		}else{
			int j1;
			
			for(j1 = 1; j1 < 4 && world.getBlockState(new BlockPos(pos.getX(), i1+j1, pos.getZ())).getBlock() == this; j1++){
				
			}
			
			if(j1 == 3 && world.getBlockState(new BlockPos(pos.getX(), i1+j1, pos.getZ())).getBlock() == ModBlocks.compressedPeculiarDust){
				boolean flag = world.getBlockState(new BlockPos(pos.getX()-1, pos.getY(), pos.getZ())).getBlock() == this || world.getBlockState(new BlockPos(pos.getX()+1, pos.getY(), pos.getZ())).getBlock() == this;
				boolean flag1 = world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()-1)).getBlock() == this || world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()+1)).getBlock() == this;
				
				if(flag && flag1){
					world.setBlockToAir(pos);
				}else{
					if((world.getBlockState(new BlockPos(pos.getX()+b0, pos.getY(), pos.getZ()+b1)).getBlock() != ModBlocks.compressedPeculiarDust || world.getBlockState(new BlockPos(pos.getX()-b0, pos.getY(), pos.getZ()-b1)).getBlock() != this) && (world.getBlockState(new BlockPos(pos.getX()-b0, pos.getY(), pos.getZ()-b1)).getBlock() != ModBlocks.compressedPeculiarDust || world.getBlockState(new BlockPos(pos.getX() + b0, pos.getY(), pos.getZ() + b1)).getBlock() != this)){
						world.setBlockToAir(pos);
					}
				}
			}else{
				world.setBlockToAir(pos);
			}
		}
	}

    //TODO: fix this
	/*@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        if (rand.nextInt(100) == 0)
        {
            world.playSound((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "portal.portal", 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
        }

        for (int l = 0; l < 4; ++l)
        {
            double d0 = (double)((float)x + rand.nextFloat());
            double d1 = (double)((float)y + rand.nextFloat());
            double d2 = (double)((float)z + rand.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = rand.nextInt(2) * 2 - 1;
            d3 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)rand.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)rand.nextFloat() - 0.5D) * 0.5D;

            if (world.getBlockState(new BlockPos(x - 1, y, z)) != this && world.getBlock(x + 1, y, z) != this)
            {
                d0 = (double)x + 0.5D + 0.25D * (double)i1;
                d3 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }
            else
            {
                d2 = (double)z + 0.5D + 0.25D * (double)i1;
                d5 = (double)(rand.nextFloat() * 2.0F * (float)i1);
            }

            //world.spawnParticle("portal", d0, d1, d2, d3, d4, d5);
            Minecraft.getMinecraft().effectRenderer.addEffect(new EntityDWPortalFX(world, d0, d1, d2, d3, d4, d5));
        }
    }*/
}
