import React, { useEffect, useState, useRef} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import { AppBar, IconButton, Toolbar, Collapse } from '@material-ui/core';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { Link as Scroll } from 'react-scroll';
import Download from './Download';
import Record from './Record';

const useStyles = makeStyles((theme) => ({
  root: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '100vh',
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
  download: {
    display: 'flex',
    justifyContent: 'center',
  }
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
        <Scroll to="info_cards" smooth={true}>
          <h1 className={classes.appbarTitle}>
            AInnotations
          </h1>
          </Scroll>
        </Toolbar>
      </AppBar>

      <Collapse
        in={checked}
        {...(checked ? { timeout: 1000 } : {})}
        collapsedHeight={50}
      >
        <div className={classes.container}>
          <h1 className={classes.title}>
            <span>Record Now</span>
            <br/>
            <br/>
            <div alignItems= 'center'>
            <Record/>
            <br/>
            <br/>
            {/* <IconButton onClick={() => {fileRef.current.click()}}>
              <KeyboardVoiceSharpIcon fontSize='large' className={classes.icon}/>
            </IconButton> */}
            <input
            ref={fileRef}
            onChange={handleChange}
            multiple={false}
            type="file"
            hidden
          />
            </div>
          </h1>
          <div className={classes.download}>
          <Download />
          </div>
          <br/>
          <Scroll to="info_cards" smooth={true}>
            <IconButton>
              <ExpandMoreIcon className={classes.icon} />
            </IconButton>
          </Scroll>
        </div>
      </Collapse>
    </div>
  );
}
