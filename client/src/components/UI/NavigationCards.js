import React from "react";
import { makeStyles } from "@material-ui/core/styles";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardContent from "@material-ui/core/CardContent";
import CardMedia from "@material-ui/core/CardMedia";
import Typography from "@material-ui/core/Typography";
import Tooltip from "@material-ui/core/Tooltip";
import teal from "@material-ui/core/colors/teal";

const selectedCardColor = teal[400];

export default function NavigationCards({
  id = -1,
  image,
  title,
  description,
  cardClick,
  tooltip = "",
  selected = false,
  onMouseEnter = () => {}
}) {
  const classes = useStyles();

  return (
    <Card
      className={classes.card}
      onClick={cardClick}
      classes={{
        root: selected ? classes.selectedCard : null
      }}
      onMouseEnter={e => {
        onMouseEnter(id);
      }}
    >
      <Tooltip
        classes={{
          tooltip: classes.tooltip
        }}
        title={tooltip}
        placement="right"
      >
        <CardActionArea>
          <CardMedia className={classes.media} image={image} title={title} />
          <CardContent>
            <Typography gutterBottom variant="h5" component="h2">
              {title}
            </Typography>
            <Typography variant="body2" color="textSecondary" component="p">
              {description}
            </Typography>
          </CardContent>
        </CardActionArea>
      </Tooltip>
    </Card>
  );
}

const useStyles = makeStyles({
  card: {
    width: 400,
    marginTop: 15,
    marginRight: 10,
    height: 250,
    paddingBottom: 10
  },
  media: {
    height: 140,
    backgroundSize: "contain"
  },
  tooltip: {
    fontSize: 16
  },
  selectedCard: {
    background: selectedCardColor
  }
});
