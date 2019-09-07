package com.example.ISAums.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendFriendshipRequestRequest {

    @NotNull
    private UUID senderUserId;

    @NotNull
    private UUID invitedUserId;

}
