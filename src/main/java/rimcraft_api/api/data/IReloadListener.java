package rimcraft_api.api.data;

/**
 * 数据重载完成监听器。
 *
 * <p>触发时机：数据包重载完成、新数据视图可用之后回调（与 Database 热重载通知对接）；
 * Core/DLC 订阅后应刷新自身缓存与派生状态。回调内不得再触发数据变更。
 */
@FunctionalInterface
public interface IReloadListener {

    /**
     * 数据重载完成回调。
     */
    void onDataReloadComplete();
}
