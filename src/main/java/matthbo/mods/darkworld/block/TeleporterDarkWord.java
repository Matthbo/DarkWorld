package matthbo.mods.darkworld.block;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import matthbo.mods.darkworld.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class TeleporterDarkWord extends Teleporter {
	
	private final WorldServer WSInstance;
	private final Random rand;
	
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List destinationCoordinateKeys = new ArrayList();

	public TeleporterDarkWord(WorldServer p_i1963_1_) {
		super(p_i1963_1_);
		
		
		this.WSInstance = p_i1963_1_;
		this.rand = new Random(p_i1963_1_.getSeed()); // may use the seed instead of a random....
	}
	
	public void placeInPortal(Entity entity, double x, double y, double z, float f){
		
		if(this.WSInstance.provider.dimensionId != 1){
			if(!this.placeInExistingPortal(entity, x, y, z, f)){
				this.makePortal(entity);
				this.placeInExistingPortal(entity, x, y, z, f);
			}
		}else{
			int entity_x = MathHelper.floor_double(entity.posX);	
			int entity_y = MathHelper.floor_double(entity.posY);	
			int entity_z = MathHelper.floor_double(entity.posZ);	
			byte b0 = 1;
			byte b1 = 0;
			
			for(int i = -2; i <= 2; i++){
				for(int j = -2; j <= 2; j++){
					for(int k = -1; k < 3; k++){
						int x2 = entity_x + j * b0 + i * b1;
						int y2 = entity_y + k;
						int z2 = entity_z + j * b1 - i * b0;
						boolean flag = k < 0;
						this.WSInstance.setBlock(x2, y2, z2, flag ? ModBlocks.peculiarStone : Blocks.air);
					}
				}
			}
			
			entity.setLocationAndAngles(entity_x, entity_y, entity_z, entity.rotationYaw, 0);
			entity.motionX = entity.motionY = entity.motionZ = 0D;
		}
		
	}
	
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float f){
		short short1 = 128;
        double d3 = -1.0D;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = MathHelper.floor_double(entity.posX);
        int i1 = MathHelper.floor_double(entity.posZ);
        long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
        boolean flag = true;
        double d7;
        int l3;

        if (this.destinationCoordinateCache.containsItem(j1))
        {
        	TeleporterDarkWord.PortalPosition portalposition = (TeleporterDarkWord.PortalPosition)this.destinationCoordinateCache.getValueByKey(j1);
            d3 = 0.0D;
            i = portalposition.posX;
            j = portalposition.posY;
            k = portalposition.posZ;
            portalposition.lastUpdateTime = this.WSInstance.getTotalWorldTime();
            flag = false;
        }
        else
        {
            for (l3 = l - short1; l3 <= l + short1; ++l3)
            {
                double d4 = (double)l3 + 0.5D - entity.posX;

                for (int l1 = i1 - short1; l1 <= i1 + short1; ++l1)
                {
                    double d5 = (double)l1 + 0.5D - entity.posZ;

                    for (int i2 = this.WSInstance.getActualHeight() - 1; i2 >= 0; --i2)
                    {
                        if (this.WSInstance.getBlock(l3, i2, l1) == ModBlocks.darkworldPortal)
                        {
                            while (this.WSInstance.getBlock(l3, i2 - 1, l1) == ModBlocks.darkworldPortal)
                            {
                                --i2;
                            }

                            d7 = (double)i2 + 0.5D - entity.posY;
                            double d8 = d4 * d4 + d7 * d7 + d5 * d5;

                            if (d3 < 0.0D || d8 < d3)
                            {
                                d3 = d8;
                                i = l3;
                                j = i2;
                                k = l1;
                            }
                        }
                    }
                }
            }
        }

        if (d3 >= 0.0D)
        {
            if (flag)
            {
                this.destinationCoordinateCache.add(j1, new TeleporterDarkWord.PortalPosition(i, j, k, this.WSInstance.getTotalWorldTime()));
                this.destinationCoordinateKeys.add(Long.valueOf(j1));
            }

            double d11 = (double)i + 0.5D;
            double d6 = (double)j + 0.5D;
            d7 = (double)k + 0.5D;
            int i4 = -1;

            if (this.WSInstance.getBlock(i - 1, j, k) == ModBlocks.darkworldPortal)
            {
                i4 = 2;
            }

            if (this.WSInstance.getBlock(i + 1, j, k) == ModBlocks.darkworldPortal)
            {
                i4 = 0;
            }

            if (this.WSInstance.getBlock(i, j, k - 1) == ModBlocks.darkworldPortal)
            {
                i4 = 3;
            }

            if (this.WSInstance.getBlock(i, j, k + 1) == ModBlocks.darkworldPortal)
            {
                i4 = 1;
            }

            int j2 = entity.getTeleportDirection();

            if (i4 > -1)
            {
                int k2 = Direction.rotateLeft[i4];
                int l2 = Direction.offsetX[i4];
                int i3 = Direction.offsetZ[i4];
                int j3 = Direction.offsetX[k2];
                int k3 = Direction.offsetZ[k2];
                boolean flag1 = !this.WSInstance.isAirBlock(i + l2 + j3, j, k + i3 + k3) || !this.WSInstance.isAirBlock(i + l2 + j3, j + 1, k + i3 + k3);
                boolean flag2 = !this.WSInstance.isAirBlock(i + l2, j, k + i3) || !this.WSInstance.isAirBlock(i + l2, j + 1, k + i3);

                if (flag1 && flag2)
                {
                    i4 = Direction.rotateOpposite[i4];
                    k2 = Direction.rotateOpposite[k2];
                    l2 = Direction.offsetX[i4];
                    i3 = Direction.offsetZ[i4];
                    j3 = Direction.offsetX[k2];
                    k3 = Direction.offsetZ[k2];
                    l3 = i - j3;
                    d11 -= (double)j3;
                    int k1 = k - k3;
                    d7 -= (double)k3;
                    flag1 = !this.WSInstance.isAirBlock(l3 + l2 + j3, j, k1 + i3 + k3) || !this.WSInstance.isAirBlock(l3 + l2 + j3, j + 1, k1 + i3 + k3);
                    flag2 = !this.WSInstance.isAirBlock(l3 + l2, j, k1 + i3) || !this.WSInstance.isAirBlock(l3 + l2, j + 1, k1 + i3);
                }

                float f1 = 0.5F;
                float f2 = 0.5F;

                if (!flag1 && flag2)
                {
                    f1 = 1.0F;
                }
                else if (flag1 && !flag2)
                {
                    f1 = 0.0F;
                }
                else if (flag1 && flag2)
                {
                    f2 = 0.0F;
                }

                d11 += (double)((float)j3 * f1 + f2 * (float)l2);
                d7 += (double)((float)k3 * f1 + f2 * (float)i3);
                float f3 = 0.0F;
                float f4 = 0.0F;
                float f5 = 0.0F;
                float f6 = 0.0F;

                if (i4 == j2)
                {
                    f3 = 1.0F;
                    f4 = 1.0F;
                }
                else if (i4 == Direction.rotateOpposite[j2])
                {
                    f3 = -1.0F;
                    f4 = -1.0F;
                }
                else if (i4 == Direction.rotateRight[j2])
                {
                    f5 = 1.0F;
                    f6 = -1.0F;
                }
                else
                {
                    f5 = -1.0F;
                    f6 = 1.0F;
                }

                double d9 = entity.motionX;
                double d10 = entity.motionZ;
                entity.motionX = d9 * (double)f3 + d10 * (double)f6;
                entity.motionZ = d9 * (double)f5 + d10 * (double)f4;
                entity.rotationYaw = f - (float)(j2 * 90) + (float)(i4 * 90);
            }
            else
            {
                entity.motionX = entity.motionY = entity.motionZ = 0.0D;
            }

            entity.setLocationAndAngles(d11, d6, d7, entity.rotationYaw, entity.rotationPitch);
            return true;
        }
        else
        {
            return false;
        }
	}
	
	public boolean makePortal(Entity entity){
		/*byte b0 = 16;
		double d0 = -1D;
		int i = MathHelper.floor_double(entity.posX);//Start X
		int j = MathHelper.floor_double(entity.posY);//Start Y
		int k = MathHelper.floor_double(entity.posZ);//Start Z
		int l = i;
		int m = j;
		int n = k;
		int o = 0;
		int p = this.rand.nextInt(4);
		int q;//X
		double d1;//X
		double d2;//Z
		int i2;//Z
		int j2;//Y
		int k2;
		int l2;
		int m2;
		int n2;
		int o2;
		int p2;
		int q2;//Y
		int r2;//X
		double d3;
		double d4;//Y
		
		for(q = i-b0; q <= i+b0; q++){
			d1 = (double)q+0.5D - entity.posX;
			for(i2 = k-b0; i2 <= k+b0; i2++){
				d2 = (double)i2+0.5D - entity.posZ;
				labek274:
				
				for(j2 = this.WSInstance.getActualHeight() - 1; j2 >= 0; j2--){
					if(this.WSInstance.isAirBlock(q, j2, i2)){
						while(j2 > 0 && this.WSInstance.isAirBlock(p, j2-1, i2)){
							j2--;
						}
						
						for(l2 = p; l2 < p+4; l2++){
							k2 = l2%2;
							n2 = 1-k2;
							
							if(l2%4 >= 2){
								k2 = -k2;
								n2 = -n2;
							}
							
							for(m2 = 0; m2 < 3; m2++){
								for(p2 = 0; p2 < 4; p2++){
									for(o2 = -1; o2 < 4; o2++){
										r2 = q + (p2-1)*k2 + m2*n2;
										q2 = j2+o2;
										int l4 = i2 + (p2-1)*n2 - m2*k2;//Z
										
										if(o2 < 0 && !this.WSInstance.getBlock(r2, q2, l4).getMaterial().isSolid() || o2 >= 0 && !this.WSInstance.isAirBlock(r2, q2, l4)){
											continue labek274;
										}
									}
								}
							}
							
							d4 = (double)k2 + 0.5D - entity.posY;
							d3 = d1*d1 + d4*d4 + d2*d2;
							
							if(d0 < 0 || d3 < d0){
								d0 = d3;
								l = i2;
								m = j2;
								n = i2;
								o = l2%4;
							}
						}
					}
				}
			}
			
			if(d0 < 0){
				for(q = i-b0; q <= i+b0; q++){
					d1 = (double)q + 0.5D - entity.posX;
					
					for(i2 = k - b0; i2 <= k+b0; i2++){
						d2 = (double)i2 + 0.5D - entity.posZ;
						label222:
						
						for(j2 = this.WSInstance.getActualHeight() - 1; j2 >= 0; j2--){
							if(this.WSInstance.isAirBlock(q, i2, j2)){
								while(j2 > 0 && this.WSInstance.isAirBlock(q, i2-1, j2)){
									j2--;
								}
								
								for(l2 = p; l2 < p + 2; l2++){
									k2 = l2%2;
									n2 = 1-k2;
									
									for(m2 = 0; m2 < 4; m2++){
										for(p2 = -1; p2 < 4; p2++){
											o2 = q + (m2-1)*k2;
											r2 = j2 + p2;
											q2 = i2 + (m2-1)*n2;
											
											if(p2 < 0 && !this.WSInstance.getBlock(o2, r2 , q2).getMaterial().isSolid() || p2 >= 0 && !this.WSInstance.isAirBlock(o2, r2, q2)){
												continue label222;
											}
										}
									}
									
									d4 = (double)j2 + 0.5D - entity.posY;
									d3 = d1*d1+d4*d4+d2*d2;
									
									if(d0 < 0D || d3 < d0){
										d0 = d3;
										l = i2;
										m = j2;
										n = i2;
										o = l2%2;
									}
								}
							}
						}
					}
				}
			}
		}
		
		int i5 = l;
		int j5 = m;
		i2 = n;
		int k5 = o%2;
		int l5 = 1-k5;
		
		if(o%4 >= 2){
			k5 = -k5;
			l5 = -l5;
		}
		
		boolean flag;
		
		if(d0 < 0D){
			if(m < 70){
				m = 70;
			}
			
			if(m > this.WSInstance.getActualHeight() -10){
				m = this.WSInstance.getActualHeight() - 10;
			}
			
			j5 = m;
			
			for(j2 = -1; j2 <= 1; j2++){
				for(o = 1; o < 3; o++){
					for(k2 = -1; k2 < 3; k2++){
						n2 = j5 + (o-1)*k5+j2*l5;
						m2 = j5+k2;
						p2 = i2 + (o-1)*l5-j2*k5;
						flag = k2 < 0;
						this.WSInstance.setBlock(n2, m2, p2, flag?ModBlocks.peculiarStone:Blocks.air);
					}
				}
			}
		}
		
		for(j2 = 0; j2 <= 4; j2++){
			for(o = 0; o < 4; o++){
				for(k2 = -1; k2 < 4; k2++){
					n2 = j5 + (o-1)*k5;
					m2 = j5+k2;
					p2 = i2 + (o-1)*l5;
					flag = o==0 || 0==3 || k2==-1 || k2==3;
					this.WSInstance.setBlock(n2, m2, p2, flag?ModBlocks.peculiarStone:ModBlocks.darkworldPortal, 0, 2);
				}
			}
			
			for(o = 0; o < 4; o++){
				for(k2 = -1; k2 < 4; k2++){
					n2 = j5 + (o-1)*k5;
					m2 = j5+k2;
					p2 = i2 + (o-1)*l5;
					this.WSInstance.notifyBlockOfNeighborChange(n2, m2, p2, this.WSInstance.getBlock(n2, m2, p2));;
				}
			}
		}
		
		return true;*/
		
		byte b0 = 16;
        double d0 = -1.0D;
        int i = MathHelper.floor_double(entity.posX);
        int j = MathHelper.floor_double(entity.posY);
        int k = MathHelper.floor_double(entity.posZ);
        int l = i;
        int i1 = j;
        int j1 = k;
        int k1 = 0;
        int l1 = this.rand.nextInt(4);
        int i2;
        double d1;
        int k2;
        double d2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        int l4;
        int i5;
        double d3;
        double d4;

        for (i2 = i - b0; i2 <= i + b0; ++i2)
        {
            d1 = (double)i2 + 0.5D - entity.posX;

            for (k2 = k - b0; k2 <= k + b0; ++k2)
            {
                d2 = (double)k2 + 0.5D - entity.posZ;
                label274:

                for (i3 = this.WSInstance.getActualHeight() - 1; i3 >= 0; --i3)
                {
                    if (this.WSInstance.isAirBlock(i2, i3, k2))
                    {
                        while (i3 > 0 && this.WSInstance.isAirBlock(i2, i3 - 1, k2))
                        {
                            --i3;
                        }

                        for (j3 = l1; j3 < l1 + 4; ++j3)
                        {
                            k3 = j3 % 2;
                            l3 = 1 - k3;

                            if (j3 % 4 >= 2)
                            {
                                k3 = -k3;
                                l3 = -l3;
                            }

                            for (i4 = 0; i4 < 3; ++i4)
                            {
                                for (j4 = 0; j4 < 4; ++j4)
                                {
                                    for (k4 = -1; k4 < 4; ++k4)
                                    {
                                        l4 = i2 + (j4 - 1) * k3 + i4 * l3;
                                        i5 = i3 + k4;
                                        int j5 = k2 + (j4 - 1) * l3 - i4 * k3;

                                        if (k4 < 0 && !this.WSInstance.getBlock(l4, i5, j5).getMaterial().isSolid() || k4 >= 0 && !this.WSInstance.isAirBlock(l4, i5, j5))
                                        {
                                            continue label274;
                                        }
                                    }
                                }
                            }

                            d3 = (double)i3 + 0.5D - entity.posY;
                            d4 = d1 * d1 + d3 * d3 + d2 * d2;

                            if (d0 < 0.0D || d4 < d0)
                            {
                                d0 = d4;
                                l = i2;
                                i1 = i3;
                                j1 = k2;
                                k1 = j3 % 4;
                            }
                        }
                    }
                }
            }
        }

        if (d0 < 0.0D)
        {
            for (i2 = i - b0; i2 <= i + b0; ++i2)
            {
                d1 = (double)i2 + 0.5D - entity.posX;

                for (k2 = k - b0; k2 <= k + b0; ++k2)
                {
                    d2 = (double)k2 + 0.5D - entity.posZ;
                    label222:

                    for (i3 = this.WSInstance.getActualHeight() - 1; i3 >= 0; --i3)
                    {
                        if (this.WSInstance.isAirBlock(i2, i3, k2))
                        {
                            while (i3 > 0 && this.WSInstance.isAirBlock(i2, i3 - 1, k2))
                            {
                                --i3;
                            }

                            for (j3 = l1; j3 < l1 + 2; ++j3)
                            {
                                k3 = j3 % 2;
                                l3 = 1 - k3;

                                for (i4 = 0; i4 < 4; ++i4)
                                {
                                    for (j4 = -1; j4 < 4; ++j4)
                                    {
                                        k4 = i2 + (i4 - 1) * k3;
                                        l4 = i3 + j4;
                                        i5 = k2 + (i4 - 1) * l3;

                                        if (j4 < 0 && !this.WSInstance.getBlock(k4, l4, i5).getMaterial().isSolid() || j4 >= 0 && !this.WSInstance.isAirBlock(k4, l4, i5))
                                        {
                                            continue label222;
                                        }
                                    }
                                }

                                d3 = (double)i3 + 0.5D - entity.posY;
                                d4 = d1 * d1 + d3 * d3 + d2 * d2;

                                if (d0 < 0.0D || d4 < d0)
                                {
                                    d0 = d4;
                                    l = i2;
                                    i1 = i3;
                                    j1 = k2;
                                    k1 = j3 % 2;
                                }
                            }
                        }
                    }
                }
            }
        }

        int k5 = l;
        int j2 = i1;
        k2 = j1;
        int l5 = k1 % 2;
        int l2 = 1 - l5;

        if (k1 % 4 >= 2)
        {
            l5 = -l5;
            l2 = -l2;
        }

        boolean flag;

        if (d0 < 0.0D)
        {
            if (i1 < 70)
            {
                i1 = 70;
            }

            if (i1 > this.WSInstance.getActualHeight() - 10)
            {
                i1 = this.WSInstance.getActualHeight() - 10;
            }

            j2 = i1;

            for (i3 = -1; i3 <= 1; ++i3)
            {
                for (j3 = 1; j3 < 3; ++j3)
                {
                    for (k3 = -1; k3 < 3; ++k3)
                    {
                        l3 = k5 + (j3 - 1) * l5 + i3 * l2;
                        i4 = j2 + k3;
                        j4 = k2 + (j3 - 1) * l2 - i3 * l5;
                        flag = k3 < 0;
                        this.WSInstance.setBlock(l3, i4, j4, flag ? ModBlocks.compressedPeculiarDust : Blocks.air);
                    }
                }
            }
        }

        for (i3 = 0; i3 < 4; ++i3)
        {
            for (j3 = 0; j3 < 4; ++j3)
            {
                for (k3 = -1; k3 < 4; ++k3)
                {
                    l3 = k5 + (j3 - 1) * l5;
                    i4 = j2 + k3;
                    j4 = k2 + (j3 - 1) * l2;
                    flag = j3 == 0 || j3 == 3 || k3 == -1 || k3 == 3;
                    this.WSInstance.setBlock(l3, i4, j4, (Block)(flag ? ModBlocks.compressedPeculiarDust : ModBlocks.darkworldPortal), 0, 2);
                }
            }

            for (j3 = 0; j3 < 4; ++j3)
            {
                for (k3 = -1; k3 < 4; ++k3)
                {
                    l3 = k5 + (j3 - 1) * l5;
                    i4 = j2 + k3;
                    j4 = k2 + (j3 - 1) * l2;
					this.WSInstance.notifyBlocksOfNeighborChange(l3, i4, j4, this.WSInstance.getBlock(l3, i4, j4));
                }
            }
        }

        return true;
	}
	
	public void removeStalePortalLocations(long i){
		
	}

}
