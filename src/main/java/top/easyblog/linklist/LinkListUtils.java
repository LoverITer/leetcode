package top.easyblog.linklist;

import java.util.Objects;

/**
 * @author HuangXin
 * @since 2020/2/11 21:08
 */
public class LinkListUtils {

    private static LinkListUtils linkListUtils = null;

    private LinkListUtils() {

    }

    public static LinkListUtils getInstance() {
        if (Objects.isNull(linkListUtils)) {
            synchronized (LinkListUtils.class) {
                if (Objects.isNull(linkListUtils)) {
                    linkListUtils = new LinkListUtils();
                }
            }
        }
        return linkListUtils;
    }

}
