import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import teal from "@material-ui/core/colors/teal";
import TabPanel from "./UI/TabPanel";
import CreateAirline from "./CreateAirline";
import CreateHotel from "./CreateHotel";
import CreateRentACar from "./CreateRentACar";

const primary = teal[200];

function a11yProps(index) {
  return {
    id: `simple-tab-${index}`,
    "aria-controls": `simple-tabpanel-${index}`
  };
}

export default function EntityAddTabs() {
  const classes = useStyles();
  const [currentTab, setCurrentTab] = React.useState(0);

  function handleChange(event, newValue) {
    setCurrentTab(newValue);
  }

  return (
    <div className={classes.root}>
      <AppBar position="static">
        <Tabs
          value={currentTab}
          onChange={handleChange}
          aria-label="simple tabs example"
          className={classes.tabs}
        >
          <Tab label="Add Airline" {...a11yProps(0)} />
          <Tab label="Add Hotel" {...a11yProps(1)} />
          <Tab label="Add Rent a car" {...a11yProps(2)} />
        </Tabs>
      </AppBar>
      <TabPanel value={currentTab} index={0}>
        <CreateAirline />
      </TabPanel>
      <TabPanel value={currentTab} index={1}>
        <CreateHotel />
      </TabPanel>
      <TabPanel value={currentTab} index={2}>
        <CreateRentACar />
      </TabPanel>
    </div>
  );
}

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
    backgroundColor: theme.palette.background.paper
  },
  tabs: {
    backgroundColor: primary
  }
}));
