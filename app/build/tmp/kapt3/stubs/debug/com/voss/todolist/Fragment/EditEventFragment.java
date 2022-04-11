package com.voss.todolist.Fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J(\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u001cH\u0002J\b\u0010\u001e\u001a\u00020\u001cH\u0016J\b\u0010\u001f\u001a\u00020\u001cH\u0016J\u001a\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u001cH\u0002R\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0013\u0010\u000e\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006&"}, d2 = {"Lcom/voss/todolist/Fragment/EditEventFragment;", "Lcom/voss/todolist/Fragment/BaseFragment;", "Lcom/voss/todolist/databinding/EditeventfragmentBinding;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "dateDetail", "Lcom/voss/todolist/Fragment/CalendarViewDetail;", "evenViewModel", "Lcom/voss/todolist/ViewModel/EventViewModel;", "getEvenViewModel", "()Lcom/voss/todolist/ViewModel/EventViewModel;", "evenViewModel$delegate", "Lkotlin/Lazy;", "navController", "Landroidx/navigation/NavController;", "getNavController", "()Landroidx/navigation/NavController;", "navController$delegate", "checkData", "", "title", "data", "content", "year", "", "insertDataToDataBase", "", "itemViewOnClickEvent", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setViewModelObserve", "app_debug"})
public final class EditEventFragment extends com.voss.todolist.Fragment.BaseFragment<com.voss.todolist.databinding.EditeventfragmentBinding> {
    private final kotlin.Lazy evenViewModel$delegate = null;
    private final kotlin.Lazy navController$delegate = null;
    private final java.lang.String TAG = null;
    private com.voss.todolist.Fragment.CalendarViewDetail dateDetail;
    
    public EditEventFragment() {
        super(null);
    }
    
    private final com.voss.todolist.ViewModel.EventViewModel getEvenViewModel() {
        return null;
    }
    
    private final androidx.navigation.NavController getNavController() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void itemViewOnClickEvent() {
    }
    
    private final void setViewModelObserve() {
    }
    
    private final void insertDataToDataBase() {
    }
    
    private final boolean checkData(java.lang.String title, java.lang.String data, java.lang.String content, int year) {
        return false;
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @java.lang.Override()
    public void onStop() {
    }
}