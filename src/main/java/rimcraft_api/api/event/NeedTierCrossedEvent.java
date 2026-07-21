package rimcraft_api.api.event;

import java.util.Objects;
import net.minecraft.resources.Identifier;
import rimcraft_api.api.pawn.IPawn;

/**
 * 需求档位跃迁事件。
 *
 * <p>触发时机：单位某项需求的档位因需求值变化而跨越阈值后发布（与 {@link NeedChangedEvent} 同次结算内追加），
 * 由 Core 的需求结算执行。不可取消（通知语义）。
 */
public final class NeedTierCrossedEvent extends RimCraftEvent {

    private final IPawn pawn;
    private final Identifier needId;
    private final int fromTier;
    private final int toTier;

    /**
     * @param pawn     涉事单位视图（非 null）
     * @param needId   需求定义标识（非 null）
     * @param fromTier 跃迁前的档位
     * @param toTier   跃迁后的档位
     */
    public NeedTierCrossedEvent(IPawn pawn, Identifier needId, int fromTier, int toTier) {
        this.pawn = Objects.requireNonNull(pawn, "pawn");
        this.needId = Objects.requireNonNull(needId, "needId");
        this.fromTier = fromTier;
        this.toTier = toTier;
    }

    /** @return 涉事单位视图 */
    public IPawn pawn() {
        return pawn;
    }

    /** @return 需求定义标识 */
    public Identifier needId() {
        return needId;
    }

    /** @return 跃迁前的档位 */
    public int fromTier() {
        return fromTier;
    }

    /** @return 跃迁后的档位 */
    public int toTier() {
        return toTier;
    }
}
