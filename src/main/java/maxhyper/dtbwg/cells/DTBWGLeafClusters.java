package maxhyper.dtbwg.cells;

import com.ferreusveritas.dynamictrees.util.SimpleVoxmap;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;

public class DTBWGLeafClusters {

    public static final SimpleVoxmap SPARSE = new SimpleVoxmap(3, 2, 3, new byte[] {
            0, 1, 0,
            1, 0, 1,
            0, 1, 0,

            0, 0, 0,
            0, 1, 0,
            0, 0, 0
    }).setCenter(new BlockPos(1, 0, 1));

    public static final SimpleVoxmap POPLAR = new SimpleVoxmap(3, 4, 3, new byte[] {
            0, 0, 0,
            0, 1, 0,
            0, 0, 0,

            0, 2, 0,
            2, 0, 2,
            0, 2, 0,

            0, 1, 0,
            1, 2, 1,
            0, 1, 0,

            0, 0, 0,
            0, 1, 0,
            0, 0, 0
    }).setCenter(new BlockPos(1, 1, 1));

    public static final SimpleVoxmap POPLAR_TOP = new SimpleVoxmap(3, 3, 3, new byte[] {
            0, 1, 0,
            1, 0, 1,
            0, 1, 0,

            0, 0, 0,
            0, 2, 0,
            0, 0, 0,

            0, 0, 0,
            0, 1, 0,
            0, 0, 0
    }).setCenter(new BlockPos(1, 0, 1));

    public static final SimpleVoxmap WILLOW = new SimpleVoxmap(5, 4, 5, new byte[]{
            //Layer 0 (Bottom)
            0, 0, 0, 0, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 0, 0, 0, 0,

            //Layer 1
            0, 1, 1, 1, 0,
            1, 3, 4, 3, 1,
            1, 4, 0, 4, 1,
            1, 3, 4, 3, 1,
            0, 1, 1, 1, 0,

            //Layer 2
            0, 1, 1, 1, 0,
            1, 2, 3, 2, 1,
            1, 3, 4, 3, 1,
            1, 2, 3, 2, 1,
            0, 1, 1, 1, 0,

            //Layer 3(Top)
            0, 0, 0, 0, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 0, 0, 0, 0

    }).setCenter(new BlockPos(2, 1, 2));

    public static final SimpleVoxmap ROUND_CONIFER = new SimpleVoxmap(3, 2, 3, new byte[] {
            1, 2, 1,
            2, 0, 2,
            1, 2, 1,

            0, 1, 0,
            1, 1, 1,
            0, 1, 0
    }).setCenter(new BlockPos(1, 0, 1));

    public static final SimpleVoxmap BUSHY = new SimpleVoxmap(5, 5, 5, new byte[]{
            //Layer 0(Bottom)
            0, 0, 0, 0, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 0, 0, 0, 0,

            //Layer 1 (Bottom-Middle)
            0, 1, 1, 1, 0,
            1, 2, 3, 2, 1,
            1, 3, 4, 3, 1,
            1, 2, 3, 2, 1,
            0, 1, 1, 1, 0,

            //Layer 2 (Middle)
            0, 1, 1, 1, 0,
            1, 3, 4, 3, 1,
            1, 4, 0, 4, 1,
            1, 3, 4, 3, 1,
            0, 1, 1, 1, 0,

            //Layer 3 (Top-Middle)
            0, 0, 1, 0, 0,
            0, 1, 2, 1, 0,
            1, 2, 3, 2, 1,
            0, 1, 2, 1, 0,
            0, 0, 1, 0, 0,

            //Layer 4 (Top)
            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 1, 1, 1, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0
    }).setCenter(new BlockPos(2, 2, 2));

    public static final SimpleVoxmap SYTHIAN_FUNGUS = new SimpleVoxmap(3, 1, 3, new byte[] {
            1, 2, 1,
            2, 0, 2,
            1, 2, 1
    }).setCenter(new BlockPos(1, 0, 1));

    public static final SimpleVoxmap LAMENT = new SimpleVoxmap(5, 2, 5, new byte[]{

            //Layer 0(Bottom)
            0, 1, 1, 1, 0,
            1, 2, 3, 2, 1,
            1, 3, 0, 3, 1,
            1, 2, 3, 2, 1,
            0, 1, 1, 1, 0,

            //Layer 1 (Top)
            0, 0, 0, 0, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 1, 1, 1, 0,
            0, 0, 0, 0, 0

    }).setCenter(new BlockPos(3, 0, 3));

    public static final SimpleVoxmap ALLIUM = new SimpleVoxmap(5, 4, 5, new byte[]{

            //Layer 0 (Bottom)
            0, 0, 0, 0, 0,
            0, 1, 1, 1, 0,
            0, 1, 0, 1, 0,
            0, 1, 1, 1, 0,
            0, 0, 0, 0, 0,

            //Layer 1
            0, 1, 1, 1, 0,
            1, 2, 3, 2, 1,
            1, 3, 0, 3, 1,
            1, 2, 3, 2, 1,
            0, 1, 1, 1, 0,

            //Layer 2
            0, 0, 1, 0, 0,
            0, 1, 2, 1, 0,
            1, 2, 0, 2, 1,
            0, 1, 2, 1, 0,
            0, 0, 1, 0, 0,

            //Layer 3 (Top)
            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 1, 1, 1, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0

    }).setCenter(new BlockPos(2, 1, 2));

    public static final SimpleVoxmap ROSE = new SimpleVoxmap(5, 5, 5, new byte[]{

            //Layer 0 (Bottom)
            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 1, 0, 1, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0,

            //Layer 1
            0, 0, 5, 0, 0,
            0, 6, 7, 6, 0,
            5, 7, 0, 7, 5,
            0, 6, 7, 6, 0,
            0, 0, 5, 0, 0,

            //Layer 2
            0, 1, 4, 1, 0,
            1, 3, 1, 3, 1,
            4, 1, 1, 1, 4,
            1, 3, 1, 3, 1,
            0, 1, 4, 1, 0,

            //Layer 3
            0, 0, 2, 0, 0,
            0, 1, 0, 1, 0,
            2, 0, 0, 0, 2,
            0, 1, 0, 1, 0,
            0, 0, 2, 0, 0,

            //Layer 4 (Top)
            0, 0, 1, 0, 0,
            0, 0, 0, 0, 0,
            1, 0, 0, 0, 1,
            0, 0, 0, 0, 0,
            0, 0, 1, 0, 0

    }).setCenter(new BlockPos(2, 1, 2));
}
