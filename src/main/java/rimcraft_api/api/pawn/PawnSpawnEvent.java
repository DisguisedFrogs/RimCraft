package rimcraft_api.api.pawn;

import java.util.Objects;
import rimcraft_api.api.event.RimCraftEvent;

/**
 * 棋子单位生成事件（生命周期四阶段之一）。
 *
 * <p>触发时机：单位实体已进入世界、棋子单位身份确立（首个组件挂载前）发布。
 * 此时组件可能尚未就绪，监听方不应在本事件内读取组件状态；组件可用以 {@link PawnReadyEvent} 为准。
 *
 * <p>不可取消（生成既成事实的通知语义）。发布执行归 Core。
 */
public final class PawnSpawnEvent extends RimCraftEvent {

    private final IPawn pawn;

    /**
     * @param pawn 生成的单位视图（非 null，不可变视图）
     */
    public PawnSpawnEvent(IPawn pawn) {
        this.pawn = Objects.requireNonNull(pawn, "pawn");
    }

    /** @return 生成的单位视图 */
    public IPawn pawn() {
        return pawn;
    }
}
