import React from "react";
import UserInformation from "../components/user/Information";

const UserProfile = ({ match }) => {

    return (
        <div>
            <UserInformation userId = {match.params.id}/>
        </div>
    );
};

export default UserProfile;