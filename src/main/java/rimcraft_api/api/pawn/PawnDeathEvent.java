package rimcraft_api.api.pawn;

import java.util.Objects;
import rimcraft_api.api.event.RimCraftEvent;

/**
 * 棋子单位死亡事件（生命周期四阶段之四）。
 *
 * <p>触发时机：单位死亡确认后发布；死亡导致的移除不再重复发布 {@link PawnRemoveEvent}。
 * 监听方可在本事件内做收尾（掉落、统计、派系通知等），但不得复活单位或假设组件仍可写。
 *
 * <p>不可取消（死亡既成事实的通知语义）。发布执行归 Core。
 */
public final class PawnDeathEvent extends RimCraftEvent {

    private final IPawn pawn;

    /**
     * @param pawn 死亡的单位视图（非 null，不可变视图）
     */
    public PawnDeathEvent(IPawn pawn) {
        this.pawn = Objects.requireNonNull(pawn, "pawn");
    }

    /** @return 死亡的单位视图 */
    public IPawn pawn() {
        return pawn;
    }
}
