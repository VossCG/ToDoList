package com.voss.todolist.Room;

import java.lang.System;

@androidx.room.Database(entities = {com.voss.todolist.Data.EventTypes.class}, version = 1, exportSchema = false)
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&\u00a8\u0006\u0006"}, d2 = {"Lcom/voss/todolist/Room/EventDataBase;", "Landroidx/room/RoomDatabase;", "()V", "eventRoomDao", "Lcom/voss/todolist/Room/EventDao;", "Companion", "app_debug"})
public abstract class EventDataBase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    public static final com.voss.todolist.Room.EventDataBase.Companion Companion = null;
    private static com.voss.todolist.Room.EventDataBase INSTANCE;
    
    public EventDataBase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.voss.todolist.Room.EventDao eventRoomDao();
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/voss/todolist/Room/EventDataBase$Companion;", "", "()V", "INSTANCE", "Lcom/voss/todolist/Room/EventDataBase;", "getInstance", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.voss.todolist.Room.EventDataBase getInstance(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}