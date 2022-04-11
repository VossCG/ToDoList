package com.voss.todolist.Fragment;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import com.voss.todolist.Adapter.ArgsToContent;
import com.voss.todolist.Data.EventTypes;
import com.voss.todolist.R;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;

public class HomeFragmentDirections {
  private HomeFragmentDirections() {
  }

  @NonNull
  public static ActionHomeFragmentToContentFragment actionHomeFragmentToContentFragment(
      @NonNull ArgsToContent ContentArgs) {
    return new ActionHomeFragmentToContentFragment(ContentArgs);
  }

  @NonNull
  public static ActionHomeFragmentToUpdateEventFragment actionHomeFragmentToUpdateEventFragment(
      @NonNull EventTypes EventTypes) {
    return new ActionHomeFragmentToUpdateEventFragment(EventTypes);
  }

  public static class ActionHomeFragmentToContentFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeFragmentToContentFragment(@NonNull ArgsToContent ContentArgs) {
      if (ContentArgs == null) {
        throw new IllegalArgumentException("Argument \"ContentArgs\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ContentArgs", ContentArgs);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToContentFragment setContentArgs(@NonNull ArgsToContent ContentArgs) {
      if (ContentArgs == null) {
        throw new IllegalArgumentException("Argument \"ContentArgs\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("ContentArgs", ContentArgs);
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    @NonNull
    public Bundle getArguments() {
      Bundle __result = new Bundle();
      if (arguments.containsKey("ContentArgs")) {
        ArgsToContent ContentArgs = (ArgsToContent) arguments.get("ContentArgs");
        if (Parcelable.class.isAssignableFrom(ArgsToContent.class) || ContentArgs == null) {
          __result.putParcelable("ContentArgs", Parcelable.class.cast(ContentArgs));
        } else if (Serializable.class.isAssignableFrom(ArgsToContent.class)) {
          __result.putSerializable("ContentArgs", Serializable.class.cast(ContentArgs));
        } else {
          throw new UnsupportedOperationException(ArgsToContent.class.getName() + " must implement Parcelable or Serializable or must be an Enum.");
        }
      }
      return __result;
    }

    @Override
    public int getActionId() {
      return R.id.action_homeFragment_to_contentFragment;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    public ArgsToContent getContentArgs() {
      return (ArgsToContent) arguments.get("ContentArgs");
    }

    @Override
    public boolean equals(Object object) {
      if (this == object) {
          return true;
      }
      if (object == null || getClass() != object.getClass()) {
          return false;
      }
      ActionHomeFragmentToContentFragment that = (ActionHomeFragmentToContentFragment) object;
      if (arguments.containsKey("ContentArgs") != that.arguments.containsKey("ContentArgs")) {
        return false;
      }
      if (getContentArgs() != null ? !getContentArgs().equals(that.getContentArgs()) : that.getContentArgs() != null) {
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
      result = 31 * result + (getContentArgs() != null ? getContentArgs().hashCode() : 0);
      result = 31 * result + getActionId();
      return result;
    }

    @Override
    public String toString() {
      return "ActionHomeFragmentToContentFragment(actionId=" + getActionId() + "){"
          + "ContentArgs=" + getContentArgs()
          + "}";
    }
  }

  public static class ActionHomeFragmentToUpdateEventFragment implements NavDirections {
    private final HashMap arguments = new HashMap();

    @SuppressWarnings("unchecked")
    private ActionHomeFragmentToUpdateEventFragment(@NonNull EventTypes EventTypes) {
      if (EventTypes == null) {
        throw new IllegalArgumentException("Argument \"EventTypes\" is marked as non-null but was passed a null value.");
      }
      this.arguments.put("EventTypes", EventTypes);
    }

    @NonNull
    @SuppressWarnings("unchecked")
    public ActionHomeFragmentToUpdateEventFragment setEventTypes(@NonNull EventTypes EventTypes) {
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
      return R.id.action_homeFragment_to_updateEventFragment;
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
      ActionHomeFragmentToUpdateEventFragment that = (ActionHomeFragmentToUpdateEventFragment) object;
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
      return "ActionHomeFragmentToUpdateEventFragment(actionId=" + getActionId() + "){"
          + "EventTypes=" + getEventTypes()
          + "}";
    }
  }
}
