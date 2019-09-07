import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { selectDestinations } from "../../store/airline/selectors";
import { makeStyles } from "@material-ui/core/styles";
import { Container } from "@material-ui/core";
import { TableCell } from '@material-ui/core';
import { TableHead } from '@material-ui/core';
import { TableRow } from '@material-ui/core';
import { Table } from '@material-ui/core';
import { TableBody } from '@material-ui/core';
import { fetchAirlineDestinations } from "../../store/airline/actions";

export default function Destinations({ airlineId }) {

    const dispatch = useDispatch();
    const classes = useStyles();
    const destinations = useSelector(selectDestinations);
    const [columns, setColumns] = useState(
        [{ title: 'State', field: 'state' },
        { title: 'City', field: 'city' }
        ]
    );

    useEffect(() => {
        dispatch(fetchAirlineDestinations({ airlineId }));
    }, [airlineId]);

    function renderTableData() {

        return destinations.map((airlineDestination) => {
            return (
                <TableRow key={airlineDestination.destination.id}>
                    <TableCell align="left">{airlineDestination.destination.state}</TableCell>
                    <TableCell align="left">{airlineDestination.destination.city}</TableCell>
                </TableRow>)
        })
    }
    return (
        <Container classes={{
            root: classes.root
        }}>

            <Table className={classes.table}>
                <TableHead>
                    <TableRow>
                        <TableCell align="left">{columns[0].title}</TableCell>
                        <TableCell align="left">{columns[1].title}</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {renderTableData()}
                </TableBody>
            </Table>
        </Container>
    );
}

const useStyles = makeStyles(theme => ({
    root: {
        width: '100%',
        marginTop: theme.spacing(3),
        overflowX: 'auto',
        height: "100%"
    },
    margin: {
        margin: theme.spacing(1)
    },
    button: {
        margin: theme.spacing(1)
    },
    table: {
        minWidth: 650,
    }
}));
