package rimcraft_api.api.component;

import java.util.Optional;
import net.minecraft.resources.Identifier;

/**
 * 派系组件：单位派系归属的查询面。
 *
 * <p>派系查询、好感读写、敌对状态判定等派系服务（IFactionService）归 M2 里程碑；
 * M1 只到归属查询粒度。归属变更执行归 Core。
 */
public interface IFactionComponent extends IPawnComponent {

    /**
     * 查询所属派系标识。
     *
     * @return 派系定义标识；无派系归属（野单位/中立单位）时为空
     */
    Optional<Identifier> getFactionId();
}
