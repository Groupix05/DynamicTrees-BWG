package maxhyper.dtbwg.init;

import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.google.common.collect.ImmutableMap;
import maxhyper.dtbwg.DynamicTreesBWG;
import maxhyper.dtbwg.blocks.DynamicWitchHazelBranch;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.potionstudios.biomeswevegone.world.level.block.BWGBlocks;
import net.potionstudios.biomeswevegone.world.level.block.plants.tree.branch.TreeBranchBlock;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = DynamicTreesBWG.MOD_ID)
public class SideBranchPlaceEventHandler {

    static final Map<Supplier<TreeBranchBlock>, Supplier<DynamicWitchHazelBranch>> branches = ImmutableMap.<Supplier<TreeBranchBlock>, Supplier<DynamicWitchHazelBranch>>builder()
//            .put(BWGBlocks.ARISIAN_BLOOM_BRANCH, DTBWGRegistries.ARISIAN_BLOOM_BRANCH)
//            .put(BWGBlocks.EMBUR_GEL_BRANCH, DTBWGRegistries.EMBUR_GEL_BRANCH)
            .put(BWGBlocks.WITCH_HAZEL_BRANCH, DTBWGRegistries.WITCH_HAZEL_BRANCH)
//            .put(BWGBlocks.IMPARIUS_MUSHROOM_BRANCH, DTBWGRegistries.IMPARIUS_MUSHROOM_BRANCH)
            .build();

   @SubscribeEvent
   public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {

       Player player = event.getEntity();
       InteractionHand hand = event.getHand();
       ItemStack heldItem = player.getItemInHand(hand);

       Item handItem = player.getItemInHand(hand).getItem();
       Block bloomBranch = null;
       if (handItem instanceof BlockItem handBlockItem && handBlockItem.getBlock() instanceof TreeBranchBlock handBlock){
           Supplier<TreeBranchBlock> normalBranch = branches.keySet().stream()
                   .filter((i)->handBlock.equals(i.get())).findFirst().orElse(null);
           if (normalBranch != null) bloomBranch = branches.get(normalBranch).get();
       }
       if (bloomBranch == null) return;

       Level world = event.getLevel();
       BlockPos branchPos = event.getPos();
       BlockState state = world.getBlockState(branchPos);

       if (!TreeHelper.isBranch(state) || TreeHelper.getRadius(world, branchPos) < 3) return;

       BlockPos pos = branchPos.relative(Objects.requireNonNull(event.getFace()));
       if (!world.getBlockState(pos).canBeReplaced()) return;

       // Remove one item from the player's hand
       if (!player.isCreative()) heldItem.shrink(1);
       world.playSound(null, pos, bloomBranch.getSoundType(state, world, pos, player).getPlaceSound(), SoundSource.BLOCKS, 1,0.8f);
       BlockPlaceContext context = new BlockPlaceContext(player, hand, heldItem, new BlockHitResult(player.getEyePosition(1.0f), event.getFace(), branchPos, false));
       BlockState placeState = bloomBranch.getStateForPlacement(context);
       if (placeState == null) return;
       world.setBlock(pos, placeState, 3);
       event.setCancellationResult(InteractionResult.SUCCESS);
       event.setCanceled(true);

   }
}
