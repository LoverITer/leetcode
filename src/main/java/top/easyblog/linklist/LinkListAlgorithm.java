package top.easyblog.linklist;

import java.util.Objects;

/**
 * @author HuangXin
 * @since 2020/2/11 21:08
 */
public class LinkListAlgorithm {

    private static LinkListAlgorithm linkListUtils = null;

    private LinkListAlgorithm() {

    }

    public static LinkListAlgorithm getInstance() {
        if (Objects.isNull(linkListUtils)) {
            synchronized (LinkListAlgorithm.class) {
                if (Objects.isNull(linkListUtils)) {
                    linkListUtils = new LinkListAlgorithm();
                }
            }
        }
        return linkListUtils;
    }

}
