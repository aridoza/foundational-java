package com.generalassembly.friends;

public class FriendsLister {
    public static void main(String[] friends) {
        for(int i = 0; i < friends.length; i++) {
            String myFriend = friends[i];
            System.out.println(myFriend);
        }
    }
}
