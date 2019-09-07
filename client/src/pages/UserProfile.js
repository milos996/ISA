import React from "react";
import UserInformation from "../components/user/Information";

export default function UserProfile({ match }){
    
  return (  
      <UserInformation userId={match.params.id} />
  );
};