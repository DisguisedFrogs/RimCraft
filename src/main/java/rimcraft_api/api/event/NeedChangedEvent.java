package rimcraft_api.api.event;

import java.util.Objects;
import net.minecraft.resources.Identifier;
import rimcraft_api.api.pawn.IPawn;

/**
 * 需求值变化事件。
 *
 * <p>触发时机：单位某项需求值经调整（消耗/补充）实际发生变化后发布，由 Core 的需求结算执行。
 * 不可取消（通知语义）。档位跃迁的专门通知见 {@link NeedTierCrossedEvent}。
 */
public final class NeedChangedEvent extends RimCraftEvent {

    private final IPawn pawn;
    private final Identifier needId;
    private final double oldValue;
    private final double newValue;

    /**
     * @param pawn     涉事单位视图（非 null）
     * @param needId   需求定义标识（非 null）
     * @param oldValue 变化前的需求值
     * @param newValue 变化后的需求值
     */
    public NeedChangedEvent(IPawn pawn, Identifier needId, double oldValue, double newValue) {
        this.pawn = Objects.requireNonNull(pawn, "pawn");
        this.needId = Objects.requireNonNull(needId, "needId");
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    /** @return 涉事单位视图 */
    public IPawn pawn() {
        return pawn;
    }

    /** @return 需求定义标识 */
    public Identifier needId() {
        return needId;
    }

    /** @return 变化前的需求值 */
    public double oldValue() {
        return oldValue;
    }

    /** @return 变化后的需求值 */
    public double newValue() {
        return newValue;
    }
}
