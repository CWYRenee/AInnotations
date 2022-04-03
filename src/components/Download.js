import React, {Component} from 'react';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';


export default function Download() {
    const [lang, setLang] = React.useState('');
    const handleChange = (event) => {setLang(event.target.value);
    };
    return (
    <Stack direction="row" spacing={12}>
        <Button variant="contained">Download</Button>
        <Box sx={{ minWidth: 120 }}>
        <FormControl fullWidth>
            <InputLabel id="simple-select-label">Language</InputLabel>
            <Select
            labelId="simple-select-label"
            id="demo-simple-select"
            value={lang}
            label="Language"
            onChange={handleChange}
            >
            <MenuItem value={10}>Ukrainian</MenuItem>
            <MenuItem value={20}>French</MenuItem>
            <MenuItem value={30}>Spanish</MenuItem>
            </Select>
        </FormControl>
        </Box>
        </Stack>
  )
}


// export default function Download(){
//     const [lang, setLang] = React.useState('');
//     const handleChange = (event) => {setLang(event.target.value);

//     return(
//         <Stack direction="row" spacing={3}>
//         <Button variant="contained">Download</Button>
//         <Box sx={{ minWidth: 120 }}>
//         <FormControl fullWidth>
//             <InputLabel id="simple-select-label">Language</InputLabel>
//             <Select
//             labelId="simple-select-label"
//             id="demo-simple-select"
//             value={lang}
//             label="Language"
//             onChange={handleChange}
//             >
//             <MenuItem value={10}>Ukrainian</MenuItem>
//             <MenuItem value={20}>French</MenuItem>
//             <MenuItem value={30}>Spanish</MenuItem>
//             </Select>
//         </FormControl>
//         </Box>
//         </Stack>
//     )

// }
// }
