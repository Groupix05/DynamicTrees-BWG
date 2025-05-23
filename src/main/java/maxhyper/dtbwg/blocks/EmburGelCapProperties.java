package maxhyper.dtbwg.blocks;

import com.ferreusveritas.dynamictrees.api.registry.TypedRegistry;
import com.ferreusveritas.dynamictrees.systems.poissondisc.Vec2i;
import com.ferreusveritas.dynamictreesplus.block.mushroom.CapProperties;
import com.ferreusveritas.dynamictreesplus.block.mushroom.DynamicCapBlock;
import com.ferreusveritas.dynamictreesplus.block.mushroom.DynamicCapCenterBlock;
import com.ferreusveritas.dynamictreesplus.systems.mushroomlogic.MushroomCapDisc;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class EmburGelCapProperties extends CapProperties {

    public static final TypedRegistry.EntryType<CapProperties> TYPE = TypedRegistry.newType(EmburGelCapProperties::new);

    public EmburGelCapProperties(ResourceLocation registryName) {
        super(registryName);
    }

    protected float droopyFirstLevelChance = 0.5f;
    protected float droopySecondLevelChance = 0.25f;
    protected int minAgeForSecondLevelDroop = 4;

    protected String getBlockRegistryNameSuffix() {
        return "_gel";
    }

    protected String getCenterBlockRegistryNameSuffix() {
        return "_gel_center";
    }

    @Override
    public BlockBehaviour.Properties getDefaultBlockProperties(MapColor mapColor) {
        return BlockBehaviour.Properties.copy(Blocks.CLAY).mapColor(MapColor.TERRACOTTA_YELLOW).sound(SoundType.HONEY_BLOCK).noOcclusion().speedFactor(1.3f);
    }

    @Override
    protected DynamicCapCenterBlock createDynamicCapCenter(BlockBehaviour.Properties properties) {
        return new DynamicCapCenterBlock(this, properties){

            public List<BlockPos> getRing(LevelAccessor level, BlockPos pos, int radius) {
                List<Vec2i> ring = MushroomCapDisc.getPrecomputedRing(radius);
                List<BlockPos> positions = new LinkedList<>();

                for (Vec2i vec : ring) {
                    BlockPos ringPos = new BlockPos(pos.getX() + vec.x, pos.getY(), pos.getZ() + vec.z);
                    if (this.properties.isPartOfCap(level.getBlockState(ringPos))) {
                        positions.add(ringPos);
                        if (this.properties.isPartOfCap(level.getBlockState(ringPos.below(2))))
                            positions.add(ringPos.below(2));
                        if (this.properties.isPartOfCap(level.getBlockState(ringPos.below())))
                            positions.add(ringPos.below());
                    }
                }

                return positions;
            }

            public void clearRing(LevelAccessor level, BlockPos pos, int radius) {
                List<Vec2i> ring = MushroomCapDisc.getPrecomputedRing(radius);
                for (Vec2i vec : ring) {
                    BlockPos ringPos = new BlockPos(pos.getX() + vec.x, pos.getY(), pos.getZ() + vec.z);
                    if (this.properties.isPartOfCap(level.getBlockState(ringPos))) {
                        if (this.properties.isPartOfCap(level.getBlockState(ringPos.below(2))))
                            level.setBlock(ringPos.below(2), Blocks.AIR.defaultBlockState(), 2);
                        if (this.properties.isPartOfCap(level.getBlockState(ringPos.below())))
                            level.setBlock(ringPos.below(), Blocks.AIR.defaultBlockState(), 2);
                        level.setBlock(ringPos, Blocks.AIR.defaultBlockState(), 2);
                    }
                }

            }

            public boolean placeRing(LevelAccessor level, BlockPos pos, int radius, int step, boolean yMoved, boolean negFactor) {
                List<Vec2i> ring = MushroomCapDisc.getPrecomputedRing(radius);
                int placed = 0;
                int notPlaced = 0;

                int age = 0;
                for (int i=0;i<8;i++){
                    BlockPos searchPos = pos.above((negFactor?-1:1)*i);
                    BlockState searchState = level.getBlockState(searchPos);
                    if (searchState.hasProperty(AGE)){
                        age = searchState.getValue(AGE);
                        break;
                    }
                }
                boolean lastRing = step == age;
                for (Vec2i vec : ring) {
                    BlockPos ringPos = new BlockPos(pos.getX() + vec.x, pos.getY(), pos.getZ() + vec.z);
                    if (canCapReplace(level.getBlockState(ringPos))) {
                        BlockState placeCapState = this.getStateForAge(this.properties, step, new Vec2i(-vec.x, -vec.z), yMoved, negFactor, this.properties.isPartOfCap(level.getBlockState(ringPos.above())), lastRing);
                        level.setBlock(ringPos, placeCapState, 2);
                        //place droopy blocks on last ring
                        if (lastRing){
                            if (level.getRandom().nextFloat() < droopyFirstLevelChance && canCapReplace(level.getBlockState(ringPos.below())) && step < 8){
                                BlockState droopyCapState1 = properties.getDynamicCapState(step+1, new boolean[]{true, false, true, true, true, true});
                                level.setBlock(ringPos.below(), droopyCapState1, 2);
                                if (level.getRandom().nextFloat() < droopySecondLevelChance && canCapReplace(level.getBlockState(ringPos.below(2))) && age >= minAgeForSecondLevelDroop && step < 7){
                                    BlockState droopyCapState2 = properties.getDynamicCapState(step+2, new boolean[]{true, false, true, true, true, true});
                                    level.setBlock(ringPos.below(2), droopyCapState2.setValue(DynamicCapBlock.DISTANCE, step+2), 2);
                                }
                            }
                        }
                        ++placed;
                    } else {
                        ++notPlaced;
                    }
                }

                return placed >= notPlaced;
            }

            @Nonnull
            private BlockState getStateForAge(CapProperties properties, int age, Vec2i centerDirection, boolean yMoved, boolean negativeFactor, boolean topIsCap, boolean lastRing) {
                boolean[] dirs = new boolean[]{lastRing, !topIsCap, true, true, true, true};
                if (yMoved || age == 1) {

                    for (Direction dir : Direction.Plane.HORIZONTAL) {
                        float dot = (float) (dir.getNormal().getX() * centerDirection.x + dir.getNormal().getZ() * centerDirection.z);
                        if (dot >= 0.0F) {
                            dirs[negativeFactor ? dir.getOpposite().ordinal() : dir.ordinal()] = false;
                        }
                    }
                }

                return properties.getDynamicCapState(age, dirs);
            }
        };
    }
}
