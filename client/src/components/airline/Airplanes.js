import React, { useState, useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import { useDispatch, useSelector } from "react-redux";
import Container from "@material-ui/core/Container";
import { selectAirlineAirplanes } from "../../store/airline/selectors";
import { fetchAirlineAirplanes } from "../../store/airline/actions";
import Airplane from "./Airplane";

export default function Airplanes({ airlineId }){

    const classes = useStyles();
    const dispatch = useDispatch();
    const airplanes = useSelector(selectAirlineAirplanes);
    
    useEffect(() => {
        dispatch(fetchAirlineAirplanes({ airlineId }));
    }, [airlineId]);

    function renderAirplanes(){
        return  airplanes.map((airplane) =>{
            return  (
                <Airplane key={airplane.id} airplane={airplane}/>
            )
        })
    }
    return(
        <Container classes = {{
            root: classes.root
        }}>
            <h1>Airplanes</h1>
            <div className="rooms-container">
            { renderAirplanes() }
            </div>
        </Container>
    );
}

const useStyles = makeStyles(theme => ({
    root: {
      display: "flex",
      flexDirection: "column"
    }
  }));