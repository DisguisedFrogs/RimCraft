package rimcraft_api.api.registry;

import java.util.List;
import java.util.Objects;
import net.minecraft.resources.Identifier;
import rimcraft_api.api.component.IPawnComponent;

/**
 * 组件类型登记项：一种棋子单位组件在注册表中的登记信息。
 *
 * <p>登记窗口在启动期开放、注册后冻结（见 {@link IComponentRegistry#freeze()}）；
 * Core 启动期注册全部 Core 组件，DLC 注册自身组件。
 *
 * @param id             组件类型标识（namespace:path，全项目唯一；组件实例的 {@code typeId()} 须与之相等）
 * @param interfaceClass 组件接口类型（须为 {@link IPawnComponent} 的子接口；访问入口按此类型反查登记 id）
 * @param dependencies   前置组件声明：本组件挂载前须先挂载的组件类型标识列表（挂载顺序按依赖拓扑保证；无前置为空列表）
 * @param <C>            组件接口类型参数
 */
public record ComponentType<C extends IPawnComponent>(Identifier id, Class<C> interfaceClass, List<Identifier> dependencies) {

    public ComponentType {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(interfaceClass, "interfaceClass");
        dependencies = List.copyOf(Objects.requireNonNull(dependencies, "dependencies"));
    }
}
