package com.innotice.discord.server.resource.server.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.odendeberia.dao.AbstractCriteria;
import org.odendeberia.dao.DaoUtil;
import org.springframework.data.util.Pair;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DiscordServerFilter extends AbstractCriteria {

    private Long id;
    private Set<Long> ids;

    @Override
    public Map<String, Object> mapCriteriaToQueryArgs(StringBuilder sql) {
        Map<String, Object> args = new HashMap<>();
        boolean whereClauseAdded = false;

        if (id != null) {
            appendWhereOrAndClause(whereClauseAdded, sql);
            whereClauseAdded = true;
            sql.append("id = :id");
            args.put("id", id);
        }

        if (!CollectionUtils.isEmpty(ids)) {
            appendWhereOrAndClause(whereClauseAdded, sql);
            Pair<String, Map<String, Object>> sqlArgs = DaoUtil.buildInStatementTuple(ids, "discordServerId");
            sql.append("id in ").append(sqlArgs.getFirst());
            args.putAll(sqlArgs.getSecond());
        }

        return args;
    }
}
