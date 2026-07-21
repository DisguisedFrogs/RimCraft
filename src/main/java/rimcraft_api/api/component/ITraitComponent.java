package rimcraft_api.api.component;

import java.util.Set;
import net.minecraft.resources.Identifier;

/**
 * 特性组件：单位持有特性的查询面。
 *
 * <p>特性效果钩子（ITraitHook）归 M2 里程碑，效果实现归 Core/DLC；M1 只到持有查询粒度。
 */
public interface ITraitComponent extends IPawnComponent {

    /**
     * 读取持有的全部特性。
     *
     * @return 特性定义标识集合（只读视图，不得修改；无特性时为空集合）
     */
    Set<Identifier> getTraits();

    /**
     * 判定是否持有指定特性。
     *
     * @param traitId 特性定义标识（非 null）
     * @return 持有返回 true
     */
    boolean hasTrait(Identifier traitId);
}
