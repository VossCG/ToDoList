package com.voss.todolist.ViewModel;

import java.lang.System;

@dagger.hilt.android.lifecycle.HiltViewModel()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0018J\u000e\u0010\"\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0018J\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010$\u001a\u00020\u0010J\u001e\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020\'J\u001e\u0010*\u001a\u00020\'2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020\'J\u001e\u0010+\u001a\u00020 2\u0006\u0010&\u001a\u00020\'2\u0006\u0010(\u001a\u00020\'2\u0006\u0010)\u001a\u00020\'J\u000e\u0010,\u001a\u00020 2\u0006\u0010-\u001a\u00020\u0010J\u000e\u0010.\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0018R#\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e\u00a8\u0006/"}, d2 = {"Lcom/voss/todolist/ViewModel/EventViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "repository", "Lcom/voss/todolist/Data/EventRepository;", "(Landroid/app/Application;Lcom/voss/todolist/Data/EventRepository;)V", "calendar", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "getCalendar", "()Ljava/util/Calendar;", "calendar$delegate", "Lkotlin/Lazy;", "date", "Landroidx/lifecycle/MutableLiveData;", "", "getDate", "()Landroidx/lifecycle/MutableLiveData;", "filterFactor", "getFilterFactor", "readAllEvent", "Landroidx/lifecycle/LiveData;", "", "Lcom/voss/todolist/Data/EventTypes;", "getReadAllEvent", "()Landroidx/lifecycle/LiveData;", "getRepository", "()Lcom/voss/todolist/Data/EventRepository;", "setRepository", "(Lcom/voss/todolist/Data/EventRepository;)V", "addEvent", "", "eventType", "deleteEvent", "filterDataWithFactor", "inputData", "getDateFormat", "year", "", "month", "day", "getDateInteger", "setDate", "setFilterFactor", "factor", "updateEvent", "app_debug"})
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
    
    public final void setDate(int year, int month, int day) {
    }
    
    public final void setFilterFactor(@org.jetbrains.annotations.NotNull()
    java.lang.String factor) {
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