package maxhyper.dtbwg.genfeatures;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.configuration.ConfigurationProperty;
import com.ferreusveritas.dynamictrees.api.network.MapSignal;
import com.ferreusveritas.dynamictrees.api.network.NodeInspector;
import com.ferreusveritas.dynamictrees.block.branch.BranchBlock;
import com.ferreusveritas.dynamictrees.block.branch.TrunkShellBlock;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeature;
import com.ferreusveritas.dynamictrees.systems.genfeature.GenFeatureConfiguration;
import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGenerationContext;
import com.ferreusveritas.dynamictrees.systems.genfeature.context.PostGrowContext;
import com.ferreusveritas.dynamictrees.util.CoordUtils;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.BlockState;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Max Hyper
 */
public class BranchSproutsGenFeature extends GenFeature {

    public static final ConfigurationProperty<Block> SPROUT_BLOCK = ConfigurationProperty.property("sprout_block", Block.class);
    public static final ConfigurationProperty<Integer> MIN_RADIUS = ConfigurationProperty.integer("min_radius");

    public BranchSproutsGenFeature(ResourceLocation registryName) {
        super(registryName);
    }

    @Override
    protected void registerProperties() {
        this.register(SPROUT_BLOCK,  FRUITING_RADIUS, PLACE_CHANCE, MAX_COUNT, MIN_RADIUS);
    }

    @Override
    public GenFeatureConfiguration createDefaultConfiguration() {
        return super.createDefaultConfiguration()
                .with(SPROUT_BLOCK, Blocks.AIR)
                .with(FRUITING_RADIUS, 8)
                .with(PLACE_CHANCE, 0.05f)
                .with(MAX_COUNT, 16)
                .with(MIN_RADIUS, 8);
    }

    @Override
    protected boolean postGenerate(GenFeatureConfiguration configuration, PostGenerationContext context) {
        LevelAccessor world = context.level();
        BlockPos rootPos = context.pos();
        final BlockState blockState = world.getBlockState(rootPos.above());
        final BranchBlock branch = TreeHelper.getBranch(blockState);

        if (branch != null && branch.getRadius(blockState) >= configuration.get(MIN_RADIUS)) {
            int count = 1 + world.getRandom().nextInt(configuration.get(MAX_COUNT));
            placeSprouts(count, configuration, world, rootPos);
        }
        return true;
    }

    @Override
    protected boolean postGrow(GenFeatureConfiguration configuration, PostGrowContext context) {
        if (context.fertility() == 0) return false;

        LevelAccessor world = context.level();
        BlockPos rootPos = context.pos();
        final BlockState blockState = world.getBlockState(rootPos.above());
        final BranchBlock branch = TreeHelper.getBranch(blockState);

        if (branch != null && branch.getRadius(blockState) >= configuration.get(MIN_RADIUS) && context.natural()) {
            if (world.getRandom().nextFloat() < configuration.get(PLACE_CHANCE)) {
                placeSprouts(1, configuration, world, rootPos);
            }
        }
        return true;
    }

    private void placeSprouts (int count, GenFeatureConfiguration configuration, LevelAccessor world, BlockPos rootPos){
        List<Pair<BlockPos, Direction>> validSpots = new LinkedList<>();
        final FindSidedBlockNode sproutPlacer = new FindSidedBlockNode(validSpots, configuration.get(MIN_RADIUS));
        TreeHelper.startAnalysisFromRoot(world, rootPos, new MapSignal(sproutPlacer));

        if (!validSpots.isEmpty()){
            for (int i=0; i<count; i++){
                Pair<BlockPos, Direction> selection = validSpots.get(world.getRandom().nextInt(validSpots.size()));
                BlockPos pos = selection.getFirst();
                Block block = configuration.get(SPROUT_BLOCK);
                if (world.getBlockState(pos.below()).getBlock() == block)
                    return;

                world.setBlock(pos, block.defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, selection.getSecond()), 3);
            }
        }
    }

    public static class FindSidedBlockNode implements NodeInspector {

        private final List<Pair<BlockPos, Direction>> validSpots;
        private final int minRadius;

        public FindSidedBlockNode(List<Pair<BlockPos, Direction>> spots, int minRadius) {
            validSpots = spots;
            this.minRadius = minRadius;
        }

        @Override
        public boolean run(BlockState blockState, LevelAccessor world, BlockPos pos, Direction fromDir) {
            int radius = TreeHelper.getRadius(world, pos);
                if (TreeHelper.isBranch(blockState) && radius >= minRadius) {
                    boolean found = false;
                    for (Direction dir : CoordUtils.HORIZONTALS){
                        BlockPos offsetPos = pos.offset(dir.getNormal());
                        BlockState offsetState = world.getBlockState(offsetPos);
                        if (offsetState.isAir() || offsetState.getBlock() instanceof TrunkShellBlock){
                            for (int i=0; i<radius; i++){
                                validSpots.add(new Pair<>(offsetPos, dir));
                            }
                            found = true;
                        }
                    }
                    return found;
                }
            return false;
        }

        @Override
        public boolean returnRun(BlockState blockState, LevelAccessor world, BlockPos pos, Direction fromDir) { return false;}

    }


}
