package Road;

import Main.Drawable;

/*
	Blokdok interfsze
*/

public interface IBlockade extends Drawable
{
	// Blokd fogadja a tmadst
	public void damage(long damage);
	
	// tparamter elkrse
	public long getT();
	
	// Javts kezdemnyezse
	public void repair();
}
