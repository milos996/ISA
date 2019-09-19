import React from "react";
import RACAdminInformation from "../components/user/RACAdminInformation";

export default function RACAdminProfile({ match }) {
  return <RACAdminInformation userId={match.params.id} />;
}
