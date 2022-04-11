package com.voss.todolist.Fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.voss.todolist.R;

public class EditEventFragmentDirections {
  private EditEventFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionEditEventFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_editEventFragment_to_homeFragment);
  }
}
