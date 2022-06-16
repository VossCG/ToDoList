package com.voss.todolist.Fragment;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010&\u001a\u00020\'H\u0002J\b\u0010(\u001a\u00020\'H\u0016J\u001a\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010.\u001a\u00020\'2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010/\u001a\u00020\'2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u00100\u001a\u00020\'2\u0006\u0010\f\u001a\u00020\rH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\t\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0013\u001a\u00020\u00148BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0017\u0010\t\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\u0018\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001a\u0010\t\u001a\u0004\b\u0019\u0010\u0007R\u001b\u0010\u001b\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\t\u001a\u0004\b\u001c\u0010\u0007R\u001b\u0010\u001e\u001a\u00020\u00058BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\t\u001a\u0004\b\u001f\u0010\u0007R\u001b\u0010!\u001a\u00020\"8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b%\u0010\t\u001a\u0004\b#\u0010$\u00a8\u00061"}, d2 = {"Lcom/voss/todolist/Fragment/SearchFragment;", "Lcom/voss/todolist/Fragment/BaseFragment;", "Lcom/voss/todolist/databinding/FragmentSearchBinding;", "()V", "fromBottom", "Landroid/view/animation/Animation;", "getFromBottom", "()Landroid/view/animation/Animation;", "fromBottom$delegate", "Lkotlin/Lazy;", "inputData", "", "isExpanded", "", "mAdapter", "Lcom/voss/todolist/Adapter/SearChRecyclerAdapter;", "getMAdapter", "()Lcom/voss/todolist/Adapter/SearChRecyclerAdapter;", "mAdapter$delegate", "navController", "Landroidx/navigation/NavController;", "getNavController", "()Landroidx/navigation/NavController;", "navController$delegate", "rotateClose", "getRotateClose", "rotateClose$delegate", "rotateOpen", "getRotateOpen", "rotateOpen$delegate", "toBottom", "getToBottom", "toBottom$delegate", "viewModel", "Lcom/voss/todolist/ViewModel/EventViewModel;", "getViewModel", "()Lcom/voss/todolist/ViewModel/EventViewModel;", "viewModel$delegate", "onAddButtonClicked", "", "onDestroyView", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setAnimation", "setInVisibleUnClickable", "setVisibility", "app_debug"})
@dagger.hilt.android.AndroidEntryPoint()
public final class SearchFragment extends com.voss.todolist.Fragment.BaseFragment<com.voss.todolist.databinding.FragmentSearchBinding> {
    private final kotlin.Lazy viewModel$delegate = null;
    private final kotlin.Lazy mAdapter$delegate = null;
    private final kotlin.Lazy navController$delegate = null;
    private java.lang.String inputData = "null";
    private final kotlin.Lazy rotateOpen$delegate = null;
    private final kotlin.Lazy rotateClose$delegate = null;
    private final kotlin.Lazy fromBottom$delegate = null;
    private final kotlin.Lazy toBottom$delegate = null;
    private boolean isExpanded = false;
    
    public SearchFragment() {
        super(null);
    }
    
    private final com.voss.todolist.ViewModel.EventViewModel getViewModel() {
        return null;
    }
    
    private final com.voss.todolist.Adapter.SearChRecyclerAdapter getMAdapter() {
        return null;
    }
    
    private final androidx.navigation.NavController getNavController() {
        return null;
    }
    
    private final android.view.animation.Animation getRotateOpen() {
        return null;
    }
    
    private final android.view.animation.Animation getRotateClose() {
        return null;
    }
    
    private final android.view.animation.Animation getFromBottom() {
        return null;
    }
    
    private final android.view.animation.Animation getToBottom() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void onAddButtonClicked() {
    }
    
    private final void setVisibility(boolean isExpanded) {
    }
    
    private final void setAnimation(boolean isExpanded) {
    }
    
    private final void setInVisibleUnClickable(boolean isExpanded) {
    }
}