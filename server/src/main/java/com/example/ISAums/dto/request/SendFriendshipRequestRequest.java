package com.example.ISAums.dto.request;
import com.example.ISAums.model.enumeration.InvitationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendFriendshipRequestRequest {

    private UUID senderId;

    private UUID invitedUserId;

}
