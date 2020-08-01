package com.telepathicgrunt.repurposedstructures.world.features;

import com.mojang.serialization.Codec;
import com.telepathicgrunt.repurposedstructures.RepurposedStructures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


public class DungeonBadlands extends Feature<NoFeatureConfig> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ResourceLocation CHEST_LOOT = new ResourceLocation(RepurposedStructures.MODID + ":chests/dungeon_badlands");
    private static final ResourceLocation SPAWNER_ID = new ResourceLocation(RepurposedStructures.MODID + ":dungeon_badlands");
    private static final BlockState CAVE_AIR = Blocks.CAVE_AIR.getDefaultState();
    private static final BlockState RED_TERRACOTTA = Blocks.RED_TERRACOTTA.getDefaultState();
    private static final BlockState ORANGE_TERRACOTTA = Blocks.ORANGE_TERRACOTTA.getDefaultState();
    private static final BlockState CUT_RED_SANDSTONE = Blocks.CUT_RED_SANDSTONE.getDefaultState();

    public DungeonBadlands(Codec<NoFeatureConfig> configFactory) {
        super(configFactory);
    }


    //only the mob spawner chance and what blocks the wall cannot replace was changed. Everything else is just the normal dungeon code.

    @Override
    public boolean generate(ISeedReader world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockPos position, NoFeatureConfig config) {
        int randXRange = random.nextInt(2) + 2;
        int xMin = -randXRange - 1;
        int xMax = randXRange + 1;
        int randZRange = random.nextInt(2) + 2;
        int zMin = -randZRange - 1;
        int zMax = randZRange + 1;
        int validOpenings = 0;
        int ceilingOpenings = 0;
        BlockPos.Mutable blockpos$Mutable = new BlockPos.Mutable().setPos(position);

        for (int x = xMin; x <= xMax; ++x) {
            for (int y = -1; y <= 4; ++y) {
                for (int z = zMin; z <= zMax; ++z) {
                    blockpos$Mutable.setPos(position).move(x, y, z);
                    Material material = world.getBlockState(blockpos$Mutable).getMaterial();
                    boolean flag = material.isSolid();

                    if (y == -1 && !flag) {
                        return false;
                    }

                    if (y == 4 && !flag) {
                        ceilingOpenings++;
                    }

                    if ((x == xMin || x == xMax || z == zMin || z == zMax) && y == 0 && world.isAirBlock(blockpos$Mutable) && world.isAirBlock(blockpos$Mutable.up())) {
                        ++validOpenings;
                    }
                }
            }
        }

        if (validOpenings >= 1 && validOpenings <= 14 && ceilingOpenings < 14) {
            for (int x = xMin; x <= xMax; ++x) {
                for (int y = 4; y >= -1; --y) {
                    for (int z = zMin; z <= zMax; ++z) {
                        blockpos$Mutable.setPos(position).move(x, y, z);

                        if (x != xMin && z != zMin && x != xMax && z != zMax && y != -1 && y != 5) {
                            if (y == 4) {
                                //ceiling
                                if (random.nextInt(4) == 0) {
                                    world.setBlockState(blockpos$Mutable, CUT_RED_SANDSTONE, 2);
                                } else {
                                    world.setBlockState(blockpos$Mutable, ORANGE_TERRACOTTA, 2);
                                }
                            } else if (world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER) {
                                world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
                            }
                        } else if (blockpos$Mutable.getY() >= 0 && !world.getBlockState(blockpos$Mutable.down()).getMaterial().isSolid()) {
                            world.setBlockState(blockpos$Mutable, CAVE_AIR, 2);
                        }

                        //made sure the dungeon wall cannot replace other dungeon's mob spawner now.
                        else if (world.getBlockState(blockpos$Mutable).getMaterial().isSolid() && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.CHEST && world.getBlockState(blockpos$Mutable).getBlock() != Blocks.SPAWNER) {
                            if (y == -1 && random.nextInt(4) != 0) {
                                world.setBlockState(blockpos$Mutable, RED_TERRACOTTA, 2);
                            } else if (random.nextInt(2) == 0) {
                                world.setBlockState(blockpos$Mutable, ORANGE_TERRACOTTA, 2);
                            } else {
                                world.setBlockState(blockpos$Mutable, CUT_RED_SANDSTONE, 2);
                            }
                        }
                    }
                }
            }

            for (int l3 = 0; l3 < 2; ++l3) {
                for (int j4 = 0; j4 < 3; ++j4) {
                    int x = position.getX() + random.nextInt(randXRange * 2 + 1) - randXRange;
                    int y = position.getY();
                    int z = position.getZ() + random.nextInt(randZRange * 2 + 1) - randZRange;
                    blockpos$Mutable.setPos(x, y, z);

                    if (world.isAirBlock(blockpos$Mutable)) {
                        int j3 = 0;

                        for (Direction direction : Direction.Plane.HORIZONTAL) {
                            if (world.getBlockState(blockpos$Mutable.offset(direction)).getMaterial().isSolid()) {
                                ++j3;
                            }
                        }

                        if (j3 == 1) {
                            world.setBlockState(blockpos$Mutable, StructurePiece.func_197528_a(world, blockpos$Mutable, Blocks.CHEST.getDefaultState()), 2);
                            LockableLootTileEntity.setLootTable(world, random, blockpos$Mutable, CHEST_LOOT);

                            break;
                        }
                    }
                }
            }

            world.setBlockState(position, Blocks.AIR.getDefaultState(), 2);
            world.setBlockState(position, Blocks.SPAWNER.getDefaultState(), 2);
            TileEntity tileentity = world.getTileEntity(position);

            if (tileentity instanceof MobSpawnerTileEntity) {
                ((MobSpawnerTileEntity) tileentity).getSpawnerBaseLogic()
                        .setEntityType(RepurposedStructures.mobSpawnerManager.getSpawnerMob(SPAWNER_ID, random));
            } else {
                LOGGER.error("Failed to fetch mob spawner entity at ({}, {}, {})", new Object[]{Integer.valueOf(position.getX()), Integer.valueOf(position.getY()), Integer.valueOf(position.getZ())});
            }

            return true;
        } else {
            return false;
        }
    }
}
