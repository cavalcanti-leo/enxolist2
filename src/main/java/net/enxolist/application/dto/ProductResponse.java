package net.enxolist.application.dto;

import java.math.BigDecimal;

public record ProductResponse(
        String id,
        String name,
        String urlLink,
        String image,
        String userID,
        Boolean wasBought,
        Integer category,
        BigDecimal price
) {
}
