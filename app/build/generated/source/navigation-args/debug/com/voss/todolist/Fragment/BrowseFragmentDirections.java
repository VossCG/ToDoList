package com.voss.todolist.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.voss.todolist.R;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class BrowseFragmentDirections {
  private BrowseFragmentDirections() {
  }

  @NonNull
  public static ActionBrowseFragmentToMonthFragment actionBrowseFragmentToMonthFragment(int year) {
    return new ActionBrowseFragmentToMonthFragment(year);
  }

  public static class ActionBrowseFragmentToMonthFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionBrowseFragmentToMonthFragment(int year) {
      this.arguments.put("year", year);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionBrowseFragmentToMonthFragment setYear(int year) {
      this.arguments.put("year", year);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("year")) {
        int year = (int) arguments.get("year");
        __result.putInt("year", year);
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_browseFragment_to_monthFragment;
    }

    @SuppressWarnings("unchecked")
    public int getYear() {
      return (int) arguments.get("year");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionBrowseFragmentToMonthFragment that = (ActionBrowseFragmentToMonthFragment) object;
      if (arguments.containsKey("year") != that.arguments.containsKey("year")) {
        return false;
      }
      if (getYear() != that.getYear()) {
        return false;
      }
      if (getActionId() != that.getActionId()) {
        return false;
      }
      return true;
    }

    @Override
    public int hashCode() {
      int result = 1;
      result = 31 * result + getYear();
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionBrowseFragmentToMonthFragment(actionId=" + getActionId() + "){"
          + "year=" + getYear()
          + "}";
    }
  }
}
