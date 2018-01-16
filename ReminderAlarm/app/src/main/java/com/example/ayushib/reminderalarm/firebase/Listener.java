package com.example.ayushib.reminderalarm.firebase;

/**
 * Created by Ayushi B on 15-Jan-18.
 */

public interface Listener <Child extends Item> extends CreateListener<Child>, ReadListener<Child>, RemoveListener<Child> {



}
