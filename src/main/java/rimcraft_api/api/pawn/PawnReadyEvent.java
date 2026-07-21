package rimcraft_api.api.pawn;

import java.util.Objects;
import rimcraft_api.api.event.RimCraftEvent;

/**
 * 棋子单位就绪事件（生命周期四阶段之二）。
 *
 * <p>触发时机：单位全部已声明组件按依赖拓扑序挂载并初始化完成后发布。
 * 自此监听方可经 {@link IPawnAccessor} 正常读取组件状态。
 *
 * <p>不可取消（通知语义）。发布执行归 Core。
 */
public final class PawnReadyEvent extends RimCraftEvent {

    private final IPawn pawn;

    /**
     * @param pawn 就绪的单位视图（非 null，不可变视图）
     */
    public PawnReadyEvent(IPawn pawn) {
        this.pawn = Objects.requireNonNull(pawn, "pawn");
    }

    /** @return 就绪的单位视图 */
    public IPawn pawn() {
        return pawn;
    }
}
