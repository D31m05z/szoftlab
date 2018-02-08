package Road;

import Main.Drawable;

/*
	Blokádok interfésze
*/

public interface IBlockade extends Drawable
{
	// Blokád fogadja a támadást
	public void damage(long damage);
	
	// Útparaméter elkérése
	public long getT();
	
	// Javítás kezdeményezése
	public void repair();
}
