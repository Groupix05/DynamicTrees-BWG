package maxhyper.dtbwg.init;

import com.ferreusveritas.dynamictrees.api.registry.RegistryHandler;
import com.ferreusveritas.dynamictrees.api.registry.TypeRegistryEvent;
import com.ferreusveritas.dynamictrees.init.DTConfigs;
import com.ferreusveritas.dynamictrees.systems.BranchConnectables;
import com.ferreusveritas.dynamictrees.tree.family.Family;
import maxhyper.dtbwg.DynamicTreesBWG;
import maxhyper.dtbwg.blocks.DynamicWitchHazelBranch;
import maxhyper.dtbwg.trees.DiagonalPalmFamily;
import maxhyper.dtbwg.trees.ImbuedLogFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class DTBWGRegistries {

//    public static final VoxelShape MUSHROOM_STEM_LONG = Block.box(7D, 0D, 7D, 9D, 10D, 9D);
//    public static final VoxelShape TALL_MUSHROOM_CAP_FLAT = Block.box(5.0D, 7.0D, 5.0D, 11.0D, 10.0D, 11.0D);
//    public static final VoxelShape SMALL_MUSHROOM_CAP_FLAT = Block.box(5.0D, 5.0D, 5.0D, 11.0D, 7.0D, 11.0D);
//    public static final VoxelShape MUSHROOM_CAP_SHORT_ROUND = Block.box(5.0D, 3.0D, 5.0D, 11.0D, 7.0D, 11.0D);
//    public static final VoxelShape SOUL_SHROOM_CAP = Block.box(5.5D, 3.0D, 5.5D, 10.5D, 10.0D, 10.5D);
//    public static final VoxelShape SYTHIAN_CAP_A = Block.box(5D, 3D, 5D, 11D, 5D, 11D);
//    public static final VoxelShape SYTHIAN_CAP_B = Block.box(4D, 6D, 4D, 12D, 8D, 12D);
//    public static final VoxelShape SYTHIAN_CAP_C = Block.box(5D, 9D, 5D, 11D, 11D, 11D);
//    public static final VoxelShape SHULKREN_CAP_A = Block.box(4D, 3D, 4D, 12D, 6D, 12D);
//    public static final VoxelShape SHULKREN_CAP_B = Block.box(5D, 6D, 5D, 11D, 9D, 11D);
//    public static final VoxelShape SHULKREN_CAP_C = Block.box(6D, 9D, 6D, 10D, 11D, 10D);
//
//    public static final VoxelShape TALL_FLAT_MUSHROOM = Shapes.or(MUSHROOM_STEM_LONG, TALL_MUSHROOM_CAP_FLAT);
//    public static final VoxelShape SMALL_FLAT_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, SMALL_MUSHROOM_CAP_FLAT);
//    public static final VoxelShape SHORT_ROUND_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, MUSHROOM_CAP_SHORT_ROUND);
//    public static final VoxelShape SOUL_SHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, SOUL_SHROOM_CAP);
//    public static final VoxelShape SYTHIAN_MUSHROOM = Shapes.or(MUSHROOM_STEM_LONG, SYTHIAN_CAP_A, SYTHIAN_CAP_B, SYTHIAN_CAP_C);
//    public static final VoxelShape SHULKREN_MUSHROOM = Shapes.or(CommonVoxelShapes.MUSHROOM_STEM, SHULKREN_CAP_A, SHULKREN_CAP_B, SHULKREN_CAP_C);
//
    public static Supplier<DynamicWitchHazelBranch> WITCH_HAZEL_BRANCH = RegistryHandler.addBlock(DynamicTreesBWG.location("witch_hazel_side_branch"), ()->new DynamicWitchHazelBranch(BWGBlocks.WITCH_HAZEL_BRANCH.get()));

//    public static void setup() {
//        biomeswevegoneConstants.ENABLE_CACTI = false;
//
//        CommonVoxelShapes.SHAPES.put(DynamicTreesBWG.location("tall_flat_mushroom").toString(), TALL_FLAT_MUSHROOM);
//        CommonVoxelShapes.SHAPES.put(DynamicTreesBWG.location("small_flat_mushroom").toString(), SMALL_FLAT_MUSHROOM);
//        CommonVoxelShapes.SHAPES.put(DynamicTreesBWG.location("short_round_mushroom").toString(), SHORT_ROUND_MUSHROOM);
//        CommonVoxelShapes.SHAPES.put(DynamicTreesBWG.location("soul_shroom").toString(), SOUL_SHROOM);
//        CommonVoxelShapes.SHAPES.put(DynamicTreesBWG.location("sythian_mushroom").toString(), SYTHIAN_MUSHROOM);
//        CommonVoxelShapes.SHAPES.put(DynamicTreesBWG.location("shulkren_mushroom").toString(), SHULKREN_MUSHROOM);
//
//    }
//
    public static void setupBlocks() {
        setupConnectables();
    }

    private static void setupConnectables() {

        for (Block block : new Block[]{WITCH_HAZEL_BRANCH.get()}){
            BranchConnectables.makeBlockConnectable(block, (state, world, pos, side) -> {
                if (state.hasProperty(HorizontalDirectionalBlock.FACING)) {
                    return state.getValue(HorizontalDirectionalBlock.FACING) == side ? 1 : 0;
                }
                return 0;
            });
        }
    }
//
//    @SubscribeEvent
//    public static void onGenFeatureRegistry (final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GenFeature> event) {
//        dtbwgGenFeatures.register(event.getRegistry());
//    }
//
//    @SubscribeEvent
//    public static void onCellKitRegistry (final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<CellKit> event) {
//        dtbwgCellKits.register(event.getRegistry());
//    }
//
//    @SubscribeEvent
//    public static void onGrowthLogicKitRegistry (final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<GrowthLogicKit> event) {
//        dtbwgGrowthLogicKits.register(event.getRegistry());
//    }
//
//    @SubscribeEvent
//    public static void registerSpeciesTypes (final TypeRegistryEvent<Species> event) {
//        event.registerType(DynamicTreesBWG.location("poplar"), PoplarSpecies.TYPE);
//        event.registerType(DynamicTreesBWG.location("twiglet"), TwigletSpecies.TYPE);
//        event.registerType(DynamicTreesBWG.location("generates_underwater"), GenUnderwaterSpecies.TYPE);
//        event.registerType(DynamicTreesBWG.location("generates_on_extra_soil"), GenOnExtraSoilSpecies.TYPE);
//        event.registerType(DynamicTreesBWG.location("mangrove"), MangroveSpecies.TYPE);
//        event.registerType(DynamicTreesBWG.location("lament"), LamentSpecies.TYPE);
//        event.registerType(DynamicTreesBWG.location("woody_mushroom"), WoodyHugeMushroomSpecies.TYPE);
//    }

    @SubscribeEvent
    public static void registerFamilyTypes (final TypeRegistryEvent<Family> event) {
        event.registerType(DynamicTreesBWG.location("imbued_log"), ImbuedLogFamily.TYPE);
        event.registerType(DynamicTreesBWG.location("diagonal_palm"), DiagonalPalmFamily.TYPE);
    }

//    @SubscribeEvent
//    public static void registerCapPropertiesTypes (final TypeRegistryEvent<CapProperties> event){
//        event.registerType(DynamicTreesBWG.location("embur_gel_cap"), EmburGelCapProperties.TYPE);
//        event.registerType(DynamicTreesBWG.location("fungal_imparius_cap"), FungalImpariusCapProperties.TYPE);
//        event.registerType(DynamicTreesBWG.location("imparius_mushroom_cap"), ImpariusMushroomCapProperties.TYPE);
//        event.registerType(DynamicTreesBWG.location("warty_cap"), WartyCapProperties.TYPE);
//        event.registerType(DynamicTreesBWG.location("weeping_milkcap_cap"), WeepingMilkcapCapProperties.TYPE);
//    }
//
//    @SubscribeEvent
//    public static void onMushroomShapeKitRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<MushroomShapeKit> event) {
//        biomeswevegoneMushroomShapeKits.register(event.getRegistry());
//    }
//
//    public static final FeatureCanceller biomeswevegone_TREE_CANCELLER = new biomeswevegoneTreeFeatureCanceller<>(DynamicTreesBWG.location("tree"), TreeFromStructureNBTConfig.class);
//    public static final FeatureCanceller biomeswevegone_FUNGUS_CANCELLER = new TreeFeatureCanceller<>(DynamicTreesBWG.location("fungus"), biomeswevegoneMushroomConfig.class);
//    public static final FeatureCanceller GIANT_FLOWER_CANCELLER = new TreeFeatureCanceller<>(DynamicTreesBWG.location("giant_flower"), GiantFlowerConfig.class);
//
//    @SubscribeEvent
//    public static void onFeatureCancellerRegistry(final com.ferreusveritas.dynamictrees.api.registry.RegistryEvent<FeatureCanceller> event) {
//        event.getRegistry().registerAll(biomeswevegone_TREE_CANCELLER);
//        event.getRegistry().registerAll(biomeswevegone_FUNGUS_CANCELLER);
//        event.getRegistry().registerAll(GIANT_FLOWER_CANCELLER);
//    }

//    public static void onBiomeLoading(final BiomeLoadingEvent event){
//        VegetationReplacement.OnBiomeLoadingEvent(event);
//    }

}
