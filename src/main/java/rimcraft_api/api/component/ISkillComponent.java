package rimcraft_api.api.component;

import net.minecraft.resources.Identifier;

/**
 * 技能组件：单位技能等级与经验的读写面。
 *
 * <p>技能定义条目经数据访问入口读取；升降级判定与通知执行归 Core。
 */
public interface ISkillComponent extends IPawnComponent {

    /**
     * 查询指定技能的等级。
     *
     * @param skillId 技能定义标识（非 null）
     * @return 技能等级（取值区间由数据条目定义）；未训练的技能返回数据条目缺省等级
     */
    int getLevel(Identifier skillId);

    /**
     * 查询指定技能的当前经验值。
     *
     * @param skillId 技能定义标识（非 null）
     * @return 当前经验值（0.0 及以上）
     */
    double getXp(Identifier skillId);

    /**
     * 增减指定技能的经验值。
     *
     * <p>升级/降级判定由 Core 实现执行，本方法只负责经验入账；负值扣减不下穿 0。
     *
     * @param skillId 技能定义标识（非 null）
     * @param amount  经验增减量（可为负）
     */
    void addXp(Identifier skillId, double amount);
}
