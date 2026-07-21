package rimcraft_api.api.pawn;

import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.LivingEntity;
import rimcraft_api.api.component.IPawnComponent;

/**
 * 棋子单位只读视图：一切人形/动物/机械活动单位的统一抽象。
 *
 * <p>身份判定 = 「该实体是否挂载了棋子单位组件」，与实体类型解耦——玩家、村民、
 * 自定义生物均可能是棋子单位，调用方不得以实体类型推断身份。
 *
 * <p>视图为只读语义：状态变更经各组件接口或 Core 服务执行。实现归 Core。
 */
public interface IPawn {

    /**
     * 底层承载实体引用。
     *
     * @return 承载本单位的 LivingEntity（非 null，单位存活期内不变）
     */
    LivingEntity entity();

    /**
     * 身份类别标识（对应 Database 棋子单位定义条目）。
     *
     * @return 单位种类标识；未经数据定义初始化的裸单位为空
     */
    Optional<Identifier> pawnKindId();

    /**
     * 检查是否挂载了指定类型的组件。
     *
     * @param type 组件接口类型（非 null）
     * @return 已挂载返回 true；语义与 {@code IPawnAccessor.of(entity()).get(type).isPresent()} 一致
     */
    boolean hasComponent(Class<? extends IPawnComponent> type);
}
