package rimcraft_api.api.component;

import java.util.Optional;
import net.minecraft.resources.Identifier;

/**
 * 健康状态实例的不可变视图：单位身上一项附加状态（创伤/疾病/增益等）的快照。
 *
 * <p>视图不可变：状态变更（严重程度推进、阶段跃迁）由健康组件实现产生新视图替换，
 * 调用方不得长期持有视图引用。实例创建归 Core。
 */
public interface IHediffInstance {

    /**
     * 健康状态定义标识（对应 Database 健康状态条目）。
     *
     * @return 定义标识（非 null）
     */
    Identifier hediffId();

    /**
     * 绑定身体部位标识。
     *
     * @return 部位标识；空表示全身性状态，不绑定特定部位
     */
    Optional<Identifier> bodyPart();

    /**
     * 当前严重程度。
     *
     * @return 严重程度（0.0 及以上；分档阈值由数据条目定义）
     */
    double severity();

    /**
     * 当前阶段序号。
     *
     * @return 阶段序号（从 0 起；由严重程度按数据条目阈值推导）
     */
    int stageIndex();
}
