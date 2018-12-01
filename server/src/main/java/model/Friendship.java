package model;

public class Friendship {

	private User fromUser;
	private User toUser;

	public Friendship(){}
	
	public Friendship(User from, User to) {
		
		this.fromUser = from;
		this.toUser = to;
	}
}
