package maxhyper.dtbwg.cells.cell;

import com.ferreusveritas.dynamictrees.cell.MatrixCell;

public class AlliumLeafCell extends MatrixCell {

    public AlliumLeafCell(int value) {
        super(value, valMap);
    }

    static final byte[] valMap = {
            0, 1, 2, 3, 4, 0, 0, 0, //D Maps * -> 0
            0, 1, 2, 3, 4, 0, 0, 0, //U Maps 3 -> 3, 4 -> 3, * -> 0
            0, 1, 2, 3, 4, 0, 0, 0, //N Maps * -> *
            0, 1, 2, 3, 4, 0, 0, 0, //S Maps * -> *
            0, 1, 2, 3, 4, 0, 0, 0, //W Maps * -> *
            0, 1, 2, 3, 4, 0, 0, 0  //E Maps * -> *
    };

}

