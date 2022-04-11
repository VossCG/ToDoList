package com.voss.todolist.Adapter;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u001c\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J\u0014\u0010\u0019\u001a\u00020\u00122\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\b0\u0007R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001c"}, d2 = {"Lcom/voss/todolist/Adapter/ContentMonthlyAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/voss/todolist/Adapter/ContentMonthlyAdapter$ContentMonthlyViewHolder;", "updateRecyclerData", "Lcom/voss/todolist/UpdateRecyclerData;", "(Lcom/voss/todolist/UpdateRecyclerData;)V", "oldList", "", "Lcom/voss/todolist/Data/EventTypes;", "getOldList", "()Ljava/util/List;", "setOldList", "(Ljava/util/List;)V", "getUpdateRecyclerData", "()Lcom/voss/todolist/UpdateRecyclerData;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "setData", "newList", "ContentMonthlyViewHolder", "app_debug"})
public final class ContentMonthlyAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.voss.todolist.Adapter.ContentMonthlyAdapter.ContentMonthlyViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final com.voss.todolist.UpdateRecyclerData updateRecyclerData = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.voss.todolist.Data.EventTypes> oldList;
    
    public ContentMonthlyAdapter(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.UpdateRecyclerData updateRecyclerData) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.voss.todolist.UpdateRecyclerData getUpdateRecyclerData() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.voss.todolist.Data.EventTypes> getOldList() {
        return null;
    }
    
    public final void setOldList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.voss.todolist.Data.EventTypes> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.voss.todolist.Adapter.ContentMonthlyAdapter.ContentMonthlyViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.voss.todolist.Adapter.ContentMonthlyAdapter.ContentMonthlyViewHolder holder, int position) {
    }
    
    public final void setData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.voss.todolist.Data.EventTypes> newList) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\b\u00a8\u0006\u0015"}, d2 = {"Lcom/voss/todolist/Adapter/ContentMonthlyAdapter$ContentMonthlyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/voss/todolist/databinding/RowContenmonthlytitemBinding;", "(Lcom/voss/todolist/Adapter/ContentMonthlyAdapter;Lcom/voss/todolist/databinding/RowContenmonthlytitemBinding;)V", "content", "Landroid/widget/TextView;", "getContent", "()Landroid/widget/TextView;", "date", "getDate", "deleteButton", "Landroid/widget/ImageButton;", "getDeleteButton", "()Landroid/widget/ImageButton;", "editButton", "getEditButton", "showMoreContent", "getShowMoreContent", "title", "getTitle", "app_debug"})
    public final class ContentMonthlyViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView title = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView content = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView date = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView showMoreContent = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton editButton = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageButton deleteButton = null;
        
        public ContentMonthlyViewHolder(@org.jetbrains.annotations.NotNull()
        com.voss.todolist.databinding.RowContenmonthlytitemBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTitle() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getContent() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getDate() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getShowMoreContent() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getEditButton() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageButton getDeleteButton() {
            return null;
        }
    }
}