// Generated by view binder compiler. Do not edit!
package com.voss.todolist.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.voss.todolist.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class RownestedItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView NestedRowDateTextView;

  @NonNull
  public final TextView NestedRowTitleTextView;

  private RownestedItemBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView NestedRowDateTextView, @NonNull TextView NestedRowTitleTextView) {
    this.rootView = rootView;
    this.NestedRowDateTextView = NestedRowDateTextView;
    this.NestedRowTitleTextView = NestedRowTitleTextView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RownestedItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RownestedItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.rownested_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RownestedItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.NestedRowDate_textView;
      TextView NestedRowDateTextView = ViewBindings.findChildViewById(rootView, id);
      if (NestedRowDateTextView == null) {
        break missingId;
      }

      id = R.id.NestedRowTitle_textView;
      TextView NestedRowTitleTextView = ViewBindings.findChildViewById(rootView, id);
      if (NestedRowTitleTextView == null) {
        break missingId;
      }

      return new RownestedItemBinding((ConstraintLayout) rootView, NestedRowDateTextView,
          NestedRowTitleTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
