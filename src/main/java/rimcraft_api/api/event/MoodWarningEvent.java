package rimcraft_api.api.event;

import java.util.Objects;
import net.neoforged.bus.api.ICancellableEvent;
import rimcraft_api.api.pawn.IPawn;

/**
 * 心情恶化预警事件。
 *
 * <p>触发时机：单位心情档位跌入预警区间、崩溃风险评估越过预警阈值时发布，由 Core 的心情结算执行。
 *
 * <p>可取消：取消表示抑制本次预警（不再向玩家侧展示/播报），不影响心情状态本身；
 * 崩溃到达见 {@link MoodBreakEvent}。
 */
public final class MoodWarningEvent extends RimCraftEvent implements ICancellableEvent {

    private final IPawn pawn;
    private final int moodTier;
    private final double breakRisk;

    /**
     * @param pawn      涉事单位视图（非 null）
     * @param moodTier  当前心情档位
     * @param breakRisk 当前崩溃风险评估值
     */
    public MoodWarningEvent(IPawn pawn, int moodTier, double breakRisk) {
        this.pawn = Objects.requireNonNull(pawn, "pawn");
        this.moodTier = moodTier;
        this.breakRisk = breakRisk;
    }

    /** @return 涉事单位视图 */
    public IPawn pawn() {
        return pawn;
    }

    /** @return 当前心情档位 */
    public int moodTier() {
        return moodTier;
    }

    /** @return 当前崩溃风险评估值 */
    public double breakRisk() {
        return breakRisk;
    }
}
