package rimcraft_api.api.registry;

import java.util.Collection;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/**
 * 组件注册表：棋子单位组件类型的统一登记入口。
 *
 * <p>生命周期契约：启动期开放注册，全部 Core/DLC 组件登记完成后调用 {@link #freeze()} 冻结；
 * 冻结后注册表只读，{@link #register(ComponentType)} 拒绝并抛 {@link IllegalStateException}。
 * 冻结调用归 Core 启动流程。
 */
public interface IComponentRegistry {

    /**
     * 登记一个组件类型。
     *
     * @param type 组件类型登记项（非 null）
     * @throws IllegalStateException    注册表已冻结
     * @throws IllegalArgumentException 同 id 重复登记
     */
    void register(ComponentType<?> type);

    /**
     * 按标识查询登记项。
     *
     * @param id 组件类型标识（非 null）
     * @return 登记项；未登记时为空
     */
    Optional<ComponentType<?>> get(Identifier id);

    /**
     * 枚举全部登记项。
     *
     * @return 全部登记项的只读集合（按登记顺序；未登记任何类型时为空集合）
     */
    Collection<ComponentType<?>> all();

    /**
     * 冻结注册表：此后拒绝一切新登记。
     *
     * <p>重复调用为无操作。
     */
    void freeze();

    /**
     * 查询注册表是否已冻结。
     *
     * @return 已冻结返回 true
     */
    boolean isFrozen();
}
