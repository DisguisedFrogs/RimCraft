package rimcraft_api.api.component;

import java.util.Optional;
import net.minecraft.resources.Identifier;

/**
 * 工作组件：单位工作优先级读写与可执行性查询面。
 *
 * <p>工作类型注册项与任务来源抽象（IWorkType / IWorkGiver）归 M2 里程碑，M1 只到优先级与状态查询粒度。
 * 工作调度执行归 Core。
 */
public interface IWorkComponent extends IPawnComponent {

    /**
     * 查询指定工作类型的优先级。
     *
     * @param workTypeId 工作类型标识（非 null）
     * @return 优先级（1~4，数值越小越优先；0 或缺省语义表示不从事，由数据条目与 Core 实现定义）
     */
    int getPriority(Identifier workTypeId);

    /**
     * 设置指定工作类型的优先级。
     *
     * <p>约束：取值 1~4（数值越小越优先）；越界值由实现方钳制或拒绝，契约以数据条目范围为准。
     *
     * @param workTypeId 工作类型标识（非 null）
     * @param priority   优先级（1~4）
     */
    void setPriority(Identifier workTypeId, int priority);

    /**
     * 查询当前正在执行的任务。
     *
     * @return 当前任务的工作类型标识；空闲时为空
     */
    Optional<Identifier> getCurrentJob();

    /**
     * 判定该单位当前是否可执行指定工作类型。
     *
     * <p>综合优先级、技能、健康等因素的判定执行归 Core；本方法只是查询入口。
     *
     * @param workTypeId 工作类型标识（非 null）
     * @return 可执行返回 true
     */
    boolean canExecute(Identifier workTypeId);
}
