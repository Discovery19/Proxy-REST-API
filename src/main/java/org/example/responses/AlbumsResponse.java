package org.example.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AlbumsResponse(
        @JsonProperty("userId") Long userId,
        @JsonProperty("id") Long id,
        @JsonProperty("title") String title

) {
}
