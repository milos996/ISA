package com.example.ISAums.constants;

import com.example.ISAums.model.enumeration.InvitationStatus;

import java.util.UUID;

import static com.example.ISAums.model.enumeration.InvitationStatus.PENDING;

public class UserConstants {


    public static final String NEW_FIRST_NAME = "Pera";
    public static final String NEW_LAST_NAME = "Peric";
    public static final String NEW_CITY = "Novi Sad";
    public static final String NEW_STATE = "Serbia";
    public static final String NEW_PHONE = "0600001112";
    public static final String NEW_PASSWORD = "user";
    public static final String NEW_EMAIL = "pera@gmail.com";

    public static final UUID DB_ID = UUID.fromString("2dca6591-d530-45ef-b3e1-e640c690d20a");
    public static final String DB_FIRST_NAME = "Igor";
    public static final String DB_LAST_NAME = "Markovic";
    public static final String DB_CITY = "Belgrade";
    public static final String DB_STATE = "Serbia";
    public static final String DB_PHONE = "0605050123";
    public static final String DB_PASSWORD = "111";
    public static final String DB_EMAIL = "igor@gmail.com";

    public static final Integer DB_NUMBER_OF_FRIENDS = 0;

    public static final InvitationStatus INVITATION_STATUS = PENDING;
    public static final String INVITED_USER_USERNAME = "marta@gmail.com";
    public static final String INVITED_USER_ID = "2a47c549-ebe1-4702-9d83-4e7f602bf945";
}
