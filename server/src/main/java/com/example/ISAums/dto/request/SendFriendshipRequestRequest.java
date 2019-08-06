package com.example.ISAums.dto.request;
import com.example.ISAums.model.enumeration.InvitationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendFriendshipRequestRequest {

    private UpdateUserProfileRequest sender;

    private UpdateUserProfileRequest invitedUser;

    private InvitationStatus invitationStatus;

}
