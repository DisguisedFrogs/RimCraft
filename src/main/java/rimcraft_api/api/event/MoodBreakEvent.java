package rimcraft_api.api.event;

import java.util.Objects;
import rimcraft_api.api.pawn.IPawn;

/**
 * 心情崩溃事件。
 *
 * <p>触发时机：单位崩溃风险评估到达崩溃阈值、崩溃行为即将接管单位时发布，由 Core 的心情结算执行。
 * 不可取消（崩溃既成事实的通知语义；拦截应在预警阶段经 {@link MoodWarningEvent} 处理）。
 */
public final class MoodBreakEvent extends RimCraftEvent {

    private final IPawn pawn;
    private final int moodTier;

    /**
     * @param pawn     涉事单位视图（非 null）
     * @param moodTier 崩溃时的心情档位
     */
    public MoodBreakEvent(IPawn pawn, int moodTier) {
        this.pawn = Objects.requireNonNull(pawn, "pawn");
        this.moodTier = moodTier;
    }

    /** @return 涉事单位视图 */
    public IPawn pawn() {
        return pawn;
    }

    /** @return 崩溃时的心情档位 */
    public int moodTier() {
        return moodTier;
    }
}
