package rimcraft_api.api.registry;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.resources.Identifier;

/**
 * 组件注册表的 API 内部单例：全项目统一的组件类型登记入口。
 *
 * <p>属契约基础设施：静态获取 + 简单 Map 实现；Core/DLC 经 {@link #get()} 取得同一实例登记与查询。
 */
public final class ComponentRegistries {

    private static final IComponentRegistry INSTANCE = new SimpleComponentRegistry();

    private ComponentRegistries() {
    }

    /**
     * 获取全局组件注册表单例。
     *
     * @return 全局注册表（非 null，全进程唯一）
     */
    public static IComponentRegistry get() {
        return INSTANCE;
    }

    /**
     * 基于 LinkedHashMap 的简单实现：按登记顺序枚举，冻结后拒绝写入。
     */
    private static final class SimpleComponentRegistry implements IComponentRegistry {

        private final Map<Identifier, ComponentType<?>> entries = new LinkedHashMap<>();
        private boolean frozen;

        @Override
        public synchronized void register(ComponentType<?> type) {
            if (frozen) {
                throw new IllegalStateException("组件注册表已冻结，拒绝登记: " + type.id());
            }
            if (entries.containsKey(type.id())) {
                throw new IllegalArgumentException("组件类型重复登记: " + type.id());
            }
            entries.put(type.id(), type);
        }

        @Override
        public synchronized Optional<ComponentType<?>> get(Identifier id) {
            return Optional.ofNullable(entries.get(id));
        }

        @Override
        public synchronized Collection<ComponentType<?>> all() {
            return Collections.unmodifiableCollection(entries.values());
        }

        @Override
        public synchronized void freeze() {
            frozen = true;
        }

        @Override
        public synchronized boolean isFrozen() {
            return frozen;
        }
    }
}
