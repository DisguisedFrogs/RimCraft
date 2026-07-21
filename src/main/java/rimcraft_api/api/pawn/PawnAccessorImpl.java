package rimcraft_api.api.pawn;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import rimcraft_api.api.component.IPawnComponent;
import rimcraft_api.api.registry.ComponentRegistries;
import rimcraft_api.api.registry.ComponentType;
import rimcraft_api.api.registry.PawnComponentAttachments;

/**
 * {@link IPawnAccessor} 的包私有实现：经 api.registry 的 Attachment 骨架读取组件。
 *
 * <p>组件定位策略：按组件注册表中登记的「接口类型 → 类型标识」映射查附件映射表；
 * 未在注册表登记的接口类型一律返回空结果（对齐「注册后冻结、未登记不可见」契约）。
 */
final class PawnAccessorImpl implements IPawnAccessor {

    private final LivingEntity entity;

    PawnAccessorImpl(LivingEntity entity) {
        this.entity = Objects.requireNonNull(entity, "entity");
    }

    @Override
    public <T extends IPawnComponent> Optional<T> get(Class<T> type) {
        Objects.requireNonNull(type, "type");
        Optional<Map<Identifier, IPawnComponent>> mounted = entity.getExistingData(PawnComponentAttachments.PAWN_COMPONENTS);
        if (mounted.isEmpty()) {
            return Optional.empty();
        }
        Optional<ComponentType<?>> registered = ComponentRegistries.get().all().stream()
                .filter(componentType -> componentType.interfaceClass().equals(type))
                .findFirst();
        if (registered.isEmpty()) {
            return Optional.empty();
        }
        IPawnComponent component = mounted.get().get(registered.get().id());
        return Optional.ofNullable(type.cast(component));
    }

    @Override
    public Set<Identifier> mountedComponentIds() {
        return entity.getExistingData(PawnComponentAttachments.PAWN_COMPONENTS)
                .map(map -> Set.copyOf(map.keySet()))
                .orElse(Set.of());
    }
}
