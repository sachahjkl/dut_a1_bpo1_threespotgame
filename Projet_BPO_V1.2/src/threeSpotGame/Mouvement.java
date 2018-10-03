package threeSpotGame;

public class Mouvement {
	public enum type{V, H};
	private Case[] déplacements;
	
	public Mouvement(){
		déplacements = new Case[2];
		
	}
	public void setCases(Case c0, Case c1) {
		déplacements[0] = c0;
		déplacements[1] = c1;

	}
}
