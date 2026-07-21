package rimcraft_api.api.pawn;

import java.util.Objects;
import rimcraft_api.api.event.RimCraftEvent;

/**
 * 棋子单位移除事件（生命周期四阶段之三）。
 *
 * <p>触发时机：单位实体自世界移除（区块卸载、跨维度转移、非死亡性消失等）时发布。
 * 死亡导致的移除以 {@link PawnDeathEvent} 为准，两者不重复发布同一事实。
 *
 * <p>不可取消（通知语义）。发布执行归 Core。
 */
public final class PawnRemoveEvent extends RimCraftEvent {

    private final IPawn pawn;

    /**
     * @param pawn 被移除的单位视图（非 null，不可变视图）
     */
    public PawnRemoveEvent(IPawn pawn) {
        this.pawn = Objects.requireNonNull(pawn, "pawn");
    }

    /** @return 被移除的单位视图 */
    public IPawn pawn() {
        return pawn;
    }
}
