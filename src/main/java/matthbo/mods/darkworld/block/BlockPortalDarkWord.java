package matthbo.mods.darkworld.block;

import java.util.Random;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import matthbo.mods.darkworld.DarkWorld;
import matthbo.mods.darkworld.client.particles.EntityDWPortalFX;
import matthbo.mods.darkworld.init.ModAchievements;
import matthbo.mods.darkworld.init.ModBlocks;
import matthbo.mods.darkworld.init.ModDimensions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
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
	public boolean func_150000_e(World world, int x, int y, int z){
		
		byte b = 0;
		byte b1 = 0;
		
		if(world.getBlock(x-1, y, z) == ModBlocks.compressedPeculiarDust || world.getBlock(x+1, y, z) == ModBlocks.compressedPeculiarDust) b = 1;
		
		if(world.getBlock(x, y, z-1) == ModBlocks.compressedPeculiarDust || world.getBlock(x, y, z+1) == ModBlocks.compressedPeculiarDust) b1 = 1;
		
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
							if(k != ModBlocks.compressedPeculiarDust) return false;
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
		
		if(world.getBlock(x, i1-1, z) != ModBlocks.compressedPeculiarDust){
			world.setBlockToAir(x, y, z);
		}else{
			int j1;
			
			for(j1 = 1; j1 < 4 && world.getBlock(x, i1+j1, z) == this; j1++){
				
			}
			
			if(j1 == 3 && world.getBlock(x, i1+j1, z) == ModBlocks.compressedPeculiarDust){
				boolean flag = world.getBlock(x-1, y, z) == this || world.getBlock(x+1, y, z) == this;
				boolean flag1 = world.getBlock(x, y, z-1) == this || world.getBlock(x, y, z+1) == this;
				
				if(flag && flag1){
					world.setBlockToAir(x, y, z);
				}else{
					if((world.getBlock(x+b0, y, z+b1) != ModBlocks.compressedPeculiarDust || world.getBlock(x-b0, y, z-b1) != this) && (world.getBlock(x-b0, y, z-b1) != ModBlocks.compressedPeculiarDust || world.getBlock(x+b0, y, z+b1) != this)){
						world.setBlockToAir(x, y, z);
					}
				}
			}else{
				world.setBlockToAir(x, y, z);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
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

            if (world.getBlock(x - 1, y, z) != this && world.getBlock(x + 1, y, z) != this)
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
    }
	
	//doesn't work because mojang hates making shit easy
	/*public boolean func_150000_e(World world, int x, int y, int z)
    {
        Size size = new Size(world, x, y, z, 1);
        Size size1 = new Size(world, x, y, z, 2);

        if (size.func_150860_b() && size.e == 0)
        {
            size.func_150859_c();
            return true;
        }
        else if (size1.func_150860_b() && size1.e == 0)
        {
            size1.func_150859_c();
            return true;
        }
        else
        {
            return false;
        }
    }*/
	
	public static class Size{
		private final World world;
        private final int b;
        private final int c;
        private final int d;
        private int e = 0;
        private ChunkCoordinates chunkCoords;
        private int g;
        private int h;

        public Size(World world, int x, int y, int z, int par5)
        {
            this.world = world;
            this.b = par5;
            this.d = BlockPortalBaseDarkWorld.field_150001_a[par5][0];
            this.c = BlockPortalBaseDarkWorld.field_150001_a[par5][1];

            for (int i1 = y; y > i1 - 21 && y > 0 && this.func_150857_a(world.getBlock(x, y - 1, z)); --y)
            {
                ;
            }

            int j1 = this.func_150853_a(x, y, z, this.d) - 1;

            if (j1 >= 0)
            {
                this.chunkCoords = new ChunkCoordinates(x + j1 * Direction.offsetX[this.d], y, z + j1 * Direction.offsetZ[this.d]);
                this.h = this.func_150853_a(this.chunkCoords.posX, this.chunkCoords.posY, this.chunkCoords.posZ, this.c);

               /* if (this.h < 2 || this.h > 21)
                {
                    this.chunkCoords = null;
                    this.h = 0;
                }*/
            }

            if (this.chunkCoords != null)
            {
                this.g = this.func_150858_a();
            }
        }

        protected int func_150853_a(int x, int y, int z, int par4)
        {
            int j1 = Direction.offsetX[par4];
            int k1 = Direction.offsetZ[par4];
            int i1;
            Block block;

            for (i1 = 0; i1 < 22; ++i1)
            {
                block = this.world.getBlock(x + j1 * i1, y, z + k1 * i1);

                if (!this.func_150857_a(block))
                {
                    break;
                }

                Block block1 = this.world.getBlock(x + j1 * i1, y - 1, z + k1 * i1);

                if (block1 != ModBlocks.compressedPeculiarDust)
                {
                    break;
                }
            }

            block = this.world.getBlock(x + j1 * i1, y, z + k1 * i1);
            return block == ModBlocks.compressedPeculiarDust ? i1 : 0;
        }

        protected int func_150858_a()
        {
            int i;
            int j;
            int k;
            int l;
            label56:

            for (this.g = 0; this.g < 21; ++this.g)
            {
                i = this.chunkCoords.posY + this.g;

                for (j = 0; j < this.h; ++j)
                {
                    k = this.chunkCoords.posX + j * Direction.offsetX[BlockPortalBaseDarkWorld.field_150001_a[this.b][1]];
                    l = this.chunkCoords.posZ + j * Direction.offsetZ[BlockPortalBaseDarkWorld.field_150001_a[this.b][1]];
                    Block block = this.world.getBlock(k, i, l);

                    if (!this.func_150857_a(block))
                    {
                        break label56;
                    }

                    if (block == ModBlocks.compressedPeculiarDust)
                    {
                        ++this.e;
                    }

                    if (j == 0)
                    {
                        block = this.world.getBlock(k + Direction.offsetX[BlockPortalBaseDarkWorld.field_150001_a[this.b][0]], i, l + Direction.offsetZ[BlockPortalBaseDarkWorld.field_150001_a[this.b][0]]);

                        if (block != ModBlocks.compressedPeculiarDust)
                        {
                            break label56;
                        }
                    }
                    else if (j == this.h - 1)
                    {
                        block = this.world.getBlock(k + Direction.offsetX[BlockPortalBaseDarkWorld.field_150001_a[this.b][1]], i, l + Direction.offsetZ[BlockPortalBaseDarkWorld.field_150001_a[this.b][1]]);

                        if (block != ModBlocks.compressedPeculiarDust)
                        {
                            break label56;
                        }
                    }
                }
            }

            for (i = 0; i < this.h; ++i)
            {
                j = this.chunkCoords.posX + i * Direction.offsetX[BlockPortalBaseDarkWorld.field_150001_a[this.b][1]];
                k = this.chunkCoords.posY + this.g;
                l = this.chunkCoords.posZ + i * Direction.offsetZ[BlockPortalBaseDarkWorld.field_150001_a[this.b][1]];

                if (this.world.getBlock(j, k, l) != ModBlocks.compressedPeculiarDust)
                {
                    this.g = 0;
                    break;
                }
            }

            if (this.g <= 21 && this.g >= 3)
            {
                return this.g;
            }
            else
            {
                this.chunkCoords = null;
                this.h = 0;
                this.g = 0;
                return 0;
            }
        }

        protected boolean func_150857_a(Block p_150857_1_)
        {
            return p_150857_1_.getMaterial() == Material.air || p_150857_1_ == ModBlocks.darkFire || p_150857_1_ == ModBlocks.compressedPeculiarDust;
        }

        public boolean func_150860_b()
        {
            return this.chunkCoords != null && this.h >= 2 && this.h <= 21 && this.g >= 3 && this.g <= 21;
        }

        public void func_150859_c()
        {
            for (int i = 0; i < this.h; ++i)
            {
                int j = this.chunkCoords.posX + Direction.offsetX[this.c] * i;
                int k = this.chunkCoords.posZ + Direction.offsetZ[this.c] * i;

                for (int l = 0; l < this.g; ++l)
                {
                    int i1 = this.chunkCoords.posY + l;
                    this.world.setBlock(j, i1, k, ModBlocks.darkworldPortal, this.b, 2);
                }
            }
        }
	}
	
}
