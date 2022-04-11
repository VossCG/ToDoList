package com.voss.todolist.Fragment;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.voss.todolist.R;

public class UpdateEventFragmentDirections {
  private UpdateEventFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionUpdateEventFragmentToHomeFragment() {
    return new ActionOnlyNavDirections(R.id.action_updateEventFragment_to_homeFragment);
  }
}
