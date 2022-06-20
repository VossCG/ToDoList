package com.voss.todolist.Fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\"\u001a\u00020#H\u0002J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00172\u0006\u0010\'\u001a\u00020\u0017H\u0002J\u001a\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,H\u0016J\b\u0010-\u001a\u00020#H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u001d\u001a\u00020\u001e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b!\u0010\u0015\u001a\u0004\b\u001f\u0010 \u00a8\u0006."}, d2 = {"Lcom/voss/todolist/Fragment/UpdateEventFragment;", "Lcom/voss/todolist/Fragment/BaseFragment;", "Lcom/voss/todolist/databinding/FragmentUpdateEventBinding;", "()V", "args", "Lcom/voss/todolist/Fragment/UpdateEventFragmentArgs;", "getArgs", "()Lcom/voss/todolist/Fragment/UpdateEventFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "argsEventTypes", "Lcom/voss/todolist/Data/EventTypes;", "getArgsEventTypes", "()Lcom/voss/todolist/Data/EventTypes;", "datePickerListener", "Landroid/app/DatePickerDialog$OnDateSetListener;", "navController", "Landroidx/navigation/NavController;", "getNavController", "()Landroidx/navigation/NavController;", "navController$delegate", "Lkotlin/Lazy;", "newDate", "", "newDateInteger", "", "newDay", "newMonth", "newYear", "viewModel", "Lcom/voss/todolist/ViewModel/EventViewModel;", "getViewModel", "()Lcom/voss/todolist/ViewModel/EventViewModel;", "viewModel$delegate", "initView", "", "inputCheck", "", "title", "content", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "updateItemData", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class UpdateEventFragment extends com.voss.todolist.Fragment.BaseFragment<com.voss.todolist.databinding.FragmentUpdateEventBinding> {
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy navController$delegate = null;
    private java.lang.String newDate = "yyyy/mm/dd";
    private int newDateInteger = 0;
    private int newYear = 0;
    private int newMonth = 0;
    private int newDay = 0;
    private final android.app.DatePickerDialog.OnDateSetListener datePickerListener = null;
    
    public UpdateEventFragment() {
        super(null);
    }
    
    private final com.voss.todolist.Fragment.UpdateEventFragmentArgs getArgs() {
        return null;
    }
    
    private final com.voss.todolist.Data.EventTypes getArgsEventTypes() {
        return null;
    }
    
    private final com.voss.todolist.ViewModel.EventViewModel getViewModel() {
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
    
    private final void updateItemData() {
    }
    
    private final void initView() {
    }
    
    private final boolean inputCheck(java.lang.String title, java.lang.String content) {
        return false;
    }
}