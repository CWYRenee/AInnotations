// import React, { Component } from 'react'
// import { string, number, bool, func, oneOf, object } from 'prop-types'
// import { MicrophoneRecorder, MicrophoneRecorderMp3 } from '../libs/MicrophoneRecorder'
// import AudioContext from '../libs/AudioContext'
// import AudioPlayer from '../libs/AudioPlayer'
// import VisualizerMain from '../libs/Visualizer'

// export default class ReactMic extends Component {
//   constructor(props) {
//     super(props)
//     this.state = {
//       microphoneRecorder: null,
//       canvas: null,
//       canvasCtx: null
//     }
//     this.visualizer = React.createRef()
//     this.drawer = null
//   }

//   componentDidMount() {
//     const {
//       onSave,
//       onStop,
//       onStart,
//       onData,
//       audioElem,
//       audioBitsPerSecond,
//       mimeType,
//       bufferSize,
//       recorderParams,
//       sampleRate
//     } = this.props
//     const canvas = this.visualizer.current
//     const canvasCtx = canvas.getContext('2d')
//     const options = {
//       audioBitsPerSecond: audioBitsPerSecond,
//       mimeType: mimeType,
//       bufferSize: bufferSize,
//       sampleRate: sampleRate,
//       recorderParams: recorderParams
//     }

//     if (audioElem) {
//       AudioPlayer.create(audioElem)

//       this.setState({
//         canvas: canvas,
//         canvasCtx: canvasCtx
//       }, () => {
//         this.visualize()
//       })
//     } else {
//       const Recorder = this.props.mimeType === 'audio/mp3' ? MicrophoneRecorderMp3 : MicrophoneRecorder
//       this.setState({
//         microphoneRecorder: new Recorder(
//           onStart,
//           onStop,
//           onSave,
//           onData,
//           options
//         ),
//         canvas: canvas,
//         canvasCtx: canvasCtx
//       }, () => {
//         this.visualize()
//       })
//     }
//   }

//   visualize() {
//     const self = this
//     const { backgroundColor, strokeColor, width, height, visualSetting } = this.props
//     const { canvas, canvasCtx } = this.state

//     if (visualSetting === 'sinewave') {
//       this.drawer = VisualizerMain.visualizeSineWave(canvasCtx, canvas, width, height, backgroundColor, strokeColor)
//     } else if (visualSetting === 'frequencyBars') {
//       this.drawer = VisualizerMain.visualizeFrequencyBars(canvasCtx, canvas, width, height, backgroundColor, strokeColor)
//     } else if (visualSetting === 'frequencyCircles') {
//       this.drawer = VisualizerMain.visualizeFrequencyCircles(canvasCtx, canvas, width, height, backgroundColor, strokeColor)
//     }
//   }

//   clear() {
//     const { width, height } = this.props
//     const { canvasCtx } = this.state
//     canvasCtx.clearRect(0, 0, width, height)
//   }

//   render() {
//     const { record, onStop, width, height } = this.props
//     const { microphoneRecorder, canvasCtx } = this.state

//     if (record) {
//       if (microphoneRecorder) {
//         microphoneRecorder.startRecording()
//       }
//     } else {
//       if (microphoneRecorder) {
//         microphoneRecorder.stopRecording(onStop)
//         if (this.drawer) {
//           this.drawer()
//         }
//         this.clear()
//       }
//     }

//     return (<canvas ref={this.visualizer} height={height} width={width} className={this.props.className} />)
//   }
// }

// ReactMic.propTypes = {
//   backgroundColor: string,
//   strokeColor: string,
//   className: string,
//   audioBitsPerSecond: number,
//   mimeType: string,
//   height: number,
//   record: bool.isRequired,
//   onStop: func,
//   onData: func,
//   bufferSize: oneOf([0, 256, 512, 1024, 2048, 4096, 8192, 16384]),
//   sampleRate: number,
//   recorderParams: object
// }

// ReactMic.defaultProps = {
//   backgroundColor: 'rgba(255, 255, 255, 0.5)',
//   strokeColor: '#000000',
//   className: 'visualizer',
//   audioBitsPerSecond: 128000,
//   mimeType: 'audio/wav',
//   bufferSize: 2048,
//   sampleRate: 44100,
//   record: false,
//   width: 640,
//   height: 100,
//   visualSetting: 'sinewave',
//   recorderParams: {}
// }

import React from 'react';
import IconButton from '@mui/material/IconButton';
import MicOffIcon from '@mui/icons-material/MicOff';
import KeyboardVoiceSharpIcon from '@mui/icons-material/KeyboardVoiceSharp';
import MicRecorder from 'mic-recorder-to-mp3';
import Stack from '@mui/material/Stack';

const Mp3Recorder = new MicRecorder({ bitRate: 128 });

class Record extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      isRecording: false,
      blobURL: '',
      isBlocked: false,
    };
  }

  start = () => {
    if (this.state.isBlocked) {
      console.log('Permission Denied');
    } else {
      Mp3Recorder
        .start()
        .then(() => {
          this.setState({ isRecording: true });
        }).catch((e) => console.error(e));
    }
  };

  stop = () => {
    Mp3Recorder
      .stop()
      .getMp3()
      .then(([buffer, blob]) => {
        const blobURL = URL.createObjectURL(blob)
        this.setState({ blobURL, isRecording: false });
      }).catch((e) => console.log(e));
  };

  componentDidMount() {
    navigator.getUserMedia({ audio: true },
      () => {
        console.log('Permission Granted');
        this.setState({ isBlocked: false });
      },
      () => {
        console.log('Permission Denied');
        this.setState({ isBlocked: true })
      },
    );
  }

  render(){
    return (
      <div >
        <header>
        <Stack direction="row" spacing={12}>
        <IconButton onClick={this.start} disabled={this.state.isRecording}>
            <KeyboardVoiceSharpIcon fontSize='large' />
        </IconButton>
        <IconButton onClick={this.stop} disabled={!this.state.isRecording}>
            <MicOffIcon fontSize='large' />
        </IconButton>
            <audio src={this.state.blobURL} controls="controls" />
        </Stack>
        </header>
      </div>
    );
  }
}

export default Record;