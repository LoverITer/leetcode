package top.easyblog.btree;

import top.easyblog.array.ArrayUtils;

import java.util.Objects;

/**
 * @author HuangXin
 * @since 2020/2/11 21:07
 */
public class BTreeUtils {

    private static BTreeUtils bTreeUtils = null;

    private BTreeUtils() {

    }

    public static BTreeUtils getInstance() {
        if (Objects.isNull(bTreeUtils)) {
            synchronized (ArrayUtils.class) {
                if (Objects.isNull(bTreeUtils)) {
                    bTreeUtils = new BTreeUtils();
                }
            }
        }
        return bTreeUtils;
    }


}
