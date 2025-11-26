package net.enxolist.application.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "O nome do item é obrigatório.") String name,
        MultipartFile imageFile,
        String urlLink,
        Boolean wasBought,
        @NotNull(message = "O item deve ser categorizado.") Integer category,
        BigDecimal price
) {
}
