package io.zilker.appstore.beans;

import java.io.Serializable;

public class StoreUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private int wallet;
	private GenericUser genericUser;
	private Applications[] downloadedApps, myApps, wishList;
	
	public int getWallet() {
		return wallet;
	}
	public void setWallet(int wallet) {
		this.wallet = wallet;
	}
	public GenericUser getGenUser() {
		return genericUser;
	}
	public void setGenUser(GenericUser genUser) {
		this.genericUser = genUser;
	}
	public Applications[] getDownloadedApps() {
		return downloadedApps;
	}
	public void setDownloadedApps(Applications[] downloadedApps) {
		this.downloadedApps = downloadedApps;
	}
	public Applications[] getMyApps() {
		return myApps;
	}
	public void setMyApps(Applications[] myApps) {
		this.myApps = myApps;
	}
	public Applications[] getWishList() {
		return wishList;
	}
	public void setWishList(Applications[] wishList) {
		this.wishList = wishList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
