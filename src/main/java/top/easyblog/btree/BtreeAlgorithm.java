package top.easyblog.btree;

import java.util.Objects;

/**
 * @author HuangXin
 * @since 2020/2/11 21:07
 */
public class BtreeAlgorithm {

    private static BtreeAlgorithm bTreeUtils = null;

    private BtreeAlgorithm() {

    }

    public static BtreeAlgorithm getInstance() {
        if (Objects.isNull(bTreeUtils)) {
            synchronized (BtreeAlgorithm.class) {
                if (Objects.isNull(bTreeUtils)) {
                    bTreeUtils = new BtreeAlgorithm();
                }
            }
        }
        return bTreeUtils;
    }


}
