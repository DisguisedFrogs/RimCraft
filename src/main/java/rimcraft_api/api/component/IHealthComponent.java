package rimcraft_api.api.component;

import java.util.List;
import java.util.Set;
import net.minecraft.resources.Identifier;

/**
 * 健康组件：身体部位模型与附加健康状态的增删查面。
 *
 * <p>部位集合按单位种类可异构（人形/动物等，粒度见待定项 T4）；状态实例以不可变视图暴露。
 * 状态推进与整体健康度结算执行归 Core。
 */
public interface IHealthComponent extends IPawnComponent {

    /**
     * 枚举该单位的全部身体部位。
     *
     * @return 部位标识集合（只读视图，不得修改；无部位模型的单位为空集合）
     */
    Set<Identifier> getBodyParts();

    /**
     * 读取当前全部健康状态实例。
     *
     * @return 状态实例不可变视图列表（只读，不得修改；无状态时为空列表）
     */
    List<IHediffInstance> getHediffs();

    /**
     * 附加一项健康状态。
     *
     * <p>与既有状态的叠加/冲突消解语义由 Core 实现按数据条目定义执行。
     *
     * @param instance 状态实例（非 null）
     */
    void addHediff(IHediffInstance instance);

    /**
     * 移除指定定义的全部健康状态实例。
     *
     * @param hediffId 健康状态定义标识（非 null）
     * @return 实际移除过实例返回 true；未持有该状态时返回 false
     */
    boolean removeHediff(Identifier hediffId);

    /**
     * 查询整体健康度。
     *
     * @return 整体健康度（0.0~1.0，由部位与状态按数据条目权重汇总；1.0 为完全健康）
     */
    double getOverallHealth();
}
