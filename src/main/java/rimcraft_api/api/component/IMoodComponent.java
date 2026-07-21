package rimcraft_api.api.component;

import java.util.List;
import net.minecraft.resources.Identifier;

/**
 * 心情组件：思绪的附加/移除/查询与心情总评、崩溃风险的读取面。
 *
 * <p>心情档位定义与思绪条目经数据访问入口读取；心情恶化预警与崩溃经事件通知
 * （{@code MoodWarningEvent} / {@code MoodBreakEvent}）。结算执行归 Core。
 */
public interface IMoodComponent extends IPawnComponent {

    /**
     * 查询当前心情总评档位。
     *
     * @return 心情档位序号（由活跃思绪按数据条目权重汇总推导）
     */
    int getMoodTier();

    /**
     * 读取当前活跃思绪列表。
     *
     * @return 活跃思绪定义标识列表（只读视图，不得修改；无思绪时为空列表）
     */
    List<Identifier> getActiveThoughts();

    /**
     * 附加一项思绪。
     *
     * <p>重复附加同 id 思绪的叠加/刷新语义由数据条目与 Core 实现定义。
     *
     * @param thoughtId 思绪定义标识（非 null）
     */
    void addThought(Identifier thoughtId);

    /**
     * 移除一项思绪。
     *
     * @param thoughtId 思绪定义标识（非 null）；未持有时调用为无操作
     */
    void removeThought(Identifier thoughtId);

    /**
     * 查询崩溃风险评估值。
     *
     * @return 崩溃风险（0.0 及以上；预警/崩溃阈值由数据条目定义）
     */
    double getBreakRisk();
}
