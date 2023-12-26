package com.innotice.model.domain.discord.server;

import com.fasterxml.jackson.annotation.JsonInclude;
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

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DiscordServerFilter extends AbstractCriteria {

    private Long id;
    private Set<Long> ids;
    private Set<Long> discordChannelIds;
    private Set<Long> streamerIds;

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
            whereClauseAdded = true;
            Pair<String, Map<String, Object>> sqlArgs = DaoUtil.buildInStatementTuple(ids, "discordServerId");
            sql.append("id in ").append(sqlArgs.getFirst());
            args.putAll(sqlArgs.getSecond());
        }

        if (!CollectionUtils.isEmpty(discordChannelIds)) {
            appendWhereOrAndClause(whereClauseAdded, sql);
            whereClauseAdded = true;
            Pair<String, Map<String, Object>> sqlArgs = DaoUtil.buildInStatementTuple(discordChannelIds, "discordChannelId");
            sql.append("channel_id in ").append(sqlArgs.getFirst());
            args.putAll(sqlArgs.getSecond());
        }

        if (!CollectionUtils.isEmpty(streamerIds)) {
            appendWhereOrAndClause(whereClauseAdded, sql);
            Pair<String, Map<String, Object>> sqlArgs = DaoUtil.buildInStatementTuple(streamerIds, "streamerId");
            sql.append("streamer_id in ").append(sqlArgs.getFirst());
            args.putAll(sqlArgs.getSecond());
        }

        return args;
    }
}
