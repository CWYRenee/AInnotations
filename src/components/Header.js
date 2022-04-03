import React, { useEffect, useState, useRef } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { AppBar, IconButton, Toolbar, Collapse } from '@material-ui/core';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import KeyboardVoiceSharpIcon from '@mui/icons-material/KeyboardVoiceSharp';
import { Link as Scroll } from 'react-scroll';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
    fontFamily: 'Nunito',
  },
  appbar: {
    background: 'none',
  },
  appbarWrapper: {
    width: '80%',
    margin: '0 auto',
  },
  appbarTitle: {
    flexGrow: '1',
    color: '#000080',
  },

  colorText: {
    color: '#000080',
  },
  container: {
    textAlign: 'center',
  },
  title: {
    color: '#000080',
    fontSize: '4.5rem',
  },
  icon: {
    color: '#000080',
    fontSize: '4rem',
  },
}));
export default function Header() {
  const classes = useStyles();
  const [checked, setChecked] = useState(false);
  useEffect(() => {
    setChecked(true);
  }, []);

  const fileRef = useRef();

  const handleChange = (e) => {
    const [file] = e.target.files;
    console.log(file);
  };

  return (
    <div className={classes.root} id="header">
      <AppBar className={classes.appbar} elevation={0}>
        <Toolbar className={classes.appbarWrapper}>
          <h1 className={classes.appbarTitle}>
            AInnotations
          </h1>
        </Toolbar>
      </AppBar>

      <Collapse
        in={checked}
        {...(checked ? { timeout: 1000 } : {})}
        collapsedHeight={50}
      >
        <div className={classes.container}>
          <h1 className={classes.title}>
            <span>Upload Now</span>
            <br/>
            <div>
            <IconButton onClick={() => fileRef.current.click()}>
              <KeyboardVoiceSharpIcon fontSize='large' className={classes.icon}/>
            </IconButton>
            <input
            ref={fileRef}
            onChange={handleChange}
            multiple={false}
            type="file"
            hidden
          />
            </div>
          </h1>
          <Scroll to="place-to-visit" smooth={true}>
            <IconButton>
              <ExpandMoreIcon className={classes.icon} />
            </IconButton>
          </Scroll>
        </div>
      </Collapse>
    </div>
  );
}
