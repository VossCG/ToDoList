package com.voss.todolist.Room;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\'J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\t0\bH\'J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\f"}, d2 = {"Lcom/voss/todolist/Room/EventDao;", "", "clearAll", "", "delete", "event", "Lcom/voss/todolist/Data/EventTypes;", "getAll", "Landroidx/lifecycle/LiveData;", "", "insert", "update", "app_debug"})
public abstract interface EventDao {
    
    @androidx.room.Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Data.EventTypes event);
    
    @androidx.room.Delete()
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Data.EventTypes event);
    
    @androidx.room.Update()
    public abstract void update(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Data.EventTypes event);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "select * from EventTypes ORDER BY dateInteger ASC")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.voss.todolist.Data.EventTypes>> getAll();
    
    @androidx.room.Query(value = "DELETE from EventTypes")
    public abstract void clearAll();
}