/**
I will call this program every 100 ms
It needs to print on a txt file pitch, volume

fft.analyize() -> array with index freq, value vol
freq with the highest vol
vol

print on a txt file pitch, volume
**/

mic = new p5.AudioIn();
mic.start();
fft = new p5.FFT();
let spectrum = fft.analyze();
max = Math.max(spectrum);
maxInd = spectrum.indexOf(max);

// creates a file
let writer = createWriter('freqVol.txt');
// write to the file
writer.write(maxInd+","+max);
// close the PrintWriter and save the file
writer.close();