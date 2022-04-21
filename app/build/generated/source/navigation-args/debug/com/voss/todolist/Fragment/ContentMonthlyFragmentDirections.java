package com.voss.todolist.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.voss.todolist.Data.EventTypes;
import com.voss.todolist.R;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class ContentMonthlyFragmentDirections {
  private ContentMonthlyFragmentDirections() {
  }

  @NonNull
  public static ActionContentFragmentToUpdateEventFragment actionContentFragmentToUpdateEventFragment(
      @NonNull EventTypes EventTypes) {
    return new ActionContentFragmentToUpdateEventFragment(EventTypes);
  }

  @NonNull
  public static NavDirections actionContentFragmentToEditEventFragment() {
    return new ActionOnlyNavDirections(R.id.action_contentFragment_to_editEventFragment);
  }

  public static class ActionContentFragmentToUpdateEventFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionContentFragmentToUpdateEventFragment(@NonNull EventTypes EventTypes) {
      if (EventTypes == null) {
        throw new IllegalArgumentException("Argument \"EventTypes\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("EventTypes", EventTypes);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionContentFragmentToUpdateEventFragment setEventTypes(
        @NonNull EventTypes EventTypes) {
      if (EventTypes == null) {
        throw new IllegalArgumentException("Argument \"EventTypes\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("EventTypes", EventTypes);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("EventTypes")) {
        EventTypes EventTypes = (EventTypes) arguments.get("EventTypes");
        if (Parcelable.class.isAssignableFrom(EventTypes.class) || EventTypes == null) {
          __result.putParcelable("EventTypes", Parcelable.class.cast(EventTypes));
        } else if (Serializable.class.isAssignableFrom(EventTypes.class)) {
          __result.putSerializable("EventTypes", Serializable.class.cast(EventTypes));
        } else {
          throw new UnsupportedOperationException(EventTypes.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_contentFragment_to_updateEventFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public EventTypes getEventTypes() {
      return (EventTypes) arguments.get("EventTypes");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionContentFragmentToUpdateEventFragment that = (ActionContentFragmentToUpdateEventFragment) object;
      if (arguments.containsKey("EventTypes") != that.arguments.containsKey("EventTypes")) {
        return false;
      }
      if (getEventTypes() != null ? !getEventTypes().equals(that.getEventTypes()) : that.getEventTypes() != null) {
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
      result = 31 * result + (getEventTypes() != null ? getEventTypes().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionContentFragmentToUpdateEventFragment(actionId=" + getActionId() + "){"
          + "EventTypes=" + getEventTypes()
          + "}";
    }
  }
}
