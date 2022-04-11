package com.voss.todolist.Fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010-\u001a\u00020.H\u0002J\b\u0010/\u001a\u00020.H\u0002J\u0018\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u000fH\u0002J\b\u00104\u001a\u00020.H\u0016J\b\u00105\u001a\u00020.H\u0016J\u001a\u00106\u001a\u00020.2\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:H\u0016J\b\u0010;\u001a\u00020.H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R+\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00118B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R+\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00118B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001d\u0010\u0019\u001a\u0004\b\u001b\u0010\u0015\"\u0004\b\u001c\u0010\u0017R+\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00118B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b!\u0010\u0019\u001a\u0004\b\u001f\u0010\u0015\"\u0004\b \u0010\u0017R\u001b\u0010\"\u001a\u00020#8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b&\u0010\'\u001a\u0004\b$\u0010%R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b,\u0010\'\u001a\u0004\b*\u0010+\u00a8\u0006<"}, d2 = {"Lcom/voss/todolist/Fragment/UpdateEventFragment;", "Lcom/voss/todolist/Fragment/BaseFragment;", "Lcom/voss/todolist/databinding/UpdateeventfragmentBinding;", "()V", "args", "Lcom/voss/todolist/Fragment/UpdateEventFragmentArgs;", "getArgs", "()Lcom/voss/todolist/Fragment/UpdateEventFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "datePickerListener", "Landroid/app/DatePickerDialog$OnDateSetListener;", "getDatePickerListener", "()Landroid/app/DatePickerDialog$OnDateSetListener;", "mDate", "", "mDateInteger", "", "<set-?>", "mDay", "getMDay", "()I", "setMDay", "(I)V", "mDay$delegate", "Lkotlin/properties/ReadWriteProperty;", "mMonth", "getMMonth", "setMMonth", "mMonth$delegate", "mYear", "getMYear", "setMYear", "mYear$delegate", "navController", "Landroidx/navigation/NavController;", "getNavController", "()Landroidx/navigation/NavController;", "navController$delegate", "Lkotlin/Lazy;", "viewModel", "Lcom/voss/todolist/ViewModel/EventViewModel;", "getViewModel", "()Lcom/voss/todolist/ViewModel/EventViewModel;", "viewModel$delegate", "initDateAttributesWithArgs", "", "initView", "inputCheck", "", "title", "content", "onStart", "onStop", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "updateItemData", "app_debug"})
public final class UpdateEventFragment extends com.voss.todolist.Fragment.BaseFragment<com.voss.todolist.databinding.UpdateeventfragmentBinding> {
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy navController$delegate = null;
    private final kotlin.properties.ReadWriteProperty mYear$delegate = null;
    private final kotlin.properties.ReadWriteProperty mMonth$delegate = null;
    private final kotlin.properties.ReadWriteProperty mDay$delegate = null;
    private java.lang.String mDate = "yyyy/mm/dd";
    private int mDateInteger = 0;
    @org.jetbrains.annotations.NotNull()
    private final android.app.DatePickerDialog.OnDateSetListener datePickerListener = null;
    
    public UpdateEventFragment() {
        super(null);
    }
    
    private final com.voss.todolist.Fragment.UpdateEventFragmentArgs getArgs() {
        return null;
    }
    
    private final com.voss.todolist.ViewModel.EventViewModel getViewModel() {
        return null;
    }
    
    private final androidx.navigation.NavController getNavController() {
        return null;
    }
    
    private final int getMYear() {
        return 0;
    }
    
    private final void setMYear(int p0) {
    }
    
    private final int getMMonth() {
        return 0;
    }
    
    private final void setMMonth(int p0) {
    }
    
    private final int getMDay() {
        return 0;
    }
    
    private final void setMDay(int p0) {
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initDateAttributesWithArgs() {
    }
    
    private final void updateItemData() {
    }
    
    private final void initView() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.DatePickerDialog.OnDateSetListener getDatePickerListener() {
        return null;
    }
    
    private final boolean inputCheck(java.lang.String title, java.lang.String content) {
        return false;
    }
    
    @java.lang.Override()
    public void onStart() {
    }
    
    @java.lang.Override()
    public void onStop() {
    }
}