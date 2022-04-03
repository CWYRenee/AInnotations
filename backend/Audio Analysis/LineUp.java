class LineUp{
	public LineUp(ArrayList<Word> transcript){
		int t = 0.025;
		for(Word w: transcript){
			double ps=0, vs=0;
			double ind=0;
			while(t<w.endTime){
				ind++;
				ps+=AudAnControl.p.get(t);
				vs+=AudAnControl.v.get(t);
				t+=0.025;
			}
			if(ind==0)ind++;
			w.pitch = ps/ind;
			w.volume = vs/ind;
		}
		
	}
}