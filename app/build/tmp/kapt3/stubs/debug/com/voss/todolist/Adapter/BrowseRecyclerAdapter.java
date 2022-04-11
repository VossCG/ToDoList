package com.voss.todolist.Adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0016B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u0005H\u0016J\u001c\u0010\u000e\u001a\u00020\u000f2\n\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0005H\u0016J\u001c\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0005H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0017"}, d2 = {"Lcom/voss/todolist/Adapter/BrowseRecyclerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/voss/todolist/Adapter/BrowseRecyclerAdapter$YearRecyclerViewHolder;", "list", "", "", "navController", "Landroidx/navigation/NavController;", "(Ljava/util/List;Landroidx/navigation/NavController;)V", "getList", "()Ljava/util/List;", "getNavController", "()Landroidx/navigation/NavController;", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "YearRecyclerViewHolder", "app_debug"})
public final class BrowseRecyclerAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.voss.todolist.Adapter.BrowseRecyclerAdapter.YearRecyclerViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Integer> list = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavController navController = null;
    
    public BrowseRecyclerAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> list, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavController navController) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Integer> getList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.navigation.NavController getNavController() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.voss.todolist.Adapter.BrowseRecyclerAdapter.YearRecyclerViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Adapter.BrowseRecyclerAdapter.YearRecyclerViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/voss/todolist/Adapter/BrowseRecyclerAdapter$YearRecyclerViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/voss/todolist/databinding/RowYearitemBinding;", "(Lcom/voss/todolist/Adapter/BrowseRecyclerAdapter;Lcom/voss/todolist/databinding/RowYearitemBinding;)V", "year", "Landroid/widget/TextView;", "getYear", "()Landroid/widget/TextView;", "app_debug"})
    public final class YearRecyclerViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView year = null;
        
        public YearRecyclerViewHolder(@org.jetbrains.annotations.NotNull()
        com.voss.todolist.databinding.RowYearitemBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getYear() {
            return null;
        }
    }
}