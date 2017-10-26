package com.realmbookexample.database;

import com.realmbookexample.model.Book;

import io.realm.Realm;

/**
 *
 */
class RealmInitialData implements Realm.Transaction {

    @Override
    public void execute(Realm realm) {
        Book book = new Book();
        book.setId(1);
        book.setAuthor("Roto Henry");
        book.setTitle("Android Application Development");
        book.setImageUrl("http://api.androidhive.info/images/realm/1.png");
        realm.insertOrUpdate(book);
    }
    
    @Override
    public int hashCode() {
        return RealmInitialData.class.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        return obj != null && obj instanceof RealmInitialData;
    }
}
