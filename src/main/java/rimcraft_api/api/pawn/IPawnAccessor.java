package rimcraft_api.api.pawn;

import java.util.Optional;
import java.util.Set;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import rimcraft_api.api.component.IPawnComponent;

/**
 * 组件访问入口：对任意实体按组件类型查询已挂载的棋子单位组件。
 *
 * <p>访问入口对调用方隐藏挂载细节（NeoForge Attachment）；有则返回接口视图，
 * 无则返回空结果，不抛异常。客户端经同一入口读取服务端同步的镜像数据。
 */
public interface IPawnAccessor {

    /**
     * 按组件接口类型取组件。
     *
     * @param <T>  组件接口类型
     * @param type 组件接口 Class（非 null；须为注册表已登记的接口类型，未登记时返回空）
     * @return 组件视图；该实体未挂载此组件（或不是棋子单位）时为空
     */
    <T extends IPawnComponent> Optional<T> get(Class<T> type);

    /**
     * 枚举该实体已挂载的组件类型标识。
     *
     * @return 已挂载组件的类型标识集合（只读视图；未挂载任何组件时为空集合）
     */
    Set<Identifier> mountedComponentIds();

    /**
     * 获取指定实体的组件访问入口。
     *
     * <p>每次调用返回一个轻量访问器实例；访问器不缓存组件状态，可短期持有。
     *
     * @param entity 目标实体（非 null）
     * @return 该实体的访问入口（非 null；对非棋子单位实体返回的入口各处查询均为空结果）
     */
    static IPawnAccessor of(LivingEntity entity) {
        return new PawnAccessorImpl(entity);
    }
}
