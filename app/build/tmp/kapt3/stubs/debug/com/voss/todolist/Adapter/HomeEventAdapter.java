package com.voss.todolist.Adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0014B\u0005\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\r\u001a\u00020\b2\n\u0010\u000e\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0007H\u0016J\u001c\u0010\u0010\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007H\u0016R&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u0015"}, d2 = {"Lcom/voss/todolist/Adapter/HomeEventAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/voss/todolist/Data/EventTypes;", "Lcom/voss/todolist/Adapter/HomeEventAdapter$EventTodayViewHolder;", "()V", "itemOnClick", "Lkotlin/Function1;", "", "", "getItemOnClick", "()Lkotlin/jvm/functions/Function1;", "setItemOnClick", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "EventTodayViewHolder", "app_debug"})
public final class HomeEventAdapter extends androidx.recyclerview.widget.ListAdapter<com.voss.todolist.Data.EventTypes, com.voss.todolist.Adapter.HomeEventAdapter.EventTodayViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> itemOnClick;
    
    public HomeEventAdapter() {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getItemOnClick() {
        return null;
    }
    
    public final void setItemOnClick(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.voss.todolist.Adapter.HomeEventAdapter.EventTodayViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Adapter.HomeEventAdapter.EventTodayViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u000b"}, d2 = {"Lcom/voss/todolist/Adapter/HomeEventAdapter$EventTodayViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/voss/todolist/databinding/RowListitemBinding;", "(Lcom/voss/todolist/Adapter/HomeEventAdapter;Lcom/voss/todolist/databinding/RowListitemBinding;)V", "date", "Landroid/widget/TextView;", "getDate", "()Landroid/widget/TextView;", "title", "getTitle", "app_debug"})
    public final class EventTodayViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        private final com.voss.todolist.databinding.RowListitemBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView title = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView date = null;
        
        public EventTodayViewHolder(@org.jetbrains.annotations.NotNull()
        com.voss.todolist.databinding.RowListitemBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getDate() {
            return null;
        }
    }
}