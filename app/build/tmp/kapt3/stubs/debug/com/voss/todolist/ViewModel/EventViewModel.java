package com.voss.todolist.ViewModel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0017J\u000e\u0010!\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0017J\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010#\u001a\u00020\u000fJ\u001e\u0010$\u001a\u00020\u000f2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&2\u0006\u0010(\u001a\u00020&J\u001e\u0010)\u001a\u00020&2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&2\u0006\u0010(\u001a\u00020&J\u001e\u0010*\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020&2\u0006\u0010(\u001a\u00020&J\u000e\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u000fJ\u000e\u0010-\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0017R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u00a8\u0006."}, d2 = {"Lcom/voss/todolist/ViewModel/EventViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "repository", "Lcom/voss/todolist/Data/EventRepository;", "(Landroid/app/Application;Lcom/voss/todolist/Data/EventRepository;)V", "calendar", "Ljava/util/Calendar;", "getCalendar", "()Ljava/util/Calendar;", "calendar$delegate", "Lkotlin/Lazy;", "date", "Landroidx/lifecycle/MutableLiveData;", "", "getDate", "()Landroidx/lifecycle/MutableLiveData;", "filterFactor", "getFilterFactor", "readAllEvent", "Landroidx/lifecycle/LiveData;", "", "Lcom/voss/todolist/Data/EventTypes;", "getReadAllEvent", "()Landroidx/lifecycle/LiveData;", "getRepository", "()Lcom/voss/todolist/Data/EventRepository;", "setRepository", "(Lcom/voss/todolist/Data/EventRepository;)V", "addEvent", "", "eventType", "deleteEvent", "filterDataWithFactor", "inputData", "getDateFormat", "year", "", "month", "day", "getDateInteger", "setDate", "setFilterFactor", "factor", "updateEvent", "app_debug"})
public final class EventViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private com.voss.todolist.Data.EventRepository repository;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.voss.todolist.Data.EventTypes>> readAllEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> date = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> filterFactor = null;
    private final kotlin.Lazy calendar$delegate = null;
    
    @javax.inject.Inject()
    public EventViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.voss.todolist.Data.EventRepository repository) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.voss.todolist.Data.EventRepository getRepository() {
        return null;
    }
    
    public final void setRepository(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Data.EventRepository p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.voss.todolist.Data.EventTypes>> getReadAllEvent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getDate() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getFilterFactor() {
        return null;
    }
    
    private final java.util.Calendar getCalendar() {
        return null;
    }
    
    public final void addEvent(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Data.EventTypes eventType) {
    }
    
    public final void updateEvent(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Data.EventTypes eventType) {
    }
    
    public final void deleteEvent(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Data.EventTypes eventType) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDateFormat(int year, int month, int day) {
        return null;
    }
    
    public final void setDate(int year, int month, int day) {
    }
    
    public final void setFilterFactor(@org.jetbrains.annotations.NotNull()
    java.lang.String factor) {
    }
    
    public final int getDateInteger(int year, int month, int day) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.voss.todolist.Data.EventTypes> filterDataWithFactor(@org.jetbrains.annotations.NotNull()
    java.lang.String inputData) {
        return null;
    }
}