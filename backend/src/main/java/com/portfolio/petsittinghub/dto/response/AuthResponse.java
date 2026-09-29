package com.portfolio.petsittinghub.dto.response;

public record AuthResponse(
        String token,
        String username
) {}
