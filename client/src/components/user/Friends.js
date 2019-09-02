import React, { useState } from "react";
import {useSelector} from "react-redux";
import MaterialTable from 'material-table';
import { selectUserFriends } from "../../store/user/selectors";

export default function Friends({ userId }){

    const userFriends  = useSelector(selectUserFriends);

    const [state, setState] = useState({
        columns: [
            { title: 'First name', field: 'firstname' },
            { title: 'Last name', field: 'lastname' },

        ],
        data: []
    });

    userFriends.forEach(function( friend ){
        state.data.push(friend);
    });

    return (
        <MaterialTable
            title="Friends"
            columns={state.columns}
            data={state.data}
        />
    );
}