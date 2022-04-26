package com.voss.todolist.Room;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.voss.todolist.Data.EventTypes;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class EventDao_Impl implements EventDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EventTypes> __insertionAdapterOfEventTypes;

  private final EntityDeletionOrUpdateAdapter<EventTypes> __deletionAdapterOfEventTypes;

  private final EntityDeletionOrUpdateAdapter<EventTypes> __updateAdapterOfEventTypes;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public EventDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEventTypes = new EntityInsertionAdapter<EventTypes>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `EventTypes` (`title`,`date`,`content`,`year`,`month`,`day`,`dateInteger`,`number`,`id`) VALUES (?,?,?,?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EventTypes value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getDate() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDate());
        }
        if (value.getContent() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getContent());
        }
        stmt.bindLong(4, value.getYear());
        stmt.bindLong(5, value.getMonth());
        stmt.bindLong(6, value.getDay());
        stmt.bindLong(7, value.getDateInteger());
        if (value.getNumber() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getNumber());
        }
        stmt.bindLong(9, value.getId());
      }
    };
    this.__deletionAdapterOfEventTypes = new EntityDeletionOrUpdateAdapter<EventTypes>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `EventTypes` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EventTypes value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfEventTypes = new EntityDeletionOrUpdateAdapter<EventTypes>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `EventTypes` SET `title` = ?,`date` = ?,`content` = ?,`year` = ?,`month` = ?,`day` = ?,`dateInteger` = ?,`number` = ?,`id` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, EventTypes value) {
        if (value.getTitle() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getTitle());
        }
        if (value.getDate() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDate());
        }
        if (value.getContent() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getContent());
        }
        stmt.bindLong(4, value.getYear());
        stmt.bindLong(5, value.getMonth());
        stmt.bindLong(6, value.getDay());
        stmt.bindLong(7, value.getDateInteger());
        if (value.getNumber() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, value.getNumber());
        }
        stmt.bindLong(9, value.getId());
        stmt.bindLong(10, value.getId());
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from EventTypes";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final EventTypes event, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfEventTypes.insert(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object delete(final EventTypes event, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfEventTypes.handle(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object update(final EventTypes event, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfEventTypes.handle(event);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public void clearAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearAll.release(_stmt);
    }
  }

  @Override
  public LiveData<List<EventTypes>> getAll() {
    final String _sql = "select * from EventTypes ORDER BY dateInteger ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"EventTypes"}, false, new Callable<List<EventTypes>>() {
      @Override
      public List<EventTypes> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfYear = CursorUtil.getColumnIndexOrThrow(_cursor, "year");
          final int _cursorIndexOfMonth = CursorUtil.getColumnIndexOrThrow(_cursor, "month");
          final int _cursorIndexOfDay = CursorUtil.getColumnIndexOrThrow(_cursor, "day");
          final int _cursorIndexOfDateInteger = CursorUtil.getColumnIndexOrThrow(_cursor, "dateInteger");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final List<EventTypes> _result = new ArrayList<EventTypes>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final EventTypes _item;
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final int _tmpYear;
            _tmpYear = _cursor.getInt(_cursorIndexOfYear);
            final int _tmpMonth;
            _tmpMonth = _cursor.getInt(_cursorIndexOfMonth);
            final int _tmpDay;
            _tmpDay = _cursor.getInt(_cursorIndexOfDay);
            final int _tmpDateInteger;
            _tmpDateInteger = _cursor.getInt(_cursorIndexOfDateInteger);
            final Integer _tmpNumber;
            if (_cursor.isNull(_cursorIndexOfNumber)) {
              _tmpNumber = null;
            } else {
              _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            }
            _item = new EventTypes(_tmpTitle,_tmpDate,_tmpContent,_tmpYear,_tmpMonth,_tmpDay,_tmpDateInteger,_tmpNumber);
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
