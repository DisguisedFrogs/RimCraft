package rimcraft_api.api.component;

import net.minecraft.resources.Identifier;

/**
 * 需求组件：随时间消耗、需被满足的生理/心理指标的读写面。
 *
 * <p>需求值变更与档位跃迁的通知经事件发布（{@code NeedChangedEvent} / {@code NeedTierCrossedEvent}），
 * 本接口不内嵌订阅机制。需求定义条目经数据访问入口读取；结算执行归 Core。
 */
public interface INeedComponent extends IPawnComponent {

    /**
     * 查询指定需求的当前值。
     *
     * @param needId 需求定义标识（非 null）
     * @return 当前需求值（取值区间由数据条目定义）；未持有的需求返回其数据条目缺省值
     */
    double getNeedValue(Identifier needId);

    /**
     * 查询指定需求的当前档位。
     *
     * @param needId 需求定义标识（非 null）
     * @return 档位序号（由需求值按数据条目阈值推导）
     */
    int getNeedTier(Identifier needId);

    /**
     * 调整指定需求的值。
     *
     * <p>正值补充、负值消耗；结果按数据条目区间钳制。值变化与档位跃迁经事件通知。
     *
     * @param needId 需求定义标识（非 null）
     * @param delta  调整量（可为负）
     */
    void adjustNeed(Identifier needId, double delta);
}
