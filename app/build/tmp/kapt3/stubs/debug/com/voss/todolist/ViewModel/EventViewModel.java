package com.voss.todolist.ViewModel;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016J\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010 \u001a\u00020\u000eJ\u001e\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#J\u001e\u0010&\u001a\u00020#2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010%\u001a\u00020#J\u000e\u0010\'\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016R#\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/voss/todolist/ViewModel/EventViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "calendar", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "getCalendar", "()Ljava/util/Calendar;", "calendar$delegate", "Lkotlin/Lazy;", "date", "Landroidx/lifecycle/MutableLiveData;", "", "getDate", "()Landroidx/lifecycle/MutableLiveData;", "filterFactor", "getFilterFactor", "readAllEvent", "Landroidx/lifecycle/LiveData;", "", "Lcom/voss/todolist/Data/EventTypes;", "getReadAllEvent", "()Landroidx/lifecycle/LiveData;", "repository", "Lcom/voss/todolist/Data/EventRepository;", "addEvent", "", "eventType", "deleteEvent", "filterDataWithFactor", "inputData", "getDateFormat", "year", "", "month", "day", "getDateInteger", "updateEvent", "app_debug"})
public final class EventViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.voss.todolist.Data.EventTypes>> readAllEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> date = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> filterFactor = null;
    private final com.voss.todolist.Data.EventRepository repository = null;
    private final kotlin.Lazy calendar$delegate = null;
    
    public EventViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
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
    
    public final int getDateInteger(int year, int month, int day) {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.voss.todolist.Data.EventTypes> filterDataWithFactor(@org.jetbrains.annotations.NotNull()
    java.lang.String inputData) {
        return null;
    }
}